package com.company.clients.util;

import java.time.LocalDate;

public class DateUtils {
    private static final int EXPECTED_LIFE_YEARS = 80;

    public static LocalDate estimateLifeExpectancyDate(LocalDate birthDate) {
        if (birthDate == null) return null;
        return birthDate.plusYears(EXPECTED_LIFE_YEARS);
    }
}

