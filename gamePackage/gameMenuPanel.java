package gamePackage;
import gamePackage.myFrame;
import gamePackage.commonBottomPanel;
import gamePackage.commonParameter;
import gamePackage.gameMenuEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

class startGame implements ActionListener 
{
    private gameStateSingleton StateGame = gameStateSingleton.getInstance();
    private playerGroupSingleton playerGroup = playerGroupSingleton.getInstance();
    public myFrame gameFrame;
    public int myKindGame;
    public startGame(myFrame mainFrame, int kindGame)
    {
	gameFrame = mainFrame;
	myKindGame = kindGame;
    }
    public void actionPerformed(ActionEvent e) 
    {
	StateGame.reset();
	playerGroup.set(myKindGame);
	if(myKindGame > 1)
	{
		StateGame.noHuman = true;
	}
	else
	{
		StateGame.noHuman = false;
	}
	gameSystemPanel mygamePanel = new gameSystemPanel(gameFrame);
	playerGroup.player[StateGame.idPlayer].gamePlayerMove(mygamePanel);
    }
}

public class gameMenuPanel
{
    public JPanel topPanel = new JPanel();
    public JPanel centerPanel = new JPanel();
    public JPanel bottomPanel = new JPanel();
    public JPanel rightPanel = new JPanel();
    public JPanel leftPanel = new JPanel();
    private commonParameter singleton = commonParameter.getInstance();    

    public gameMenuPanel(myFrame mainFrame)
    {
	mainFrame.myPanel =  new JPanel();
	JButton OneVOneButton = new JButton("Joueur vs Joueur");
	OneVOneButton.setPreferredSize(new Dimension(500,50));
	OneVOneButton.addActionListener(new startGame(mainFrame, 0));

	JButton OneVMacButton = new JButton("Joueur vs Machine");
	OneVMacButton.setPreferredSize(new Dimension(500,50));
	OneVMacButton.addActionListener(new startGame(mainFrame, 1));
	
	JButton MacVMacButton = new JButton("Machine vs Machine");
	MacVMacButton.setPreferredSize(new Dimension(500,50));
	MacVMacButton.addActionListener(new startGame(mainFrame, 2));

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

	topPanel.setPreferredSize(new Dimension(100,150));
	bottomPanel.setPreferredSize(new Dimension(100,100));
	centerPanel.setPreferredSize(new Dimension(100,400));
	leftPanel.setPreferredSize(new Dimension(125,100));
	rightPanel.setPreferredSize(new Dimension(125,100));

	centerPanel.add(OneVOneButton);
	centerPanel.add(OneVMacButton);
	centerPanel.add(MacVMacButton);

	commonTopPanel myCommonTopPanel = new commonTopPanel(topPanel);
	commonBottomPanel myCommonBottomPanel = new commonBottomPanel(mainFrame, bottomPanel);	

	mainFrame.setContentPane(mainFrame.myPanel);
	mainFrame.setVisible(true);
    }
}
