package pages;

import utils.Save;

import javax.swing.*;
import java.awt.*;

public class ErrorPage extends JFrame {
    private String otherText;
    JButton closeButton;
    public ErrorPage(){
        super("HATA");
    }

    public void editMessage(String other){
        otherText = other;
    }
    public void mainScreen(){
        setLayout(new FlowLayout(FlowLayout.CENTER));

        closeButton = new JButton("TAMAM");

        add(new JLabel("Bir hata oluştu...: "));
        add(new JLabel(otherText));
        add(closeButton);

        closeButton.addActionListener(e -> setVisible(false));

        setLocation(700,350);
        setSize(215,150);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
}

class Error extends ErrorPage{
    @Override
    public void editMessage(String other) {
        Save saver = new Save();
        super.editMessage(other);
        saver.logSaver(other);
    }
}