package bank.repository;

import bank.domain.Bank;
import bank.domain.Employee;

import java.util.List;

public interface BankRepositoryDB {

    Employee findEmployeeById(int employeeId) throws Exception;
    List<Bank> findAllBanks() throws Exception;
    List<Employee> findEmployeesByBankId(int bankId) throws Exception;
}
