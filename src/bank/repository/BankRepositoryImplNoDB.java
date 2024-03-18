package bank.repository;

import bank.domain.Bank;
import bank.domain.Employee;
import db.DataBase;
import db.DataBaseImpl;

import java.util.List;
import java.util.TreeMap;

public class BankRepositoryImplNoDB implements BankRepositoryNoDB {

    private final DataBase db = DataBaseImpl.getInstance();
    private final TreeMap<Integer,Bank> bankTable = db.getBankTable();
    private final TreeMap<Integer,Employee> employeeTable = db.getEmployeeTable();

    static class Holder{
        static final BankRepositoryNoDB INSTANCE = new BankRepositoryImplNoDB();
    }

    public static BankRepositoryNoDB getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Employee findBuEmployeeById(int employeeId) throws Exception {
        Employee employee = employeeTable.get(employeeId);
        if(employee == null) {
            throw new Exception("해당하는 직원이 존재하지 않습니다.");
        }
        return employee;
    }

    @Override
    public List<Bank> findAllBanks() throws Exception {
        TreeMap<Integer,Bank> bank = bankTable;
        if(bank == null) {
            throw new Exception("해당하는 은행이 존재하지 않습니다.");
        }
        return bankTable.values().stream().toList();
    }

    @Override
    public List<Employee> findUsersByBankId(int id) throws Exception {
        TreeMap<Integer,Employee> employee = employeeTable;
        if(employee == null) {
            throw new Exception("해당 지점에 속한 직원이 존재하지 않습니다.");
        }
        return employeeTable.values().stream().filter(it->it.getBankId()==id).toList();
    }

    @Override
    public List<Employee> findEmployeeById() throws Exception {
        if(employeeTable == null) {
            throw new Exception("직원이 존재하지 않습니다.");
        }
        return employeeTable.values().stream().toList();
    }
}
