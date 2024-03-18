package account.controller;

import account.application.AccountService;
import account.application.AccountServiceImpl;
import account.application.CreateAccountDto;
import account.domain.Account;
import account.domain.AccountType;
import account.domain.TransactionHistory;
import bank.application.BankService;
import bank.application.BankServiceImpl;
import bank.domain.Bank;
import bank.domain.Employee;
import common.util.ConsoleUtility;

import java.util.List;

public class AccountControllerImpl implements AccountController {
    // 세현

    private final AccountService accountService = AccountServiceImpl.getInstance();
    private final BankService bankService = BankServiceImpl.getInstance();
    static class Holder {
        static final AccountController INSTANCE = new AccountControllerImpl();
    }

    public static AccountController getInstance() {
        return AccountControllerImpl.Holder.INSTANCE;
    }

    @Override
    public Account createAccount(int userId, int bankId, int employeeId) throws Exception {

        // 타입선택
        int typeChoice = ConsoleUtility.promptForChoice("계좌종류를 지정해주세요 1.예금 2.적금",1,2);
        AccountType type;
        if(typeChoice == 1) type = AccountType.DEPOSIT; else type =AccountType.SAVING;

        String pawword = ConsoleUtility.promptForInput("계좌비밀번호 4자리 숫자를 입력해주세요");
        CreateAccountDto dto = new CreateAccountDto(
                pawword,
                type,
                bankId,
                employeeId,
                0,
                userId,
                type.getInterestRate()
        );
        return accountService.createAccount(dto);
    }

    @Override
    public List<Account> getAccounts(int userId) throws Exception {
        List<Account> accounts = accountService.getAccounts(userId);
        if(accounts.isEmpty()) {
            System.out.println("조회할 수 있는 계좌가 없습니다.");
        } else {
            for(int i=0; i<accounts.size(); i++) {
                System.out.print(i+1+"."+ accounts.get(i).getAccountNumber()+" ");
            }
        }
        System.out.println();
        return accounts;
    }

//    @Override
//    public Account getAccount(int accountId) throws Exception {
//        return null;
//    }

    @Override
    public void deleteAccount(int accountId) throws Exception {
        accountService.deleteAccount(accountId);
    }

    @Override
    public TransactionHistory deposit(Account account, int amount) throws Exception {
        return accountService.deposit(account, amount);
    }

    @Override
    public TransactionHistory withdraw(Account account, int amount) throws Exception {
        return accountService.withdraw(account, amount);
    }

    @Override
    public List<TransactionHistory> showTransactions(int accountId) throws Exception {
        return accountService.showTransactions(accountId);
    }
}
