package pages;

import javax.swing.*;
import java.awt.*;

public class OptionsforExecuter extends JFrame {
    JButton cargoButton;
    JButton staffButton;
    JButton executerButton;
    JButton notesButton;
    JButton menuButton;
    public OptionsforExecuter(){
        super("AGT KURYECİLİK YÖNETİCİ PANELİ");
        this.setLayout(new GridLayout(4,1,20,20));

        cargoButton = new JButton("KARGO YÖNETİMİ EKRANI");
        staffButton = new JButton("PERSONEL YÖNETİMİ EKRANI");
        executerButton = new JButton("YÖNETİCİ YÖNETİMİ EKRANI");
        notesButton = new JButton("NOTLAR");
        menuButton = new JButton("ANA MENÜ");

        this.add(executerButton);
        this.add(staffButton);
        this.add(cargoButton);
        this.add(notesButton);
        add(menuButton);

        notesButton.addActionListener(e -> {
            setVisible(false);
            new Notes();
        });
        executerButton.addActionListener(e ->{
            setVisible(false);
            new ExecuterorStaffProcess(0);
        });
        staffButton.addActionListener(e ->{
            setVisible(false);
            new ExecuterorStaffProcess(1);
        });

        cargoButton.addActionListener(e ->{
            setVisible(false);
            new CargoProcess(0);
        });

        menuButton.addActionListener(e->{
            setVisible(false);
            new MainPage();
        });

        this.setResizable(false);
        this.setLocation(500,170);
        this.setSize(500,250);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
