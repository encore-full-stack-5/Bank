package account.application;

import account.domain.AccountType;

public record CreateAccountDto(
        String password,
        AccountType type,
        int bankId,
        int employeeId,
        int balance,
        int userId,
        float interestRate
) {

}
