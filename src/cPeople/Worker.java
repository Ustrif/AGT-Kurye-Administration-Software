package cPeople;

public abstract class Worker {
    protected String usercode;
    protected String name;
    protected String surname;
    protected int telNo;
    protected int tcNO;
    protected String pass;

    public abstract String getUsercode();

    public abstract String getName();
    public abstract void setName(String name);

    public abstract String getSurname();
    public abstract void setSurname(String surname);

    public abstract int getTelNo();
    public abstract void setTelNo(int tel);

    public abstract int getTcNO();
    public abstract void setTcNO(int tc);

    public abstract String getPass();
    public abstract void setPass(String password);
}