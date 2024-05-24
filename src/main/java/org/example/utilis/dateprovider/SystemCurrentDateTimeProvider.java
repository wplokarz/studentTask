package org.example.utilis.dateprovider;

import java.time.LocalDateTime;

public class SystemCurrentDateTimeProvider implements CurrentDateTimeProvider{
    @Override
    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
