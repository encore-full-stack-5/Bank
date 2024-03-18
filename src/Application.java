import account.controller.AccountController;
import account.controller.AccountControllerImpl;
import account.domain.Account;
import account.domain.TransactionHistory;
import bank.controller.BankController;
import bank.controller.BankControllerImpl;
import bank.domain.Bank;
import bank.domain.Employee;
import common.util.ConsoleUtility;
import db.DB;
import reservation.controller.ReservationController;
import reservation.controller.ReservationControllerImpl;
import reservation.domain.Reservation;
import user.controller.UserController;
import user.controller.UserControllerImpl;
import user.domain.User;

import java.util.List;
import java.util.Scanner;

public class Application {

    private final static Scanner sc = new Scanner(System.in);
    private static final UserController userController = UserControllerImpl.getInstance();
    private static final BankController bankController = BankControllerImpl.getInstance();
    private static final AccountController accountController = AccountControllerImpl.getInstance();
    private static final ReservationController reservationController = ReservationControllerImpl.getInstance();

    private static User userState;
    private static Bank bankState;

    public static void main(String[] args) {
        DB.getInstance();
        while (true) {
            try {
                if(userState == null) start();
                else mainMenu();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void start() throws Exception {
        System.out.println("기존 유저이신가요? 1.예 2.아니오");
        int command = sc.nextInt();
        switch (command) {
            case 1 -> login();
            case 2 -> register();
        }
    }

    public static void login() throws Exception {
        userState = userController.signIn();
        ConsoleUtility.systemMessage("로그인 성공");
        mainMenu();
    }

    public static void register() throws Exception {
        userState = userController.signUp();
        ConsoleUtility.systemMessage("회원가입 성공");
        mainMenu();
    }

    public static void mainMenu() throws Exception {
        int choice = ConsoleUtility.promptForChoice("메뉴를 선택해주세요 1.유저관리 2.계좌관리 3.신용점수측정 4.은행조회",1,4);
        switch (choice) {
            case 1 -> userMenu();
            case 2 -> accountMenu();
            case 3 -> {
                int score = userController.measureCreditScore(userState);
                ConsoleUtility.systemMessage("신용점수 : " + score);
            }
            case 4 -> bankMenu();
        }
    }

    public static void userMenu() throws Exception {
        int choice = ConsoleUtility.promptForChoice("1.정보조회 2.로그아웃 3.회원탈퇴 4.비밀번호변경",1,4);
        switch (choice) {
            case 1 -> {
                User user =userController.getUser(userState.getUid());
                ConsoleUtility.systemMessage(user.toString());
            }
            case 2 -> logout();
            case 3 -> {
                userController.unResgiter(userState);
                ConsoleUtility.systemMessage("회원탈퇴에 성공했습니다.");
                logout();
            }
            case 4 -> {
                userController.changePassword(userState);
                logout();
                ConsoleUtility.systemMessage("비밀번호 변경에 성공하였습니다 다시 로그인 해주세요");
            }
        }
    }

    public static void logout() {
        userState = null;
    }
  
    public static void bankMenu() throws Exception {
        int choice = ConsoleUtility.promptForChoice("1.은행지점조회 2.방문상담예약 3.직원정보조회",1,3);
        switch (choice) {
            case 1 -> bankController.findAllBanks();
            case 2 -> makeReservation();
            case 3 -> showEmployees();
        }
        System.out.println();
    }

    public static void accountMenu() throws Exception {
        int choice = ConsoleUtility.promptForChoice("1.계좌조회 2.계좌개설 3.계좌해지 4.입금하기 5.출금하기 6.내역조회 " ,1,6);
        switch (choice) {
            case 1 -> showAccount();
            case 2 -> createAccount();
            case 3 -> deleteAccount();
            case 4 -> deposit();
            case 5 -> withdraw();
            case 6 -> showTransactions();
        }
    }

    public static void showAccount() throws Exception {
        List<Account> accounts = accountController.getAccounts(userState.getUid());
        if(accounts.isEmpty()) return;
        int showAccountChoice = ConsoleUtility.promptForChoice("위의 계좌중 조회하실 계좌를 선택해주세요",1,accounts.size());
        Account choseAccount = accounts.get(showAccountChoice - 1);
        ConsoleUtility.systemMessage(choseAccount.toString());
    }

    public static void createAccount() throws Exception {
        List<Bank> banks = bankController.findAllBanks();
        Bank bank = banks.get(ConsoleUtility.promptForChoice("위의 은행중 하나를 선택해주세요", 1, banks.size()) -1);
        List<Employee> employees = bankController.findEmployeeByBankId(bank.getId());
        Employee employee = employees.get(ConsoleUtility.promptForChoice("위의 직원중 하나를 선택해주세요", 1, employees.size()) -1);
        Account account = accountController.createAccount(userState.getUid(), bank.getId(), employee.getId());
        System.out.println("계좌개설 성공!!");
        ConsoleUtility.systemMessage(account.toString());
    }

    public static void deleteAccount() throws Exception {
        List<Bank> banks = bankController.findAllBanks();
        Bank bank = banks.get(ConsoleUtility.promptForChoice("위의 은행 중 하나를 선택해주세요", 1, banks.size()) -1);
        List<Account> accounts = accountController.getAccounts(userState.getUid()); // controller에서 계좌 번호 목록을 보여준다.
        int showAccountChoice = ConsoleUtility.promptForChoice("위의 계좌 중 해지하실 계좌를 선택해주세요",1,accounts.size());
        Account account = accounts.get(showAccountChoice - 1); // 계좌 선택
        int inputPassword = ConsoleUtility.promptForInt("계좌 비밀번호 4자리를 입력해주세요");
        account.validatePassword(inputPassword); // 계좌 비밀 번호를 인증한다.
        accountController.deleteAccount(account.getId()); // 계좌 해지를 진행한다.
        ConsoleUtility.systemMessage("계좌해지 완료되었습니다."); // 계좌 해지 완료 문구 출력
    }

    public static void deposit() throws Exception {
        List<Bank> banks = bankController.findAllBanks();
        Bank bank = banks.get(ConsoleUtility.promptForChoice("위의 은행 중 하나를 선택해주세요", 1, banks.size()) -1);
        List<Account> accounts = accountController.getAccounts(userState.getUid());
        int showAccountChoice = ConsoleUtility.promptForChoice("입금하실 계좌를 선택해주세요",1,accounts.size());
        Account account = accounts.get(showAccountChoice - 1);
        int inputPassword = ConsoleUtility.promptForInt("계좌 비밀번호 4자리를 입력해주세요");
        account.validatePassword(inputPassword); // 계좌 비밀 번호를 인증한다.
        int inputAmount = ConsoleUtility.promptForInt("입금하실 금액을 눌러주세요");
        TransactionHistory transactionHistory = accountController.deposit(account, inputAmount); // 입금 후 거래내역 가져오기
        ConsoleUtility.systemMessage("입금이 완료되었습니다. 현재 잔액은 " + transactionHistory.totalAmount() + "입니다."); // 입금 후 잔액 조회
    }

    public static void withdraw() throws Exception {
        List<Bank> banks = bankController.findAllBanks();
        Bank bank = banks.get(ConsoleUtility.promptForChoice("위의 은행 중 하나를 선택해주세요", 1, banks.size()) -1);
        List<Account> accounts = accountController.getAccounts(userState.getUid());
        int showAccountChoice = ConsoleUtility.promptForChoice("출금하실 계좌를 선택해주세요",1,accounts.size());
        Account account = accounts.get(showAccountChoice - 1);
        int inputPassword = ConsoleUtility.promptForInt("계좌 비밀번호 4자리를 입력해주세요");
        account.validatePassword(inputPassword); // 계좌 비밀 번호를 인증한다.
        int inputAmount = ConsoleUtility.promptForInt("출금하실 금액을 눌러주세요");
        TransactionHistory transactionHistory = accountController.withdraw(account, inputAmount); // 출금 후 거래내역 가져오기
        ConsoleUtility.systemMessage("출금이 완료되었습니다. 현재 잔액은 " + transactionHistory.totalAmount() + "입니다."); // 출금 후 잔액 조회
    }

    public static void showTransactions() throws Exception {
        List<Bank> banks = bankController.findAllBanks();
        Bank bank = banks.get(ConsoleUtility.promptForChoice("위의 은행 중 하나를 선택해주세요", 1, banks.size()) -1);
        List<Account> accounts = accountController.getAccounts(userState.getUid()); // controller에서 계좌 번호 목록을 보여준다.
        int showAccountChoice = ConsoleUtility.promptForChoice("위의 계좌 중 조회하실 계좌를 선택해주세요",1,accounts.size());
        Account account = accounts.get(showAccountChoice - 1); // 계좌 선택
        int inputPassword = ConsoleUtility.promptForInt("계좌 비밀번호 4자리를 입력해주세요");
        account.validatePassword(inputPassword); // 계좌 비밀 번호를 인증한다.
        List<TransactionHistory> transactionHistorys = accountController.showTransactions(account.getId());
        transactionHistorys.forEach(System.out::println);
    }

    public static void showEmployees() throws Exception {
        List<Bank> banks = bankController.findAllBanks();
        int showBankChoice = ConsoleUtility.promptForChoice("지점을 선택해주세요", 1, banks.size());
        int choseBankId = banks.get(showBankChoice - 1).getId();
        // 은행 아이디로 찾기
        List<Employee> employees = bankController.findEmployeeByBankId(choseBankId);
        int employeeChoice = ConsoleUtility.promptForChoice("정보를 조회할 직원을 선택해주세요", 1, employees.size()) - 1;
        int choseId = employees.get(employeeChoice).getId();

        Employee employee = bankController.showEmployee(choseId);
        System.out.println("\n****** " + employees.get(employeeChoice).getName() + "이/가 개설한 계좌 ******");
        List<Account> accounts = bankController.findAccountsByEmployeeId(employee.getId());
        System.out.println();
    }

    public static void makeReservation() throws Exception {
        List<Bank> banks = bankController.findAllBanks();
        int showBankChoice = ConsoleUtility.promptForChoice("위의 지점중 예약할 지점을 선택해주세요",1,banks.size());
        int choseBankId = banks.get(showBankChoice-1).getId();
        List<Reservation> reservations = reservationController.findAllReservationsById(choseBankId);
        reservationController.printAvailableTime(reservations,choseBankId,banks);
        int choseReservationTime = ConsoleUtility.promptForChoice("위의 시간중 예약할 시간을 입력해주세요",8,15);
        boolean availableTime = reservationController.isAvailableTime(choseBankId,choseReservationTime);
        if(availableTime){
            System.out.println("예약 가능한 시간입니다.");
            reservationController.createReservation(userState.getUid(), choseReservationTime,choseBankId);
            System.out.println("예약이 완료되었습니다.");
        }else {
            System.out.println("예약 불가능한 시간입니다.");
        }
    }
}