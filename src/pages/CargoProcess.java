package pages;

import cargo.Cargo;
import utils.Infos;
import utils.Save;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class CargoProcess extends JFrame {
    Container mainContainer;
    JButton addButton;
    JButton deleteButton;
    JButton deliverButton;
    JButton refreshButton;
    JButton backButton;
    JPanel forTablePanel;
    JPanel forButtonsPanel;
    JTable cargoList;
    DefaultTableModel defaultmodel;
    JScrollPane scrollTable;
    private String[][] dataArray;
    int who;
    public CargoProcess(int who){
        super();
        this.who = who;
        mainContainer = this.getContentPane();
        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        String[] columnNames = {"KARGO KODU","KARGO DESİSİ" ,"GÖNDEREN ADI", "GÖNDEREN SOYADI", "GÖNDERİM ŞUBESİ",
                "ALICI ADI","ALICI SOYADI", "ALICI TELEFON", "TESLİM ŞUBE", "DURUM"};

        dataAdder();
        defaultmodel = new DefaultTableModel(dataArray, columnNames);
        cargoList = new JTable(defaultmodel);
        cargoList.setDefaultEditor(Object.class,null);
        forTablePanel = new JPanel();
        scrollTable = new JScrollPane(cargoList);
        scrollTable.setPreferredSize(new Dimension(1250, 320));
        scrollTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        forButtonsPanel = new JPanel();

        addButton = new JButton("KARGO EKLE");
        deliverButton = new JButton("TESLİM ET");
        refreshButton = new JButton("TABLOYU YENİLE");
        deleteButton = new JButton("KARGOYU LİSTEDEN KALDIR");
        backButton = new JButton("MENÜ");


        forTablePanel.add(scrollTable,BorderLayout.CENTER);
        forButtonsPanel.add(addButton);
        forButtonsPanel.add(deliverButton);
        forButtonsPanel.add(deleteButton);
        forButtonsPanel.add(refreshButton);
        forButtonsPanel.add(backButton);

        mainContainer.add(forTablePanel);
        mainContainer.add(forButtonsPanel);

        addButton.addActionListener(e ->{
            setVisible(false);
            new AddCargoPage(this,who);
        });

        refreshButton.addActionListener(e ->{
            setVisible(false);
            new CargoProcess(who);
        });

        backButton.addActionListener(e ->{
            setVisible(false);
            if(who == 0)
                new OptionsforExecuter();
            else {
                new MainPage();
            }
        });


        deleteButton.addActionListener(e ->{
            if(cargoList.getSelectedRow() == -1 && who == 0)
            {
                ErrorPage ag = new Error();
                ag.editMessage("Seçili satır olmadığı için silinmedi.");
                ag.mainScreen();
            }
            else if(who == 1){
                ErrorPage ag = new Error();
                ag.editMessage("Personeller silme işlemi yapamaz.");
                ag.mainScreen();
            }
            else
            {
                int dialogResult = JOptionPane.showOptionDialog (this,
                        "Seçili kargo kaldırılsın mı?", "Emin misiniz?", JOptionPane.YES_NO_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, new String[]{"EVET", "HAYIR"}, null);
                if(dialogResult == JOptionPane.YES_OPTION){
                    if(cargoList.getValueAt(cargoList.getSelectedRow(), 9).equals("TESLİM EDİLDİ")){
                        Save saver = new Save();
                        saver.removeCargo(cargoList.getSelectedRow());
                        setVisible(false);
                        new CargoProcess(who);
                    }
                    else{
                        ErrorPage ag = new Error();
                        ag.editMessage("Kargo teslim edilmedi!");
                        ag.mainScreen();
                    }

                }
            }
        });

        deliverButton.addActionListener(e ->{
            if(cargoList.getSelectedRow() == -1)
            {
                ErrorPage ag = new Error();
                ag.editMessage("Teslim edilecek kargoyu seçiniz.");
                ag.mainScreen();
            }
            else if((cargoList.getValueAt(cargoList.getSelectedRow(), 9).equals("TESLİM EDİLDİ"))){
                ErrorPage ag = new Error();
                ag.editMessage("Çoktan teslim edilmiş.");
                ag.mainScreen();
            }
            else
            {
                Save saver = new Save();
                saver.deliverCargo(cargoList.getSelectedRow());
                JOptionPane.showMessageDialog(null,"TESLİM EDİLDİ.",
                        "BİLGİ",JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                new CargoProcess(who);
            }

        });

        setTitle("KARGO EYLEMLERİ");
        setResizable(false);
        setLocation(150,150);
        setSize(1300,440);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    private void dataAdder(){
        Infos at = new Infos();
        ArrayList<String[]> list = at.cargoInfosFetcher();
        dataArray = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            dataArray[i] = list.get(i);
        }
    }
}

class AddCargoPage extends JFrame{
    JLabel info;
    JLabel volume;
    JLabel senderName;
    JLabel senderSurname;
    JLabel senderAdress;
    JLabel receiverName;
    JLabel receiverSurname;
    JLabel receiverPhone;
    JLabel receiverAdress;

    JTextField volumeField;
    JTextField senderNameField;
    JTextField senderSurnameField;
    JTextField receiverNameField;
    JTextField receiverSurnameField;
    JTextField receiverPhoneField;

    JComboBox<String> senderAdressField;
    JComboBox<String> receiverAdressField;

    JButton addButton;
    JButton desiCalculateButton;
    JButton priceCalculatorButton;
    JButton backButton;

    Container container;
    JPanel infoPanel;
    JPanel dataPanel;
    JPanel buttonPanel;

    JFrame backframe;
    int who;
    private int result = 0;
    public AddCargoPage(JFrame backframe, int who){
        super("KARGO EKLE");
        this.who = who;
        this.backframe = backframe;
        container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dataPanel = new JPanel(new GridLayout(8, 2, 3,3));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        info = new JLabel("KARGONUN VERİLERİNİ GİRİNİZ.");

        volume = new JLabel("Kargo Desisi:");
        senderName = new JLabel("Gönderenin Adı:");
        senderSurname = new JLabel("Gönderenin Soyadı:");
        senderAdress = new JLabel("Gönderinin Adresi:");
        receiverName = new JLabel("Alıcının Adı:");
        receiverSurname = new JLabel("Alıcının Soyadı:");
        receiverPhone = new JLabel("Alıcının Telefonu:");
        receiverAdress = new JLabel("Alıcının Adresi:");

        volumeField = new JTextField();
        senderNameField = new JTextField();
        senderSurnameField = new JTextField();
        senderAdressField = new JComboBox<>();
        receiverNameField = new JTextField();
        receiverSurnameField = new JTextField();
        receiverPhoneField = new JTextField();
        receiverAdressField = new JComboBox<>();

        Infos forAdresses = new Infos();

        for(String item: forAdresses.getStores()){
            senderAdressField.addItem(item);
            receiverAdressField.addItem(item);
        }

        addButton = new JButton("EKLE");
        desiCalculateButton = new JButton("DESİ SOR");
        priceCalculatorButton = new JButton("ÜCRET HESAPLA");
        backButton = new JButton("MENÜ");

        infoPanel.add(info);
        dataPanel.add(volume);
        dataPanel.add(volumeField);
        dataPanel.add(senderName);
        dataPanel.add(senderNameField);
        dataPanel.add(senderSurname);
        dataPanel.add(senderSurnameField);
        dataPanel.add(senderAdress);
        dataPanel.add(senderAdressField);
        dataPanel.add(receiverName);
        dataPanel.add(receiverNameField);
        dataPanel.add(receiverSurname);
        dataPanel.add(receiverSurnameField);
        dataPanel.add(receiverPhone);
        dataPanel.add(receiverPhoneField);
        dataPanel.add(receiverAdress);
        dataPanel.add(receiverAdressField);

        buttonPanel.add(addButton);
        buttonPanel.add(desiCalculateButton);
        buttonPanel.add(priceCalculatorButton);
        buttonPanel.add(backButton);

        container.add(infoPanel);
        container.add(dataPanel);
        container.add(buttonPanel);

        backButton.addActionListener(e -> {
            setVisible(false);
            backframe.setVisible(true);
        });

        priceCalculatorButton.addActionListener(e ->{
            if(senderAdressField.getSelectedItem() == receiverAdressField.getSelectedItem()){
                ErrorPage ag = new Error();
                ag.editMessage("Gönderici adresi ile alıcı aynı.");
                ag.mainScreen();
            }
            else if(volumeField.getText().isEmpty()){
                ErrorPage ag = new Error();
                ag.editMessage("Lütfen desi bilgisini giriniz.");
                ag.mainScreen();
            }
            else if(Integer.parseInt(volumeField.getText()) < 1){
                ErrorPage ag = new Error();
                ag.editMessage("Desi değeri minumum 1 olmalıdır.");
                ag.mainScreen();
            }
            else {
                int kmPrice = 5;
                int desiPrice = 7;
                int one = 1;
                int two = 1;

                for (int i = 0; i < forAdresses.getStores().size(); i++) {
                    if (Objects.equals(senderAdressField.getSelectedItem(), forAdresses.getStores().get(i)))
                        one = i;
                    else if (Objects.equals(receiverAdressField.getSelectedItem(), forAdresses.getStores().get(i)))
                        two = i;
                }
                result = netPrice(one, two) * kmPrice + desiPrice * Integer.parseInt(volumeField.getText());
                JOptionPane.showMessageDialog(null,"Kargo ücretiniz: " + result + "₺",
                        "KARGO ÜCRETİ",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        addButton.addActionListener(e ->{
            ErrorPage ag = new Error();
            if(volumeField.getText().isEmpty() || senderNameField.getText().isEmpty() ||
                    senderSurnameField.getText().isEmpty() || receiverNameField.getText().isEmpty() ||
                    receiverSurnameField.getText().isEmpty() || receiverPhoneField.getText().isEmpty())
            {
                ag.editMessage("Eksik veri veya tamamen boş.");
                ag.mainScreen();
            }
            else if(result == 0){
                ag.editMessage("Önce fiyat hesaplanmalıdır.");
                ag.mainScreen();
            }
            else if(Integer.parseInt(volumeField.getText()) < 1){
                ag.editMessage("Desi değeri minumum 1 olmalıdır.");
                ag.mainScreen();
            }
            else if(senderAdressField.getSelectedItem() == receiverAdressField.getSelectedItem()){
                ag.editMessage("Gönderici adresi ile alıcı aynı.");
                ag.mainScreen();
            }
            else {
                Object[] options = {"TAMAM", "İPTAL"};
                int dialogresult = JOptionPane.showOptionDialog(null,
                        result + "₺ olan ücreti onaylıyor musunuz?", "Ücret Onayı",JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,null,options,options[1]);

                if(dialogresult == JOptionPane.OK_OPTION){
                    Save saver = new Save();
                    Infos cargoNameforSave = new Infos();
                    Cargo okCargo = new Cargo(cargoNameforSave.getCargoName(),Integer.parseInt(volumeField.getText()),
                            senderNameField.getText(),senderSurnameField.getText(),
                            senderAdressField.getSelectedItem().toString(),receiverNameField.getText(),
                            receiverSurnameField.getText(),receiverPhoneField.getText(),
                            receiverAdressField.getSelectedItem().toString());
                    saver.saveCargoInfos(okCargo);
                    JOptionPane.showMessageDialog(null,"Kargo başarıyla eklendi.",
                            "KARGO ÜCRETİ",JOptionPane.INFORMATION_MESSAGE);
                    saver.logSaver(okCargo.getCargo_number() + " numaralı kargo eklendi.");
                    setVisible(false);
                    new CargoProcess(who);
                }
            }
        });

        desiCalculateButton.addActionListener(e->
            new DesiCalculate());

        setVisible(true);
        setResizable(false);
        setLocation(500,170);
        setSize(440,365);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private int netPrice(int o, int t) {
        if ((o < 8 && t < 8) || (t > 8 && t < 13 && o > 8 && o < 13) || (o > 12 && t > 12)) {
            return 2;
        } else if ((o < 8 && t > 8 && t < 13) || (t < 8 && o > 8 && o < 13)) {
            return 3;
        } else if ((o < 8 && t > 13) || (t < 8 && o > 13)) {
            return 5;
        }
        else
            return 5;
    }

}

class DesiCalculate extends JFrame{

    JLabel heightLabel;
    JLabel lengthLabel;
    JLabel widthLabel;

    JTextField heightField;
    JTextField lengthField;
    JTextField widthField;

    JButton calculateButton;
    JButton backButton;

    JPanel infoPanel;
    JPanel buttonPanel;

    Container container;
    public DesiCalculate(){
        super("DESİ");

        container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        infoPanel = new JPanel(new GridLayout(3,2,3,3));
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        heightLabel = new JLabel("Yüksekliği: ");
        lengthLabel = new JLabel("Uzunluğu: ");
        widthLabel = new JLabel("Eni: ");

        heightField = new JTextField();
        lengthField = new JTextField();
        widthField = new JTextField();

        calculateButton = new JButton("HESAPLA");
        backButton = new JButton("KAPAT");

        infoPanel.add(heightLabel);
        infoPanel.add(heightField);
        infoPanel.add(lengthLabel);
        infoPanel.add(lengthField);
        infoPanel.add(widthLabel);
        infoPanel.add(widthField);
        buttonPanel.add(calculateButton);
        buttonPanel.add(backButton);

        container.add(infoPanel);
        container.add(buttonPanel);

        backButton.addActionListener(e->
            setVisible(false));

        calculateButton.addActionListener(e ->{
            int desi = ((Integer.parseInt(heightField.getText()) * Integer.parseInt(lengthField.getText()) *
                    Integer.parseInt(widthField.getText())) / 30) + 1;
            setVisible(false);
            JOptionPane.showMessageDialog(null,"KARGO DESİSİ: " + desi,
                    "KARGO DESİSİ",JOptionPane.INFORMATION_MESSAGE);
        });

        setVisible(true);
        setResizable(false);
        setLocation(570,210);
        setSize(250,160);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}