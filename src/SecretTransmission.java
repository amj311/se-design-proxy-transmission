public class SecretTransmission implements Transmission {

    @Override
    public String getMessage() throws TransmissionException {
        return "This is not the transmission you're looking for.";
    }

    @Override
    public void sendMessage(String msg) throws TransmissionException {
    }

}
