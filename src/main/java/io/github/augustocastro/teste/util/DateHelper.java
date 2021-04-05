package io.github.augustocastro.teste.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static LocalDate parse(String data) {
        return LocalDate.parse(data, formatter);
    }
}
