package user.application;

import user.domain.Income;
import user.domain.Jobs;

public record SignUpRequestDto(
    String id,
    String passwd,
    String name,
    String address,
    String phoneNumber,
    String email,
    Jobs job,
    Income income,
    int asset,
    String birth
) {
}
