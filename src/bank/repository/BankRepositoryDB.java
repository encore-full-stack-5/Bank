package bank.repository;

import account.domain.Account;
import bank.domain.Bank;
import bank.domain.Employee;

import java.util.List;

public interface BankRepositoryDB {

    Employee findEmployeeById(int employeeId) throws Exception;
    List<Bank> findAllBanks() throws Exception;
    List<Account> findAccountsByEmployeeId(int employeeId) throws Exception;
    List<Employee> findEmployeesByBankId(int bankId) throws Exception;
}
