package pages;

import utils.Infos;
import utils.Save;

import javax.swing.*;
import java.awt.*;

public class Notes extends JFrame {
    JTextArea noteArea;
    JButton saveButton;
    JButton backButton;
    Container mainContainer;
    JPanel panelforArea;
    JPanel panelforButtons;
    JScrollPane scrollforText;
    public Notes(){
        super("YÖNETİCİ NOTLARI");

        mainContainer = this.getContentPane();
        mainContainer.setLayout(new FlowLayout(FlowLayout.CENTER));

        panelforArea = new JPanel(new FlowLayout());
        panelforButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        noteArea = new JTextArea(19,53);
        noteArea.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,16));
        scrollforText = new JScrollPane(noteArea);
        scrollforText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        saveButton = new JButton("KAYDET");
        backButton = new JButton("MENÜ");

        noteSetter(noteArea);

        panelforArea.add(scrollforText);
        panelforButtons.add(saveButton);
        panelforButtons.add(backButton);

        mainContainer.add(panelforArea);
        mainContainer.add(panelforButtons);

        saveButton.addActionListener(e -> {
            Save saver = new Save();
            saver.noteRecorder(noteArea.getText());
            JOptionPane.showMessageDialog(null,"Notlarınız kaydedildi.",
                    "BİLGİ",JOptionPane.INFORMATION_MESSAGE);
        });
        backButton.addActionListener(e ->{
            setVisible(false);
            new OptionsforExecuter();
        });

        this.setResizable(false);
        this.setLocation(400,130);
        this.setSize(800,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    private void noteSetter(JTextArea textbox){
        Infos notes = new Infos();
        textbox.setText(notes.executerNoteFetcher());
    }

}
