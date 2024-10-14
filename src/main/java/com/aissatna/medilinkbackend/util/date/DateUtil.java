package com.aissatna.medilinkbackend.util.date;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {

    public static final String PARIS_TIME_ZONE = "Europe/Paris";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public static String formatLocalDateToString(LocalDate localDate, String outputPattern, String timeZone ) {
        return DateTimeFormatter.ofPattern (outputPattern).withZone(ZoneId.of(timeZone)).format(localDate);
    }

    public static LocalDate parseLocalDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern(DD_MM_YYYY));
    }

}
