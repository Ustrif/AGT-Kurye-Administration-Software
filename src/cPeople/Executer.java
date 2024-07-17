package cPeople;
import utils.*;
public class Executer extends Worker {
    private String usercode;
    private String name;
    private String surname;
    private int tel;
    private int tc;
    private String password;

    public Executer(String usercode, String name, String surname,int tel, int tc, String password){
        setUsercode(usercode);
        setName(name);
        setSurname(surname);
        setTelNo(tel);
        setTcNO(tc);
        setPass(password);
    }

    public void executerSaver(){
        Save saver = new Save();
        saver.addWorker("e",getUsercode(),getName(),getSurname(),getTelNo(),getTcNO(),getPass());
        saver.logSaver(getUsercode() + " kullanıcı adlı yönetici eklendi.");
    }

    @Override
    public String getUsercode(){
        return usercode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public int getTelNo() {
        return tel;
    }

    @Override
    public void setTelNo(int tel) {
        this.tel = tel;
    }

    @Override
    public int getTcNO() {
        return tc;
    }

    @Override
    public void setTcNO(int tc) {
        this.tc = tc;
    }

    @Override
    public String getPass() {
        return password;
    }

    @Override
    public void setPass(String password) {
        this.password = password;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
}
