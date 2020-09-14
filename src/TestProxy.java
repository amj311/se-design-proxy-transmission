public class TestProxy {
    static SecretTransmission transmission = new SecretTransmission();

    public static void main(String[] args) {
        assertGoodTime(WEEKDAY.MON,HOUR_12.AM_10);
        assertGoodTime(WEEKDAY.WED,HOUR_12.PM_12);
        assertBadTime(WEEKDAY.SAT, HOUR_12.AM_10);
        assertBadTime(WEEKDAY.WED, HOUR_12.AM_6);
    }

    private static void assertGoodTime(WEEKDAY day, HOUR_12 hour) {
        SecretTransmissionProxy proxy = new SecretTransmissionProxy(
                transmission, day, hour);

        try {
            System.out.println(proxy.getMessage());
            proxy.sendMessage("test message");
            System.out.println("Assertion Passed!");
        }
        catch (TransmissionException e) {
            System.err.println("Assertion Failed!");
            e.printStackTrace();
        }
    }

    private static void assertBadTime(WEEKDAY day, HOUR_12 hour) {
        SecretTransmissionProxy proxy = new SecretTransmissionProxy(
                transmission, day, hour);

        try {
            System.out.println(proxy.getMessage());
            proxy.sendMessage("test message");
            System.err.println("Assertion Failed!");
        }
        catch (TransmissionException e) {
            System.out.println("Assertion Passed!");
        }
    }
}
