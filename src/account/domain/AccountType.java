package account.domain;

import user.domain.Jobs;

public enum AccountType {
    DEPOSIT(5.5f),
    SAVING(7.5f);

    private final float interestRate;

    AccountType(float interestRate) {
        this.interestRate = interestRate;
    }

    public float getInterestRate() {
        return interestRate;
    }

}
