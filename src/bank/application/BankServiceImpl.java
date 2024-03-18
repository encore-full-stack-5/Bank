package bank.application;

import account.domain.Account;
import bank.domain.Bank;
import bank.domain.Employee;
import bank.repository.BankRepositoryDB;
import bank.repository.BankRepositoryImplDB;
import bank.repository.BankRepositoryNoDB;
import bank.repository.BankRepositoryImplNoDB;

import java.util.List;

public class BankServiceImpl implements BankService {

    BankRepositoryDB bankRepository = BankRepositoryImplDB.getInstance();

    static class Holder {
        static final BankService INSTANCE = new BankServiceImpl();
    }

    public static BankService getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<Bank> getBankList() {
        return null;
    }

    @Override
    public List<Employee> findEmployeesByBankId(int bankId) throws Exception{
        return bankRepository.findEmployeesByBankId(bankId);
    }
    public List<Account> findAccountsByEmployeeId(int employeeId) throws Exception{
        return bankRepository.findAccountsByEmployeeId(employeeId);
    }
    @Override
    public List<Bank> findAllBanks() throws Exception {
        return bankRepository.findAllBanks();
    }

    @Override
    public Employee getEmployee(int id) throws Exception {
        return bankRepository.findEmployeeById(id);    }
}
