package gamePackage;
import gamePackage.myFrame;
import gamePackage.aProposEvent;
import gamePackage.menuEvent;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JPanel;

public class commonBottomPanel
{
    public commonBottomPanel(myFrame mainFrame, JPanel bottomPanel)
    {
        JButton backButton = new JButton("Retour au menu");
	backButton.setPreferredSize(new Dimension(300,50));
	backButton.addActionListener(new menuEvent(mainFrame));

	JButton endButton = new JButton("Quitter jeu");
	endButton.setPreferredSize(new Dimension(300,50));
	endButton.addActionListener(new aProposEvent(mainFrame, true));

	bottomPanel.add(backButton);
	bottomPanel.add(endButton);
    }
}
