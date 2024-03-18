package bank.repository;

import bank.domain.Bank;
import bank.domain.Employee;

import java.util.List;

public interface BankRepositoryNoDB {

    Employee findBuEmployeeById(int employeeId) throws Exception;
    List<Bank> findAllBanks() throws Exception;
    List<Employee> findUsersByBankId(int id) throws Exception;

    List<Employee> findEmployeeById() throws Exception;

}
