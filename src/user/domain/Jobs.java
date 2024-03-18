package user.domain;

public enum Jobs {
    UNEMPLOYEED(1),
    STUDENT(2),
    PART_TIME(3),
    FULL_TIME(4);

    private final int command;

    Jobs(int command) {
        this.command = command;
    }

    public int getCommand() {
        return command;
    }

    public static Jobs fromCommand(int command) {
        for (Jobs job : Jobs.values()) {
            if (job.getCommand() == command) {
                return job;
            }
        }
        // Optional: throw an exception or return null if the command is not valid
        throw new IllegalArgumentException("Invalid command: " + command);
    }
}