package user.domain;

public enum Income {
    ZERO(1),
    OVER_200(2),
    OVER_400(3),
    OVER_600(4);

    private final int command;

    Income(int command) {
        this.command = command;
    }

    public int getCommand() {
        return command;
    }

    public static Income fromCommand(int command) {
        for (Income income : Income.values()) {
            if (income.getCommand() == command) {
                return income;
            }
        }
        // Optional: throw an exception or return null if the command is not valid
        throw new IllegalArgumentException("Invalid command: " + command);
    }
}