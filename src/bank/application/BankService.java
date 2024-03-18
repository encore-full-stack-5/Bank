package bank.application;

import bank.domain.Bank;
import bank.domain.Employee;

import java.util.List;

public interface BankService {

    List<Bank> getBankList() throws Exception;
    List<Bank> findAllBanks() throws Exception;
    List<Employee> findEmployeesByBankId(int bankId) throws Exception;
    Employee getEmployee(int id) throws Exception;

}
