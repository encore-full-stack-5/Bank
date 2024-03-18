package bank.controller;

import bank.domain.Bank;
import bank.domain.Employee;

import java.util.List;

public interface BankController {

    List<Bank> findAllBanks() throws Exception;
    List<Employee> showEmployee(int bankId);
    List<Employee> findEmployeeByBankId(int bankId) throws Exception;
}