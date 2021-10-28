package com.company;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.Temporal;

public class GameTimer {

    private Temporal start;

    public void startTimer() {
        start = LocalTime.now();
    }

    public String stopTimer() {
        try {
            Duration duration = Duration.between(start, LocalTime.now());
            javax.xml.datatype.Duration elapsed = DatatypeFactory.newInstance().newDuration(String.valueOf(duration));

            if (elapsed.getHours() > 1) {
                return "Your time was: " + elapsed.getHours() + " hours, " + elapsed.getMinutes() + " minutes and " + elapsed.getSeconds() + " seconds!";
            } else if (elapsed.getHours() == 1)
                return "Your time was: "+elapsed.getHours()+" hours, "+elapsed.getMinutes()+" minutes and "+elapsed.getSeconds()+" seconds!";
            else if (elapsed.getMinutes() > 0) {
                return "Your time was: "+elapsed.getMinutes()+" minutes and "+elapsed.getSeconds()+" seconds!";
            } else {
                return "Wow! Your time was: "+elapsed.getSeconds()+" seconds!";
            }
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
