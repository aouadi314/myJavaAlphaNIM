package gamePackage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import java.awt.Color;
import gamePackage.myFrame;

public class menuEvent implements ActionListener 
{
    public myFrame gameFrame;
    public menuEvent(myFrame mainFrame)
    {
	gameFrame = mainFrame;
    }
    public void actionPerformed (ActionEvent e) 
    {
	menuPanel myMenuPanel = new menuPanel(gameFrame);
    }
}
