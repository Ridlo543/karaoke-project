/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pbo_project1;

/**
 *
 * @author LENOVO
 */
import java.time.Duration;
import java.time.Instant;

public class TimeRemainingCalculator {

    private int duration;

    public TimeRemainingCalculator(int duration) {
        this.duration = duration;
    }

    public Duration calculateTimeRemaining() {
        return Duration.between(Instant.now(), Instant.now().plus(Duration.ofHours(duration)));
    }

    public String formatTimeRemaining() {
        Duration timeRemaining = calculateTimeRemaining();
        return String.format("%d hours, %d minutes, %d seconds",
                timeRemaining.toHoursPart(), timeRemaining.toMinutesPart(), timeRemaining.toSecondsPart());
    }

    public int getDuration() {
        return duration;
    }
}

