package gamePackage;
import gamePackage.aproposPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class aProposEvent implements ActionListener 
{
    private boolean exitGame;
    private myFrame gameFrame;
    public aProposEvent(myFrame mainFrame, boolean quitGame)
    {
	gameFrame = mainFrame;
	exitGame = quitGame;
    }
    public void actionPerformed (ActionEvent e) 
    {
	if(!exitGame)
	{
		aproposPanel myAproposPanel = new aproposPanel(gameFrame);
	}
	else
	{
		System.exit(0);
	}
    }
}   
