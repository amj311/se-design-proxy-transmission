import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SecretTransmissionProxy implements Transmission {
    private Transmission transmission;
    private boolean testing = false;
    private WEEKDAY testWeekday;
    private HOUR_12 testHour;

    private WEEKDAY[] daysArray = {WEEKDAY.MON, WEEKDAY.WED, WEEKDAY.FRI};
    private HOUR_12[] hoursArray = {HOUR_12.AM_9, HOUR_12.AM_10, HOUR_12.AM_11, HOUR_12.PM_12};
    private ArrayList<WEEKDAY> validDays = new ArrayList<WEEKDAY>(Arrays.asList(daysArray));
    private ArrayList<HOUR_12> validHours = new ArrayList<HOUR_12>(Arrays.asList(hoursArray));

    SecretTransmissionProxy(Transmission transmission) {
        this.transmission = transmission;
    }
    SecretTransmissionProxy(Transmission transmission, WEEKDAY testWeekday, HOUR_12 testHour) {
        this.transmission = transmission;
        this.testing = true;
        this.testWeekday = testWeekday;
        this.testHour = testHour;
    }

    @Override
    public String getMessage() throws TransmissionException {
        blockIfInvalidTime();
        return transmission.getMessage();
    }

    @Override
    public void sendMessage(String msg) throws TransmissionException {
        blockIfInvalidTime();
        transmission.sendMessage(msg);
    }

    private WEEKDAY getWeekday() {
        if (testing) return this.testWeekday;
        else return WEEKDAY.THU;
    }
    private HOUR_12 getHour() {
        if (testing) return this.testHour;
        else return HOUR_12.PM_7;
    }

    private void blockIfInvalidTime() throws TransmissionException {
        if (!(validHours.contains(getHour()) && validDays.contains(getWeekday())))
            throw new TransmissionException("Sorry imperial bucket-heads, you're too late!");
    }
}
