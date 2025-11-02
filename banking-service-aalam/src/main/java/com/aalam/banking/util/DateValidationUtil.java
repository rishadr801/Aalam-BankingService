package com.aalam.banking.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Utility class for validating and generating date ranges for month/year-based queries.
 */
public class DateValidationUtil {

    private DateValidationUtil() {
        // Prevent instantiation
    }

    public static LocalDate[] getDateRange(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }

        try {
            YearMonth ym = YearMonth.of(year, month);
            return new LocalDate[]{ym.atDay(1), ym.atEndOfMonth()};
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("Invalid year/month combination: " + year + "-" + month);
        }
    }

    public static String formatYearMonth(int year, int month) {
        return String.format("%04d-%02d", year, month);
    }
}
