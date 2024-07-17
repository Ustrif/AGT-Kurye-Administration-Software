package pages;

import utils.Infos;

import javax.swing.*;
import java.awt.*;

public class OtherPage extends JFrame {


    JLabel contactLabel;
    JButton backButton;
    JButton aboutButton;
    JPanel textPanel;
    JPanel contactPanel;
    JPanel subPanel;
    JScrollPane scroll;
    JTextArea questions;
    Container container;

    public OtherPage(){
        super("SSS ve İletişim");
        container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        backButton = new JButton("MENÜ");
        aboutButton = new JButton("HAKKIMIZDA");
        contactLabel = new JLabel("Acil Durum / Hatalı Teslimat durumunda İletişim Bilgileri: " +
                "Resul A. +90(552)xxx yy yy Avcılar/İst");

        questions = new JTextArea(21,60);
        questions.setBackground(Color.black);
        questions.setEnabled(false);
        scroll = new JScrollPane(questions);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        contactPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        noteSetter(questions);

        contactPanel.add(contactLabel);
        textPanel.add(scroll);
        subPanel.add(aboutButton);
        subPanel.add(backButton);

        container.add(textPanel);
        container.add(contactPanel);
        container.add(subPanel);

        backButton.addActionListener(e->{
            setVisible(false);
            new MainPage();
        });

        aboutButton.addActionListener(e ->{
            JOptionPane.showMessageDialog(null, "Bu program Resul A. tarafından AGT-Kuryecilik " +
                    "için hazırlanmıştır.\nProgramda emeği geçenlere teşekkür ederiz.\n" +
                    "Firmamız 2024 yılından beridir halkımıza kaliteli hizmeti ucuza sunmayı hedeflemektedir.\n" +
                    "Kargolarınızı hızlıca, zarar vermeksizin teslim etmek tek gayemizdir.\n" +
                    "Bizlere her daim ulaşabilirsiniz.\nAçık adresimiz: " +
                    "Üni Mah. Avcılar/İst","HAKKIMIZDA",JOptionPane.INFORMATION_MESSAGE);
        });

        setLocation(500,170);
        setSize(700,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    private void noteSetter(JTextArea textbox){
        Infos notes = new Infos();
        textbox.setText(notes.questionsFetcher());
    }


}
