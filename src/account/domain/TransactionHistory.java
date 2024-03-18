package account.domain;

import java.time.LocalDateTime;

public record TransactionHistory(
    int id,
    // 입금, 출금
    String type,
    int totalAmount,
    int accountId,
    int amount,
    LocalDateTime createdTime
) {}