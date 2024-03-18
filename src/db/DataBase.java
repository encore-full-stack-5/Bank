package db;

import account.domain.Account;
import account.domain.TransactionHistory;
import bank.domain.Bank;
import bank.domain.Employee;
import user.domain.User;

import java.util.TreeMap;

public interface DataBase {

    TreeMap<Integer, User> getUserTable();
    TreeMap<Integer, Account> getAccountTable();
    TreeMap<Integer, Bank> getBankTable();
    TreeMap<Integer, Employee> getEmployeeTable();
    TreeMap<Integer, TransactionHistory> getTransactionTable();

}
