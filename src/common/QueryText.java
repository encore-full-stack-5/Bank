package common;

public class QueryText {

    public static final String CREATE_ACCOUNT_QUERY = "CREATE TABLE IF NOT EXISTS Account (\n" +
            "    id INT AUTO_INCREMENT,\n" +
            "    accountNumber VARCHAR(255) NOT NULL,\n" +
            "    balance INT,\n" +
            "    type ENUM('DEPOSIT', 'SAVING'), -- AcouuntType 열거형을 ENUM으로 변환\n" +
            "    bankId INT,\n" +
            "    userId INT,\n" +
            "    employeeId INT,\n" +
            "    password VARCHAR(255),\n" +
            "    interestRate FLOAT,\n" +
            "    createdTime DATETIME,\n" +
            "    PRIMARY KEY (id)\n" +
            ")";

    public static final String CREATE_BANK_QUERY = "CREATE TABLE IF NOT EXISTS Bank (\n" +
            "    id INT,\n" +
            "    name VARCHAR(255) NOT NULL,\n" +
            "    address VARCHAR(255),\n" +
            "    phoneNumber VARCHAR(20),\n" +
            "    openTime TIME, -- LocalDateTime을 시간 정보만 포함한다고 가정\n" +
            "    closeTime TIME, -- LocalDateTime을 시간 정보만 포함한다고 가정\n" +
            "    createdTime DATETIME,\n" +
            "    PRIMARY KEY (id)\n" +
            ")";

    public static final String CRATE_RESERVATION_QUERY = "CREATE TABLE IF NOT EXISTS Reservation (\n" +
            "    id INT AUTO_INCREMENT,\n" +
            "    reservationTime INT NOT NULL,\n" +
            "    bankId INT,\n" +
            "    userId INT,\n" +
            "    PRIMARY KEY (id)\n" +
            ")";

    public static final String CREATE_EMPLOYEE_QUERY = "CREATE TABLE IF NOT EXISTS Employee (\n" +
            "    id INT AUTO_INCREMENT,\n" +
            "    name VARCHAR(255) NOT NULL,\n" +
            "    position VARCHAR(255),\n" +
            "    phoneNumber VARCHAR(20),\n" +
            "    email VARCHAR(255),\n" +
            "    bankId INT,\n" +
            "    createdTime DATETIME,\n" +
            "    PRIMARY KEY (id)\n" +
            ")";

    public static final String CRATE_TRANSACTION_HISTORY_QUERY = "CREATE TABLE IF NOT EXISTS TransactionHistory (\n" +
            "    id INT AUTO_INCREMENT,\n" +
            "    type VARCHAR(255) NOT NULL,\n" +
            "    totalAmount INT,\n" +
            "    accountId INT,\n" +
            "    amount INT,\n" +
            "    createdTime DATETIME,\n" +
            "    PRIMARY KEY (id)\n" +
            ")";

    public static final String CREATE_USER_QUERY = "CREATE TABLE IF NOT EXISTS User (\n" +
            "    uid INT AUTO_INCREMENT,\n" +
            "    loginId VARCHAR(255) UNIQUE NOT NULL,\n" +
            "    password VARCHAR(255) NOT NULL,\n" +
            "    name VARCHAR(255),\n" +
            "    address VARCHAR(255),\n" +
            "    phoneNumber VARCHAR(20),\n" +
            "    email VARCHAR(255),\n" +
            "    job ENUM('UNEMPLOYEED', 'STUDENT', 'PART_TIME', 'FULL_TIME'),\n" +
            "    income ENUM('ZERO', 'OVER_200', 'OVER_400', 'OVER_600'),\n" +
            "    asset INT,\n" +
            "    birth DATE,\n" +
            "    createdTime DATETIME,\n" +
            "    deleteTime DATETIME,\n" +
            "    PRIMARY KEY (uid)\n" +
            ")";


    public static final String INSERT_RESERVATION_DUMMY = "INSERT IGNORE INTO Reservation\n" +
            " (id, reservationTime, bankId, userId)\n" +
            "VALUES \n" +
            "    (1, 10, 1, 1),\n" +
            "    (2, 13, 2, 2),\n" +
            "    (3, 14, 3, 3),\n" +
            "    (4, 14, 4, 4),\n" +
            "    (5, 15, 5, 5),\n" +
            "    (6, 12, 6, 6),\n" +
            "    (7, 9, 7, 7),\n" +
            "    (8, 10, 8, 8),\n" +
            "    (9, 11, 9, 9),\n" +
            "    (10, 14, 10, 10)";

    public static final String INSERT_USER_DUMMY = "INSERT IGNORE INTO User\n" +
            "  (uid, loginId, password, name, address, phoneNumber, email, job, income, asset, birth, createdTime )\n" +
            "VALUES \n" +
            "   (1, 'test', '12345a', '테스트', '성남', '01012345678', 'test@gmail.com', 'FULL_TIME', 'OVER_400', 1000, '1996-01-01', '2024-03-14 00:00:00'),\n" +
            "   (2, 'user2', 'pass2', '김사랑', '서울', '01023456789', 'user2@example.com', 'FULL_TIME', 'OVER_400', 500, '1985-05-05', '2024-03-14 00:00:00'),\n" +
            "   (3, 'user3', 'pass3', '이몽룡', '부산', '01034567890', 'user3@example.com', 'STUDENT', 'OVER_400', 300, '1993-09-19', '2024-03-14 00:00:00'),\n" +
            "   (4, 'user4', 'pass4', '성춘향', '대구', '01045678901', 'user4@example.com', 'STUDENT', 'OVER_400', 800, '1990-12-12', '2024-03-14 00:00:00'),\n" +
            "   (5, 'user5', 'pass5', '홍길동', '인천', '01056789012', 'user5@example.com', 'FULL_TIME', 'OVER_400', 1200, '1982-02-22', '2024-03-14 00:00:00'),\n" +
            "   (6, 'user6', 'pass6', '장보고', '광주', '01067890123', 'user6@example.com', 'PART_TIME', 'OVER_400', 700, '1975-07-07', '2024-03-14 00:00:00'),\n" +
            "   (7, 'user7', 'pass7', '이순신', '울산', '01078901234', 'user7@example.com', 'STUDENT', 'OVER_400', 950, '1964-04-28', '2024-03-14 00:00:00'),\n" +
            "   (8, 'user8', 'pass8', '강감찬', '대전', '01089012345', 'user8@example.com', 'STUDENT', 'OVER_400', 200, '2000-08-15', '2024-03-14 00:00:00'),\n" +
            "   (9, 'user9', 'pass9', '을지문덕', '제주', '01090123456', 'user9@example.com', 'FULL_TIME', 'OVER_400', 1100, '1978-11-11', '2024-03-14 00:00:00')";

    public static final String INSERT_BANK_DUMMY = "INSERT IGNORE INTO Bank (id, name, address, phoneNumber, openTime, closeTime, createdTime)\n" +
            "VALUES \n" +
            "    (1, '부산진구지점', '부산시 부산진구', '051-000-0000', '09:00:00', '16:00:00', '2024-03-14 00:00:00'),\n" +
            "    (2, '중구지점', '대구시 중구', '053-001-0001', '09:00:00', '16:00:00', '2024-03-14 00:00:00'),\n" +
            "    (3, '유성구지점', '대전시 유성구', '042-002-0002', '09:00:00', '16:00:00', '2024-03-14 00:00:00'),\n" +
            "    (4, '남구지점', '광주시 남구', '062-003-0003', '09:00:00', '16:00:00', '2024-03-14 00:00:00'),\n" +
            "    (5, '연수구지점', '인천시 연수구', '032-004-0004', '09:00:00', '16:00:00', '2024-03-14 00:00:00'),\n" +
            "    (6, '수성구지점', '대구시 수성구', '053-005-0005', '09:00:00', '16:00:00', '2024-03-14 00:00:00'),\n" +
            "    (7, '성산구지점', '창원시 성산구', '055-006-0006', '09:00:00', '16:00:00', '2024-03-14 00:00:00'),\n" +
            "    (8, '덕진구지점', '전주시 덕진구', '063-007-0007', '09:00:00', '16:00:00', '2024-03-14 00:00:00'),\n" +
            "    (9, '해운대구지점', '부산시 해운대구', '051-008-0008', '09:00:00', '16:00:00', '2024-03-14 00:00:00'),\n" +
            "    (10, '서귀포지점', '제주특별자치도 서귀포시', '064-009-0009', '09:00:00', '16:00:00', '2024-03-14 00:00:00')";

    public static final String INSERT_EMPLOYEE_DUMMY = "INSERT IGNORE INTO Employee (id, name, position, phoneNumber, email, bankId, createdTime)\n" +
            "VALUES \n" +
            "(1, '김은행', '지점장', '010-0000-0001', 'manager1@bank1.com', 1, '2024-03-14 00:00:00'), " +
            "(2, '이은행', '차장', '010-0000-0002', 'deputy1@bank1.com', 1, '2024-03-14 00:00:00'), " +
            "(3, '박은행', '과장', '010-0000-0003', 'manager2@bank1.com', 1, '2024-03-14 00:00:00'), " +
            "(4, '최은행', '대리', '010-0000-0004', 'assistant1@bank1.com', 1, '2024-03-14 00:00:00'), " +
            "(5, '정은행', '사원', '010-0000-0005', 'employee1@bank1.com', 1, '2024-03-14 00:00:00'), " +
            "(6, '김신한', '지점장', '010-0001-0001', 'manager1@bank2.com', 2, '2024-03-14 00:00:00'), " +
            "(7, '이신한', '차장', '010-0001-0002', 'deputy1@bank2.com', 2, '2024-03-14 00:00:00'), " +
            "(8, '박신한', '과장', '010-0001-0003', 'manager2@bank2.com', 2, '2024-03-14 00:00:00'), " +
            "(9, '최신한', '대리', '010-0001-0004', 'assistant1@bank2.com', 2, '2024-03-14 00:00:00'), " +
            "(10, '정신한', '사원', '010-0001-0005', 'employee1@bank2.com', 2, '2024-03-14 00:00:00'), " +
            "(11, '김우리', '지점장', '010-0002-0001', 'manager1@bank3.com', 3, '2024-03-14 00:00:00'), " +
            "(12, '이우리', '차장', '010-0002-0002', 'deputy1@bank3.com', 3, '2024-03-14 00:00:00'), " +
            "(13, '박우리', '과장', '010-0002-0003', 'manager2@bank3.com', 3, '2024-03-14 00:00:00'), " +
            "(14, '최우리', '대리', '010-0002-0004', 'assistant1@bank3.com', 3, '2024-03-14 00:00:00'), " +
            "(15, '정우리', '사원', '010-0002-0005', 'employee1@bank3.com', 3, '2024-03-14 00:00:00'), " +
            "(16, '김하나', '지점장', '010-0003-0001', 'manager1@bank4.com', 4, '2024-03-14 00:00:00'), " +
            "(17, '이하나', '차장', '010-0003-0002', 'deputy1@bank4.com', 4, '2024-03-14 00:00:00'), " +
            "(18, '박하나', '과장', '010-0003-0003', 'manager2@bank4.com', 4, '2024-03-14 00:00:00'), " +
            "(19, '최하나', '대리', '010-0003-0004', 'assistant1@bank4.com', 4, '2024-03-14 00:00:00'), " +
            "(20, '정하나', '사원', '010-0003-0005', 'employee1@bank4.com', 4, '2024-03-14 00:00:00'), " +
            "(21, '김농협', '지점장', '010-0004-0001', 'manager1@bank5.com', 5, '2024-03-14 00:00:00'), " +
            "(22, '이농협', '차장', '010-0004-0002', 'deputy1@bank5.com', 5, '2024-03-14 00:00:00'), " +
            "(23, '박농협', '과장', '010-0004-0003', 'manager2@bank5.com', 5, '2024-03-14 00:00:00'), " +
            "(24, '최농협', '대리', '010-0004-0004', 'assistant1@bank5.com', 5, '2024-03-14 00:00:00'), " +
            "(25, '정농협', '사원', '010-0004-0005', 'employee1@bank5.com', 5, '2024-03-14 00:00:00'), " +
            "(26, '김기업', '지점장', '010-0005-0001', 'manager1@bank6.com', 6, '2024-03-14 00:00:00'), " +
            "(27, '이기업', '차장', '010-0005-0002', 'deputy1@bank6.com', 6, '2024-03-14 00:00:00'), " +
            "(28, '박기업', '과장', '010-0005-0003', 'manager2@bank6.com', 6, '2024-03-14 00:00:00'), " +
            "(29, '최기업', '대리', '010-0005-0004', 'assistant1@bank6.com', 6, '2024-03-14 00:00:00'), " +
            "(30, '정기업', '사원', '010-0005-0005', 'employee1@bank6.com', 6, '2024-03-14 00:00:00'), " +
            "(31, '김수협', '지점장', '010-0006-0001', 'manager1@bank7.com', 7, '2024-03-14 00:00:00'), " +
            "(32, '이수협', '차장', '010-0006-0002', 'deputy1@bank7.com', 7, '2024-03-14 00:00:00'), " +
            "(33, '박수협', '과장', '010-0006-0003', 'manager2@bank7.com', 7, '2024-03-14 00:00:00'), " +
            "(34, '최수협', '대리', '010-0006-0004', 'assistant1@bank7.com', 7, '2024-03-14 00:00:00'), " +
            "(35, '정수협', '사원', '010-0006-0005', 'employee1@bank7.com', 7, '2024-03-14 00:00:00'), " +
            "(36, '김제일', '지점장', '010-0007-0001', 'manager1@bank8.com', 8, '2024-03-14 00:00:00'), " +
            "(37, '이제일', '차장', '010-0007-0002', 'deputy1@bank8.com', 8, '2024-03-14 00:00:00'), " +
            "(38, '박제일', '과장', '010-0007-0003', 'manager2@bank8.com', 8, '2024-03-14 00:00:00'), " +
            "(39, '최제일', '대리', '010-0007-0004', 'assistant1@bank8.com', 8, '2024-03-14 00:00:00'), " +
            "(40, '정제일', '사원', '010-0007-0005', 'employee1@bank8.com', 8, '2024-03-14 00:00:00'), " +
            "(41, '김씨티', '지점장', '010-0008-0001', 'manager1@bank9.com', 9, '2024-03-14 00:00:00'), " +
            "(42, '이씨티', '차장', '010-0008-0002', 'deputy1@bank9.com', 9, '2024-03-14 00:00:00'), " +
            "(43, '박씨티', '과장', '010-0008-0003', 'manager2@bank9.com', 9, '2024-03-14 00:00:00'), " +
            "(44, '최씨티', '대리', '010-0008-0004', 'assistant1@bank9.com', 9, '2024-03-14 00:00:00'), " +
            "(45, '정씨티', '사원', '010-0008-0005', 'employee1@bank9.com', 9, '2024-03-14 00:00:00'), " +
            "(46, '김카카오', '지점장', '010-0009-0001', 'manager1@bank10.com', 10, '2024-03-14 00:00:00'), " +
            "(47, '이카카오', '차장', '010-0009-0002', 'deputy1@bank10.com', 10, '2024-03-14 00:00:00'), " +
            "(48, '박카카오', '과장', '010-0009-0003', 'manager2@bank10.com', 10, '2024-03-14 00:00:00'), " +
            "(49, '최카카오', '대리', '010-0009-0004', 'assistant1@bank10.com', 10, '2024-03-14 00:00:00'), " +
            "(50, '정카카오', '사원', '010-0009-0005', 'employee1@bank10.com', 10, '2024-03-14 00:00:00')";

    public static final String INSERT_ACCOUNT_DUMMY = "INSERT IGNORE INTO Account (id, accountNumber, balance, type, bankId, userId, employeeId, password, interestRate, createdTime)\n" +
            "VALUES ('1', '0-000000-0', 100000, 'DEPOSIT', 1, 1, 1, '1234', 0.05, '2024-03-14 00:00:00')";

}
