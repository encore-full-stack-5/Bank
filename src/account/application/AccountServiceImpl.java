package account.application;

import account.domain.Account;
import account.domain.TransactionHistory;
import account.repository.AccountRepository;
import account.repository.AccountRepositoryImpl;

import java.util.List;
import java.util.Random;

public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository= AccountRepositoryImpl.getInstance();
    static class Holder {
        static final AccountService INSTANCE = new AccountServiceImpl();
    }

    public static AccountService getInstance() {
        return AccountServiceImpl.Holder.INSTANCE;
    }

    @Override
    public Account createAccount(CreateAccountDto dto) throws Exception {
        // 비밀번호는 4자리 숫자
        if (!dto.password().matches("^\\d{4}$")) {
            throw new Exception("비밀번호 양식이 유효하지 않습니다.");
        }

        Account account = new Account(
                makeAccountNumber(dto.bankId(), dto.userId()),
                0,
                dto.type(),
                dto.bankId(),
                dto.userId(),
                dto.employeeId(),
                dto.password(),
                dto.type().getInterestRate()
        );

        return repository.createAccount(account);
    }

    @Override
    public List<Account> getAccounts(int userId) throws Exception {
        return repository.findAccountsByUserId(userId);
    }

    @Override
    public Account getAccount(int accountId) throws Exception {
        return repository.findAccountById(accountId);
    }

    @Override
    public void deleteAccount(int accountId) throws Exception {
        repository.deleteAccountById(accountId);
    }

    @Override
    public TransactionHistory deposit(Account account, int amount) throws Exception {
        account.deposit(amount);
        return repository.createDepositTransaction(account, amount);
    }

    @Override
    public TransactionHistory withdraw(Account account, int amount) throws Exception {
        account.withdraw(amount);
        return repository.createWithdrawTransaction(account, amount);
    }

    @Override
    public List<TransactionHistory> showTransactions(int accountId) throws Exception {
        return repository.findTransactionsById(accountId);
    }

    private String makeAccountNumber(int bankId, int userId) {
        return bankId+"-"+makeRandomNumber()+"-"+userId;
    }

    private int makeRandomNumber() {
        Random rnd = new Random();
        return 100000 + rnd.nextInt(900000);
    }
}
