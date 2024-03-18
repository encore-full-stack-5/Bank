package account.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record TransactionHistory(
    int id,
    // 입금, 출금
    String type,
    int totalAmount,
    int accountId,
    int amount,
    LocalDateTime createdTime
) {
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "거래 내역 {" +
                "\n   거래 유형: " + type + // "입금" 또는 "출금"
                "\n   총액: " + totalAmount +
                "\n   금액: " + amount +
                "\n   생성 시간: " + createdTime.format(formatter) +
                '\n' + '}';
    }
}