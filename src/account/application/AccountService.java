package account.application;

import account.domain.Account;
import account.domain.AccountType;
import account.domain.TransactionHistory;
import user.domain.User;

import java.util.List;

//계좌개설, 계좌조회, 계좌삭제, 입금하기, 출금하기, 입출금조회,
public interface AccountService {
    Account createAccount(CreateAccountDto dto) throws Exception;
    List<Account> getAccounts(int userId) throws Exception;
    Account getAccount(int accountId) throws Exception;
    void deleteAccount(int accountId) throws Exception;
    TransactionHistory deposit(Account account, int amount) throws Exception;
    TransactionHistory withdraw(Account account, int amount) throws Exception;
    List<TransactionHistory> showTransactions(int accountId) throws Exception;
}