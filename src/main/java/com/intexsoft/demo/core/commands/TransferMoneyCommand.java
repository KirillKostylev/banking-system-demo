package com.intexsoft.demo.core.commands;

import com.intexsoft.demo.core.Command;
import com.intexsoft.demo.core.exception.InvalidTransferRequest;
import com.intexsoft.demo.entity.BankAccount;
import com.intexsoft.demo.entity.ClientType;
import com.intexsoft.demo.repository.BankAccountRepository;
import com.intexsoft.demo.repository.BankRepository;
import com.intexsoft.demo.repository.ClientRepository;
import com.intexsoft.demo.repository.RepositoryFactory;
import com.intexsoft.demo.repository.exception.JsonStorageException;
import com.intexsoft.demo.utils.ParamsValidator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class TransferMoneyCommand extends AbstractCommand implements Command {

    public static final String COMMAND_NAME = "transfer";
    public static final String COMMAND_DESCRIPTION = "Transfer money from 1st account to 2nd (Might take additional fee). Params: sourceBankAccountId, targetBankAccountId, amount. E.g. 'transfer 987846d7-a4ea-44a4-a06a-75cf1513925d 987846d7-a4ea-44a4-a06a-75cf1513925d 100'";

    private final BankAccountRepository bankAccountRepository = RepositoryFactory.INSTANCE.getBankAccountRepository();
    private final BankRepository bankRepository = RepositoryFactory.INSTANCE.getBankRepository();
    private final ClientRepository clientRepository = RepositoryFactory.INSTANCE.getClientRepository();

    public TransferMoneyCommand() {
        super(COMMAND_NAME, COMMAND_DESCRIPTION);
    }

    @Override
    public Object execute(String[] args) throws JsonStorageException {
        ParamsValidator.validateParametersNumber(this, args, 3);
        ParamsValidator.validateAmount(this, args[2]);

        var sourceBankAccount = bankAccountRepository.findById(UUID.fromString(args[0]));
        var targetBankAccount = bankAccountRepository.findById(UUID.fromString(args[1]));
        var transferAmount = new BigDecimal(args[2]);

        if (sourceBankAccount.getCurrency() != targetBankAccount.getCurrency()) {
            throw new RuntimeException("The transfer to another currency is not supported yet.");
        }

        var fee = calculateFee(sourceBankAccount, targetBankAccount);
        var amountWithFee = transferAmount.add(fee);

        if (sourceBankAccount.getAmount().compareTo(amountWithFee) < 0) {
            throw new InvalidTransferRequest("Not enough money on a source bank account.");
        }

        var finalSourceAmount = sourceBankAccount.getAmount().subtract(amountWithFee);
        sourceBankAccount.setAmount(finalSourceAmount);

        var finalTargetAmount = targetBankAccount.getAmount().add(transferAmount);
        targetBankAccount.setAmount(finalTargetAmount);

        var updatedSourceAccount = bankAccountRepository.update(sourceBankAccount);
        bankAccountRepository.update(targetBankAccount);
        return updatedSourceAccount;
    }

    private BigDecimal calculateFee(BankAccount sourceBankAccount, BankAccount targetBankAccount) throws JsonStorageException {
        if (Objects.equals(sourceBankAccount.getBankId(), targetBankAccount.getBankId())) {
            return BigDecimal.ZERO;
        }

        // transfer to a different bank
        var sourceBank = bankRepository.findById(sourceBankAccount.getBankId());
        var sourceClient = clientRepository.findById(sourceBankAccount.getClientId());
        return sourceClient.getClientType() == ClientType.INDIVIDUAL ? sourceBank.getIndividualFee() : sourceBank.getLegalFee();
    }
}
