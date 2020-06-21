package gamePackage;
import gamePackage.myFrame;
import gamePackage.commonTopPanel;
import gamePackage.gameMenuEvent;
import gamePackage.aProposEvent;
import gamePackage.commonParameter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class menuPanel
{
    public JPanel topPanel = new JPanel();
    public JPanel centerPanel = new JPanel();
    public JPanel bottomPanel = new JPanel();
    public JPanel rightPanel = new JPanel();
    public JPanel leftPanel = new JPanel();
    private commonParameter singleton = commonParameter.getInstance();
   
    public menuPanel(myFrame mainFrame)
    {
	mainFrame.myPanel =  new JPanel();
	JButton gameButton = new JButton("Commencer partie");
	gameButton.setPreferredSize(new Dimension(300,50));
	gameButton.addActionListener(new gameMenuEvent(mainFrame));

	JButton aproposButton = new JButton("A propos de ce jeu");
	aproposButton.setPreferredSize(new Dimension(300,50));
	aproposButton.addActionListener(new aProposEvent(mainFrame, false));
	
	JButton endButton = new JButton("Fin du jeu");
	endButton.setPreferredSize(new Dimension(300,50));
	endButton.addActionListener(new aProposEvent(mainFrame, true));

	mainFrame.myPanel.setBackground(singleton.myColor);
	topPanel.setBackground(singleton.myColor);
	bottomPanel.setBackground(singleton.myColor);
	centerPanel.setBackground(singleton.myColor);
	leftPanel.setBackground(singleton.myColor);
	rightPanel.setBackground(singleton.myColor);

	mainFrame.myPanel.setLayout(new BorderLayout());
	mainFrame.myPanel.add(topPanel, BorderLayout.NORTH);
	mainFrame.myPanel.add(centerPanel, BorderLayout.CENTER);
	mainFrame.myPanel.add(bottomPanel, BorderLayout.SOUTH);
	mainFrame.myPanel.add(rightPanel, BorderLayout.EAST);
	mainFrame.myPanel.add(leftPanel, BorderLayout.WEST);

	topPanel.setPreferredSize(new Dimension(100,250));
	bottomPanel.setPreferredSize(new Dimension(100,100));
	centerPanel.setPreferredSize(new Dimension(100,400));
	leftPanel.setPreferredSize(new Dimension(200,100));
	rightPanel.setPreferredSize(new Dimension(200,100));

	centerPanel.add(gameButton);
	centerPanel.add(aproposButton);
	centerPanel.add(endButton);
	
	commonTopPanel myCommonTopPanel = new commonTopPanel(topPanel);

	mainFrame.setContentPane(mainFrame.myPanel);
	mainFrame.setVisible(true);
    }
}
