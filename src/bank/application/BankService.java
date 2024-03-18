package bank.application;

import account.domain.Account;
import bank.domain.Bank;
import bank.domain.Employee;

import java.util.List;

public interface BankService {

    List<Bank> getBankList() throws Exception;
    List<Bank> findAllBanks() throws Exception;
    List<Employee> findEmployeesByBankId(int bankId) throws Exception;
    Employee getEmployee(int id) throws Exception;

    List<Account> findAccountsByEmployeeId(int id) throws Exception;
}
