package pages;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import utils.*;

public class LoginPageforStaff {
    JFrame mainFrame;
    JLabel userCode;
    JLabel userPass;
    JLabel infoScreen;
    JLabel loginInfo;
    JTextField userCodeField;
    JPasswordField userPassField;
    JButton loginButton;
    JButton backButton;
    JPanel mainPanel;
    JPanel secondPanel;
    JPanel topPanel;
    Container mainContainer;
    JFrame back;
    public LoginPageforStaff(JFrame back){
        this.back = back;
        mainFrame = new JFrame("Personel Girişi Ekranı");
        mainContainer = mainFrame.getContentPane();
        mainContainer.setLayout(new GridLayout(3,1));

        userPass = new JLabel();
        userCode = new JLabel();
        userCode.setText("Kullanıcı Adı: ");
        userPass.setText("Parola: ");
        userPassField = new JPasswordField();
        userPassField.setEchoChar('*');
        userCodeField = new JTextField();
        loginButton = new JButton("GİRİŞ");
        infoScreen = new JLabel("-----PERSONEL GİRİŞİ EKRANI-----");
        loginInfo = new JLabel();
        backButton = new JButton("ANA MENÜ");

        mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        secondPanel = new JPanel(new GridLayout(2,2));
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        topPanel.add(infoScreen);
        secondPanel.add(userCode);
        secondPanel.add(userCodeField);
        secondPanel.add(userPass);
        secondPanel.add(userPassField);
        mainPanel.add(loginButton);
        mainPanel.add(backButton);
        mainPanel.add(loginInfo);


        mainContainer.add(topPanel);
        mainContainer.add(secondPanel);
        mainContainer.add(mainPanel);

        mainFrame.setVisible(true);
        mainFrame.setLocation(500,170);
        mainFrame.setResizable(false);
        mainFrame.setSize(300,250);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loginButton.addActionListener(e->{{
            Infos forLogin = new Infos();
            if((userPassField.getText()).equals(forLogin.LoginInfos(userCodeField.getText(), "personels"))) {
                mainFrame.setVisible(false);
                Save saver = new Save();
                saver.logSaver("Personel "+ userCodeField.getText() + " giriş yaptı.");
                new CargoProcess(1);
            }
            else if(userPassField.getText().isEmpty() || userCodeField.getText().isEmpty()){
                ErrorPage ag = new Error();
                ag.editMessage("Giriş bilgilerinde eksik var.");
                ag.mainScreen();
            }
            else
                loginInfo.setText("Kullanıcı adınız veyahut parolanız hatalıdır.");
        }

        });
        backButton.addActionListener(e -> {
            back.setVisible(true);
            mainFrame.setVisible(false);
        });

    }

}
