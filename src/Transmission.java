public interface Transmission {
    public String getMessage() throws TransmissionException;
    public void sendMessage(String msg) throws TransmissionException;
}
