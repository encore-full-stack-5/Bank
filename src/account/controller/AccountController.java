package account.controller;

import account.application.CreateAccountDto;
import account.domain.Account;
import account.domain.TransactionHistory;
import user.domain.User;

import java.util.List;

public interface AccountController {
    Account createAccount(int userId, int bankId, int employeeId) throws Exception;
    List<Account> getAccounts(int userId) throws Exception;
//    Account getAccount(int accountId) throws Exception;
    void deleteAccount(int accountId) throws Exception;
    TransactionHistory deposit(Account account, int amount) throws Exception;
    TransactionHistory withdraw(Account account, int amount) throws Exception;
    List<TransactionHistory> showTransactions(int accountId) throws Exception;
}