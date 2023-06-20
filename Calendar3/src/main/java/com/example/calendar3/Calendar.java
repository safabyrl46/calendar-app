//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.calendar3;

import java.io.PrintStream;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Calendar {
    private int weekCount;
    public static LocalDate today = LocalDate.now();
    private int dayOfMonth;
    private int monthLenght;
    public LocalDate firstDayOfMonth;
    public LocalDate lastDayOfMonth;
    public LocalDate dateItterator;

    public Calendar(LocalDate day) {
        this.firstDayOfMonth = day.minusDays((long)(day.getDayOfMonth() - 1));
        this.weekCount = this.getDaysInOurCalendar() / 7;
        this.dayOfMonth = day.getDayOfMonth();
        this.monthLenght = day.lengthOfMonth();
        this.lastDayOfMonth = this.firstDayOfMonth.plusDays((long)(this.monthLenght - 1));
        this.dateItterator = this.getFirstMondayOfCalendar();
        this.getLastSundayOfCalendar();
        PrintStream var10000 = System.out;
        String var10001 = String.valueOf(today);
        var10000.println("\n\nbugun: " + var10001 + "\nayin ilk gunu: " + String.valueOf(this.firstDayOfMonth) + "\nhafta sayisi: " + this.weekCount + "\ndayofmonth" + this.dayOfMonth + "\nay kac cekiyor: " + this.monthLenght + "\nayin son gunu: " + String.valueOf(this.lastDayOfMonth) + "\n date ıtterator " + String.valueOf(this.dateItterator) + "\ntakvimin ilk pazartesi: " + String.valueOf(this.getFirstMondayOfCalendar()) + "\ntakvimin son pazari:  " + String.valueOf(this.getLastSundayOfCalendar()) + "\nget days in our claendar " + this.getDaysInOurCalendar());
    }

    public LocalDate getFirstMondayOfCalendar() {
        LocalDate firstMonday = null;
        if (this.firstDayOfMonth.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstMonday = this.firstDayOfMonth.minusDays((long)(this.firstDayOfMonth.getDayOfWeek().getValue() - 1));
        } else {
            firstMonday = this.firstDayOfMonth;
        }

        return firstMonday;
    }

    public LocalDate getLastSundayOfCalendar() {
        LocalDate lastSunday = this.firstDayOfMonth.plusMonths(1L);
        if (lastSunday.getDayOfWeek() != DayOfWeek.SUNDAY) {
            lastSunday = lastSunday.plusDays((long)(7 - lastSunday.getDayOfWeek().getValue()));
        }

        return lastSunday;
    }

    private int getDaysInOurCalendar() {
        return this.getLastSundayOfCalendar().getDayOfYear() - this.getFirstMondayOfCalendar().getDayOfYear();
    }
}
