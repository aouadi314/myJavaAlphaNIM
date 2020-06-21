package gamePackage;
import gamePackage.myFrame;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameNumberPanel
{
    private gameStateSingleton StateGame = gameStateSingleton.getInstance();
    public gameNumberPanel(JPanel topPanel)
    {
	JLabel myLabel = null;
	Font police = new Font("Tahoma", Font.BOLD, 30);
	if(StateGame.idPlayer == 0)
	{
		myLabel = new JLabel("Joueur 1");
		myLabel.setFont(police);
		myLabel.setForeground(Color.GREEN);
	}
	else
	{
		myLabel = new JLabel("Joueur 2");
		myLabel.setFont(police);
		myLabel.setForeground(Color.RED);
	}
	topPanel.add(myLabel);
    }
}
