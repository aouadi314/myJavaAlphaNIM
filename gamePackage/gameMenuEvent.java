package gamePackage;
import gamePackage.gameMenuPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import java.awt.Color;
import gamePackage.myFrame;

public class gameMenuEvent implements ActionListener 
{
    public myFrame gameFrame;
    public gameMenuEvent(myFrame mainFrame)
    {
	gameFrame = mainFrame;
    }
    public void actionPerformed (ActionEvent e) 
    {
	gameMenuPanel mygameMenuPanel = new gameMenuPanel(gameFrame);
    }
}
