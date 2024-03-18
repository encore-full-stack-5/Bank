package account.repository;

import account.domain.Account;
import account.domain.TransactionHistory;

import java.util.List;


// 계좌개설, 계좌조회, 계좌삭제, 입금하기, 출금하기, 입출금조회,
public interface AccountRepository {
    Account createAccount(Account account) throws Exception;
    List<Account> findAccountsByUserId(int userId) throws Exception;
    Account findAccountById(int accountId) throws Exception;
    void deleteAccountById(int id) throws Exception;
    TransactionHistory createDepositTransaction(Account account, int amount) throws Exception;
    TransactionHistory createWithdrawTransaction(Account account, int amount) throws Exception;
    List<TransactionHistory> findTransactionsById(int accountId) throws Exception;
}