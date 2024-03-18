package bank.controller;

import account.domain.Account;
import bank.domain.Bank;
import bank.domain.Employee;

import java.util.List;

public interface BankController {

    List<Bank> findAllBanks() throws Exception;
//    List<Employee> showEmployee(int bankId);
    Employee showEmployee(int bankId) throws Exception;

    List<Employee> findEmployeeByBankId(int bankId) throws Exception;

    List<Account> findAccountsByEmployeeId(int id) throws Exception;
}