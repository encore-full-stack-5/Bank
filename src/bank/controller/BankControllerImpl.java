package bank.controller;

import account.domain.Account;
import bank.application.BankService;
import bank.application.BankServiceImpl;
import bank.domain.Bank;
import bank.domain.Employee;

import java.util.List;

public class BankControllerImpl implements BankController {

    private final BankService bankService = BankServiceImpl.getInstance();
    private static BankControllerImpl bankController;

    public static BankController getInstance() {
        if(bankController == null) bankController = new BankControllerImpl();
        return bankController;
    }

    @Override
    public List<Bank> findAllBanks() throws Exception{
        List<Bank> bankList =bankService.findAllBanks();
        bankList.forEach(bank -> System.out.print(bank.getId()+"."+bank.getName()+" "));
        System.out.println();
        return bankList;
    }

    @Override
    public List<Employee> findEmployeeByBankId(int bankId) throws Exception {
        List<Employee> employees =bankService.findEmployeesByBankId(bankId);
        employees.forEach(employee -> System.out.print((employee.getId() - 1) % 5 + 1+"."+employee.getName()+":"+employee.getPosition()+" "));
        System.out.println();
        return employees;
    }

    @Override
    public List<Account> findAccountsByEmployeeId(int id) throws Exception {
        List<Account> accounts =bankService.findAccountsByEmployeeId(id);
        accounts.forEach(account -> System.out.print("계좌번호: "+account.getAccountNumber()+" ("
                + (account.getType().equals("DEPOSIT")?"예금":"적금")+"통장)"+"\n"));
        System.out.println("총 "+accounts.size()+"개");
        return accounts;
    }

//    @Override
//    public List<Employee> showEmployee(int bankId) {
//        return null;
//    }

    public Employee showEmployee(int id) throws Exception {
        Employee employees =bankService.getEmployee(id);
        System.out.println(employees.toString());
        return employees;
    }
}
