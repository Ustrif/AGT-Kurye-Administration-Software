package pages;

import cPeople.Staff;
import utils.Infos;
import cPeople.Executer;
import utils.Save;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;


public class ExecuterorStaffProcess extends JFrame {
    Container mainContainer;
    JButton addButton;
    JButton editButton;
    JButton deleteButton;
    JButton refreshButton;
    JButton backButton;
    JPanel forTablePanel;
    JPanel forButtonsPanel;
    JTable executerList;
    DefaultTableModel defaultmodel;
    JScrollPane scrollTable;
    private String[][] dataArray;
    JFrame frame = this;
    int who;
    public ExecuterorStaffProcess(int who){
        this.who = who;
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        String[] columnNames = {"KULLANICI NO","ADI", "SOYADI", "TELEFON", "TC NUMARASI","PAROLA" };

        dataAdder();
        defaultmodel = new DefaultTableModel(dataArray, columnNames);
        executerList = new JTable(defaultmodel);
        executerList.setDefaultEditor(Object.class,null);
        forTablePanel = new JPanel();
        scrollTable = new JScrollPane(executerList);
        scrollTable.setPreferredSize(new Dimension(550, 320));
        scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        forButtonsPanel = new JPanel();

        String[] adText = {"YÖNETİCİ EKLE", "PERSONEL EKLE"};
        addButton = new JButton(adText[who]);
        editButton = new JButton("SEÇİLİYİ DÜZENLE");
        deleteButton = new JButton("SEÇİLİYİ SİL");
        refreshButton = new JButton("TABLOYU YENİLE");
        backButton = new JButton("MENÜ");

        forTablePanel.add(scrollTable,BorderLayout.CENTER);
        forButtonsPanel.add(addButton);
        forButtonsPanel.add(editButton);
        forButtonsPanel.add(deleteButton);
        forButtonsPanel.add(refreshButton);
        forButtonsPanel.add(backButton);

        mainContainer.add(forTablePanel);
        mainContainer.add(forButtonsPanel);

        addButton.addActionListener(e -> {
            setVisible(false);
            new AddExecuterPage(frame, who);
        });

        backButton.addActionListener(e -> {
            setVisible(false);
            new OptionsforExecuter();
        });

        refreshButton.addActionListener(e ->{
            setVisible(false);
            new ExecuterorStaffProcess(who);
        });

        deleteButton.addActionListener(e ->{

            if(executerList.getSelectedRow() == -1)
            {
                ErrorPage ag = new Error();
                ag.editMessage("Seçili satır olmadığı için silinmedi.");
                ag.mainScreen();
            }
            else
            {
                String[] yesNoText = {"Yönetici kaldırılsın mı?", "Personel kaldırılsın mı?"};
                int dialogResult = JOptionPane.showOptionDialog (this,
                        yesNoText[who], "Emin misiniz?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new String[]{"EVET", "HAYIR"}, null);
                if(dialogResult == JOptionPane.YES_OPTION){
                    Save saver = new Save();
                    saver.removeWorker(executerList.getSelectedRow(),who);
                    setVisible(false);
                    new ExecuterorStaffProcess(who);
                }
            }
        });

        editButton.addActionListener(e ->{

            if(executerList.getSelectedRow() == -1)
            {
                ErrorPage ag = new Error();
                ag.editMessage("Düzenleme için satır seçilmelidir.");
                ag.mainScreen();
            }
            else
            {
                setVisible(false);
                new EditExecuterPage(this, executerList.getSelectedRow(),dataArray[executerList.getSelectedRow()],who);
            }


        });

        String[] whoDo = {"YÖNETİCİ EYLEMLERİ", "PERSONEL EYLEMLERİ"};
        setTitle(whoDo[who]);
        setResizable(false);
        setLocation(500,170);
        setSize(625,440);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void dataAdder(){
        Infos at = new Infos();
        String[] textFile = {"executers", "personels"};
        ArrayList<String[]> list = at.staffInfoFetcher(textFile[who]);
        dataArray = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            dataArray[i] = list.get(i);
        }
    }
}

class AddExecuterPage extends JFrame{
    JLabel info;
    JLabel usercode;
    JLabel name;
    JLabel surname;
    JLabel phoneNumber;
    JLabel tcNo;
    JLabel password;

    JTextField usercodeField;
    JTextField nameField;
    JTextField surnameField;
    JTextField phoneField;
    JTextField tcField;
    JTextField passwordField;

    JButton addButton;
    JButton backButton;

    Container container;
    JPanel infoPanel;
    JPanel dataPanel;
    JPanel buttonPanel;

    JFrame backframe;
    int who;
    public AddExecuterPage(JFrame backframe, int who){
        super();
        this.who = who;
        this.backframe = backframe;
        container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dataPanel = new JPanel(new GridLayout(6, 2, 3,3));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        String[] infos = {"YÖNETİCİNİN BİLGİLERİNİ YAZINIZ.","PERSONELİN BİLGİLERİNİ YAZINIZ."};
        info = new JLabel(infos[who]);

        usercode = new JLabel("Kullanıcı Adı:");
        name = new JLabel("Adı:");
        surname = new JLabel("Soyadı:");
        phoneNumber = new JLabel("Telefon Numarası:");
        tcNo = new JLabel("T.C. Numarası:");
        password = new JLabel("Parolası:");
        usercodeField = new JTextField();
        nameField = new JTextField();
        surnameField = new JTextField();
        phoneField = new JTextField();
        tcField = new JTextField();
        passwordField = new JTextField();
        addButton = new JButton("EKLE");
        backButton = new JButton("MENÜ");

        infoPanel.add(info);
        dataPanel.add(usercode);
        dataPanel.add(usercodeField);
        dataPanel.add(name);
        dataPanel.add(nameField);
        dataPanel.add(surname);
        dataPanel.add(surnameField);
        dataPanel.add(phoneNumber);
        dataPanel.add(phoneField);
        dataPanel.add(tcNo);
        dataPanel.add(tcField);
        dataPanel.add(password);
        dataPanel.add(passwordField);
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);

        container.add(infoPanel);
        container.add(dataPanel);
        container.add(buttonPanel);

        backButton.addActionListener(e -> {
            setVisible(false);
            backframe.setVisible(true);
        });

        addButton.addActionListener(e ->{
            if(usercodeField.getText().isEmpty() || nameField.getText().isEmpty() ||
                    surnameField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                    tcField.getText().isEmpty() || passwordField.getText().isEmpty())
            {
                ErrorPage ag = new Error();
                ag.editMessage("Eksik veri veya tamamen boş.");
                ag.mainScreen();
            }
            else {
                Infos checkUser = new Infos();
                boolean found = false;
                String[] whoText = {"executers","personels"};
                for (String user : checkUser.getKnameList(whoText[who]+".txt")) {
                    if (user.equals(usercodeField.getText()))
                        found = true;
                }
                if (!found) {
                    if(who == 0){
                        Executer myex = new Executer(usercodeField.getText(), nameField.getText(),
                                surnameField.getText(), Integer.parseInt(phoneField.getText()),
                                Integer.parseInt(tcField.getText()), passwordField.getText());
                        myex.executerSaver();
                    }
                    else{
                        Staff myex = new Staff(usercodeField.getText(), nameField.getText(),
                                surnameField.getText(), Integer.parseInt(phoneField.getText()),
                                Integer.parseInt(tcField.getText()), passwordField.getText());
                        myex.staffSaver();
                    }
                    setVisible(false);
                    new ExecuterorStaffProcess(who);
                } else {
                    ErrorPage ag = new Error();
                    ag.editMessage("Bu kullanıcı zaten mevcut.");
                    ag.mainScreen();
                }
            }
        });
        String[] titleName = {"YÖNETİCİ EKLEME SAYFASI", "PERSONEL EKLEME SAYFASI"};
        setVisible(true);
        setResizable(false);
        setLocation(500,170);
        setSize(360,290);
        setTitle(titleName[who]);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

class EditExecuterPage extends JFrame{
    JLabel info;

    JLabel usercode;
    JLabel name;
    JLabel surname;
    JLabel phoneNumber;
    JLabel tcNo;
    JLabel password;

    JTextField usercodeField;
    JTextField nameField;
    JTextField surnameField;
    JTextField phoneField;
    JTextField tcField;
    JTextField passwordField;

    JButton addButton;
    JButton backButton;

    Container container;
    JPanel infoPanel;
    JPanel dataPanel;
    JPanel buttonPanel;

    JFrame backframe;
    int whosec;
    public EditExecuterPage(JFrame backframe,int who,String[] datas, int whosec){
        super();
        this.whosec = whosec;
        this.backframe = backframe;
        container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dataPanel = new JPanel(new GridLayout(6, 2, 3,3));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        String[] infos = {"YÖNETİCİNİN BİLGİLERİNİ YAZINIZ.","PERSONELİN BİLGİLERİNİ YAZINIZ."};
        info = new JLabel(infos[whosec]);

        usercode = new JLabel("Kullanıcı Adı:");
        name = new JLabel("Adı:");
        surname = new JLabel("Soyadı:");
        phoneNumber = new JLabel("Telefon Numarası:");
        tcNo = new JLabel("T.C. Numarası:");
        password = new JLabel("Parolası:");
        usercodeField = new JTextField(datas[0]);
        nameField = new JTextField(datas[1]);
        surnameField = new JTextField(datas[2]);
        phoneField = new JTextField(datas[3]);
        tcField = new JTextField(datas[4]);
        passwordField = new JTextField(datas[5]);
        addButton = new JButton("KAYDET");
        backButton = new JButton("MENÜ");

        infoPanel.add(info);
        dataPanel.add(usercode);
        dataPanel.add(usercodeField);
        dataPanel.add(name);
        dataPanel.add(nameField);
        dataPanel.add(surname);
        dataPanel.add(surnameField);
        dataPanel.add(phoneNumber);
        dataPanel.add(phoneField);
        dataPanel.add(tcNo);
        dataPanel.add(tcField);
        dataPanel.add(password);
        dataPanel.add(passwordField);
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);

        container.add(infoPanel);
        container.add(dataPanel);
        container.add(buttonPanel);

        backButton.addActionListener(e -> {
            setVisible(false);
            backframe.setVisible(true);
        });

        addButton.addActionListener(e ->{
            if(usercodeField.getText().isEmpty() || nameField.getText().isEmpty() ||
                    surnameField.getText().isEmpty() || phoneField.getText().isEmpty() ||
                    tcField.getText().isEmpty() || passwordField.getText().isEmpty())
            {
                ErrorPage ag = new Error();
                ag.editMessage("Eksik veri veya tamamen boş.");
                ag.mainScreen();
            }
            else {
                Infos checkUser = new Infos();
                boolean found = false;
                String[] whoText = {"executers","personels"};
                for (String user : checkUser.getKnameList(whoText[whosec]+".txt")) {
                    if (user.equals(usercodeField.getText()) && !usercodeField.getText().equals(datas[0]))
                        found = true;
                }
                if (!found) {
                    Save saver = new Save();
                    if(whosec == 0) {
                        Executer myex = new Executer(usercodeField.getText(), nameField.getText(),
                                surnameField.getText(), Integer.parseInt(phoneField.getText()),
                                Integer.parseInt(tcField.getText()), passwordField.getText());
                        myex.executerSaver();
                        saver.removeWorker(who, whosec);
                    }
                    else{
                        Staff myex = new Staff(usercodeField.getText(), nameField.getText(),
                                surnameField.getText(), Integer.parseInt(phoneField.getText()),
                                Integer.parseInt(tcField.getText()), passwordField.getText());
                        myex.staffSaver();
                        saver.removeWorker(who, whosec);
                    }
                    setVisible(false);
                    new ExecuterorStaffProcess(whosec);
                } else {
                    ErrorPage ag = new Error();
                    ag.editMessage("Bu kullanıcı adı alınmış.");
                    ag.mainScreen();
                }
            }
        });

        String[] titleName = {"YÖNETİCİ DÜZENLEME SAYFASI", "PERSONEL DÜZENLEME SAYFASI"};
        setTitle(titleName[whosec]);
        setVisible(true);
        setResizable(false);
        setLocation(500,170);
        setSize(360,290);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}