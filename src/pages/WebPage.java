package pages;

import javax.swing.*;
import utils.Infos;
import utils.Save;
import java.awt.*;

public class WebPage{
    JFrame firstFrame;
    JLabel cargoFollowText;
    JLabel messageBox;
    JLabel cargoFollowCodeWhat;
    JButton clickButton;
    JButton backButton;
    JFrame back;
    JTextField cargoFollowField;
    private final String[] messagetoUser= {"Girilen kargo kodu mevcut değil.","Kargo kodu giriniz.","Sorgulama başarılı."};
    JTextArea cargoInfos;
    JScrollPane cargoScroll;

    public WebPage(JFrame back){
        super();
        this.back = back;

        firstFrame = new JFrame("Kargo Takip Ekranı");
        firstFrame.setLayout(new FlowLayout());

        cargoFollowText = new JLabel(" Kargo Takip Kodunuzu Giriniz.: ");
        clickButton = new JButton("SORGULA");
        cargoFollowField = new JTextField();
        cargoFollowField.setPreferredSize(new Dimension(100,25));
        cargoFollowCodeWhat = new JLabel("Bu kodu gönderiniz üzerinde bulabilirsiniz. Sorgulama durumu: ");
        messageBox = new JLabel();
        messageBox.setForeground(Color.blue);

        cargoInfos = new JTextArea(6,30);
        cargoInfos.setFont(new Font(Font.SERIF,Font.ITALIC,18));
        cargoInfos.setEnabled(false);
        cargoScroll = new JScrollPane(cargoInfos);
        cargoScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        backButton = new JButton("ANA MENÜ");

        firstFrame.add(cargoFollowText);
        firstFrame.add(cargoFollowField);
        firstFrame.add(clickButton);
        firstFrame.add(backButton);
        firstFrame.add(cargoFollowCodeWhat);
        firstFrame.add(messageBox);
        firstFrame.add(cargoScroll);

        firstFrame.setLocation(500,170);
        firstFrame.setResizable(false);
        firstFrame.setSize(600,250);
        firstFrame.setVisible(true);

        clickButton.addActionListener(e ->{
            Infos cargos = new Infos();
            if (cargoFollowField.getText().isEmpty()){
                messageBox.setText(messagetoUser[1]);
                cargoInfos.setText("");
            }
            else{
                for(String cargoname:cargos.getCargoNamesList()){
                    if(cargoFollowField.getText().equals(cargoname)){
                        messageBox.setText(messagetoUser[2]);
                        Save saver = new Save();
                        saver.logSaver(cargoFollowField.getText() + " numaralı kargo sorgulandı.");
                        cargoInfos.setText("KARGO KODUNUZ: " + cargos.getCargoInfos(cargoname)[0] +
                                "\nKARGONUN BULUNDUĞU ŞUBE: " + cargos.getCargoInfos(cargoname)[4] +
                                "\nKARGO HEDEF ŞUBESİ: " + cargos.getCargoInfos(cargoname)[8] +
                                "\nKARGO TESLİM DURUMU: " + cargos.getCargoInfos(cargoname)[9]);
                        break;
                    }
                    else{
                        messageBox.setText(messagetoUser[0]);
                        cargoInfos.setText("");
                    }
                }
            }


        });

        backButton.addActionListener(e -> {
            back.setVisible(true);
            firstFrame.setVisible(false);

        });

        firstFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}