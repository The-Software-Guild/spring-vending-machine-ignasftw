package SpringVendingMachine.dao;

import java.time.LocalDateTime;

public class Audit {
    private final LocalDateTime timeStamp;
    private final String message;

    public Audit(String message) {
        timeStamp = LocalDateTime.now();
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }
}
