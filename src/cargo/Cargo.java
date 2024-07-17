package cargo;
public class Cargo {
    private String cargoNumber;
    private String senderName;
    private String senderSurname;
    private String senderAdress;
    private String receiverName;
    private String receiverSurname;
    private String receiverPhone;
    private String receiverAdress;
    private int cargoSize;

    public Cargo(String cargoNumber, int cargoSize, String senderName, String senderSurname,
                 String senderAdress, String receiverName, String receiverSurname,
                 String receiverPhone, String receiverAdress)
    {
        setCargoNumber(cargoNumber);
        setCargoSize(cargoSize);
        setSenderName(senderName);
        setSenderSurname(senderSurname);
        setSenderAdress(senderAdress);
        setReceiverName(receiverName);
        setReceiverSurname(receiverSurname);
        setReceiverPhone(receiverPhone);
        setReceiverAdress(receiverAdress);

    }

    public String getCargo_number(){
        return cargoNumber;
    }
    public int getCargo_size(){
        return cargoSize;
    }
    public String getSender_name(){
        return senderName;
    }
    public String getSender_surname(){
        return senderSurname;
    }
    public String getSender_adress(){
        return senderAdress;
    }
    public String getReceiver_name(){
        return receiverName;
    }
    public String getReceiver_surname(){
        return receiverSurname;
    }
    public String getReceiver_phone(){
        return receiverPhone;
    }
    public String getReceiver_adress(){
        return receiverAdress;
    }

    public void setCargoNumber(String cargoNumber) {
        this.cargoNumber = cargoNumber;
    }
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
    public void setSenderAdress(String senderAdress) {
        this.senderAdress = senderAdress;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    public void setSenderSurname(String senderSurname) {
        this.senderSurname = senderSurname;
    }
    public void setReceiverSurname(String receiverSurname) {
        this.receiverSurname = receiverSurname;
    }
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
    public void setReceiverAdress(String receiverAdress) {
        this.receiverAdress = receiverAdress;
    }
    public void setCargoSize(int cargoSize) {
        this.cargoSize = cargoSize;
    }
}
