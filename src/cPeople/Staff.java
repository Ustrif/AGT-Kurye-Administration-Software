package cPeople;

import utils.Save;

public class Staff extends Worker {
    private final String usercode;
    private String name;
    private String surname;
    private int tel;
    private int tc;
    private String password;

    public Staff(String usercode, String name, String surname,int tel, int tc, String password){
        this.usercode = usercode;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        this.tc = tc;
        this.password = password;
    }

    public void staffSaver(){
        Save saver = new Save();
        saver.addWorker("p",usercode,name,surname,tel,tc,password);
        saver.logSaver(usercode + " isimli personel eklendi.");
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
}
