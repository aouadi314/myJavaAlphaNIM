package gamePackage;
import gamePackage.myFrame;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class commonTopPanel
{
    public commonTopPanel(JPanel topPanel)
    {
	ImageIcon imageIcon = new ImageIcon(new ImageIcon("./gamePackage/alpha_logo.png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
	JLabel myLabel = new JLabel("-NIM");
	myLabel.setIcon(imageIcon);
	Font police = new Font("Tahoma", Font.BOLD, 75);
	myLabel.setFont(police);
	myLabel.setForeground(new Color(0, 146, 201));
	topPanel.add(myLabel);
    }
}
