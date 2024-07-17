package pages;
import javax.swing.*;
import java.awt.*;
public class MainPage extends JFrame {
    JLabel forInfo;
    JButton forStaff;
    JButton forExecuter;
    JButton forWebPage;
    JButton forOther;
    Container container;
    JPanel forImage;
    JPanel forText;
    JPanel forButtons;

    public MainPage(){
        super();
        container = this.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        forImage = new JPanel(new FlowLayout(FlowLayout.CENTER));
        forText = new JPanel(new FlowLayout(FlowLayout.CENTER));
        forButtons = new JPanel(new GridLayout(1,3));

        forInfo = new JLabel("İşlemler için butonları kullanınız. Müşteriler yalnızca MÜŞTERİ girişi yapabilir.");
        forExecuter = new JButton("YÖNETİCİ");
        forStaff = new JButton("PERSONEL");
        forWebPage = new JButton("MÜŞTERİ");
        forOther = new JButton("DİĞER");

        JLabel imageLabel = new JLabel(new ImageIcon
                ("main.png"));

        forImage.add(imageLabel);
        forText.add(forInfo);
        forButtons.add(forExecuter);
        forButtons.add(forStaff);
        forButtons.add(forWebPage);
        forButtons.add(forOther);

        container.add(forImage);
        container.add(forText);
        container.add(forButtons);

        forExecuter.addActionListener(e->{
            setVisible(false);
            new LoginPageforExecuter(this);
        });
        forStaff.addActionListener(e->{
            setVisible(false);
            new LoginPageforStaff(this);
        });
        forWebPage.addActionListener(e->{
            setVisible(false);
            new WebPage(this);
        });

        forOther.addActionListener(e->{
            setVisible(false);
            new OtherPage();
        });

        this.setTitle("AGT Kuryecilik");
        this.setResizable(false);
        this.setLocation(500,170);
        this.setSize(500,300);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}