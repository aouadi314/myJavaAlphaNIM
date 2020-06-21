package gamePackage;
import gamePackage.myFrame;
import gamePackage.gameNumberPanel;
import gamePackage.commonBottomPanel;
import gamePackage.commonParameter;
import gamePackage.gameStateSingleton;
import gamePackage.playerGroupSingleton;
import gamePackage.menuPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;

class checkEvent implements ActionListener 
{
    private gameStateSingleton StateGame = gameStateSingleton.getInstance();
    private commonParameter singleton = commonParameter.getInstance();
    private int stickIdx;
    public checkEvent(int idx)
    {
	stickIdx = idx;
    }
    public void actionPerformed (ActionEvent e) 
    {
	JCheckBox myCheck = ((JCheckBox)e.getSource());
	if(myCheck.isSelected())
	{
		StateGame.numberPlayerCheck++;
		if(StateGame.numberPlayerCheck > singleton.nbStickToChoose)
		{
			StateGame.numberPlayerCheck--;
			myCheck.setSelected(false);
		}
		else
		{
			StateGame.stickIsCheck[stickIdx] = true;
		}
	}
	else
	{
		StateGame.numberPlayerCheck--;
		StateGame.stickIsCheck[stickIdx] = false;
	}
    }
}

class EndTurnEvent implements ActionListener 
{
    private gameStateSingleton StateGame = gameStateSingleton.getInstance();
    private commonParameter singleton = commonParameter.getInstance();
    private playerGroupSingleton playerGroup = playerGroupSingleton.getInstance();
    public myFrame gameFrame;
    public EndTurnEvent(myFrame mainFrame)
    {
	gameFrame = mainFrame;
    }
    public void actionPerformed(ActionEvent e) 
    {
	if(playerGroup.player[StateGame.idPlayer].gameStateMove())
	{
		gameSystemPanel mygamePanel = new gameSystemPanel(gameFrame);
		playerGroup.player[StateGame.idPlayer].gamePlayerMove(mygamePanel);
	}
	else
	{
		playerGroup.player[StateGame.idPlayer].saveState(false);
		playerGroup.player[(StateGame.idPlayer + 1) % 2].saveState(true);
		menuPanel myMenuPanel = new menuPanel(gameFrame);
	}
    }
}


public class gameSystemPanel
{
    public JPanel topPanel = new JPanel();
    public JPanel centerPanel = new JPanel();
    public JPanel bottomPanel = new JPanel();
    public JPanel rightPanel = new JPanel();
    public JPanel leftPanel = new JPanel();

    private commonParameter singleton = commonParameter.getInstance();
    private gameStateSingleton StateGame = gameStateSingleton.getInstance();
    private playerGroupSingleton playerGroup = playerGroupSingleton.getInstance();
    public JCheckBox[] myCheckBox;
 
    public JButton EndTurnButton = new JButton("Fin de tour");;    

    public gameSystemPanel(myFrame mainFrame)
    {
	myCheckBox = new JCheckBox[StateGame.stickAvailable];
	StateGame.resetForTurn();
	mainFrame.myPanel =  new JPanel();
	EndTurnButton.setPreferredSize(new Dimension(300,50));
	EndTurnButton.addActionListener(new EndTurnEvent(mainFrame));

	mainFrame.myPanel.setBackground(singleton.myColor);
	topPanel.setBackground(singleton.myColor);
	bottomPanel.setBackground(singleton.myColor);
	centerPanel.setBackground(singleton.myColor);
	leftPanel.setBackground(singleton.myColor);
	rightPanel.setBackground(singleton.myColor);

	JPanel secondLevelTopPanel = new JPanel();
	JPanel secondLevelCenterPanel = new JPanel();
	JPanel secondLevelBottomPanel = new JPanel();
	secondLevelTopPanel.setBackground(singleton.myColor);
	secondLevelCenterPanel.setBackground(singleton.myColor);
	secondLevelBottomPanel.setBackground(singleton.myColor);

	mainFrame.myPanel.setLayout(new BorderLayout());
	mainFrame.myPanel.add(topPanel, BorderLayout.NORTH);
	mainFrame.myPanel.add(centerPanel, BorderLayout.CENTER);
	mainFrame.myPanel.add(bottomPanel, BorderLayout.SOUTH);
	mainFrame.myPanel.add(rightPanel, BorderLayout.EAST);
	mainFrame.myPanel.add(leftPanel, BorderLayout.WEST);
	
	topPanel.setPreferredSize(new Dimension(100,100));
	bottomPanel.setPreferredSize(new Dimension(100,75));
	leftPanel.setPreferredSize(new Dimension(50,100));
	rightPanel.setPreferredSize(new Dimension(50,100));

	//JPanel[] stickPanel = new JPanel[singleton.P];
	int SizeX = 800 - 50 - 50 - 100;
	int SizeY = 600 - 100 - 100 - 75 - 65;
	int SizeSmallPanelX = 20;
	secondLevelTopPanel.setPreferredSize(new Dimension(50,100));
	secondLevelBottomPanel.setPreferredSize(new Dimension(50,65));
	secondLevelBottomPanel.add(EndTurnButton);
	int stAvail = 0;
	for(int i_p = 0; i_p < singleton.nbStick; i_p++)
	{
		JPanel stickPanel = new JPanel();
		stickPanel.setLayout(new BorderLayout());
		stickPanel.setPreferredSize(new Dimension(SizeSmallPanelX,SizeY));
		stickPanel.setBackground(singleton.myColor);
		if(StateGame.stickIsAvailable[i_p])
		{
			JPanel stickShowPanel = new JPanel();
			stickShowPanel.setBackground(singleton.myColor);
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("./gamePackage/stick.png").getImage().getScaledInstance(10, 150, Image.SCALE_DEFAULT));
			JLabel myLabel = new JLabel("");
			myLabel.setIcon(imageIcon);
			stickShowPanel.add(myLabel);
			JPanel CheckBoxPanel = new JPanel();
			CheckBoxPanel.setBackground(singleton.myColor);
			myCheckBox[stAvail] = new JCheckBox("");
			myCheckBox[stAvail].addActionListener(new checkEvent(i_p));
			CheckBoxPanel.add(myCheckBox[stAvail]);
			CheckBoxPanel.setPreferredSize(new Dimension(50,100));
			stickPanel.add(stickShowPanel, BorderLayout.CENTER);
			stickPanel.add(CheckBoxPanel, BorderLayout.SOUTH);
			stAvail++;
		}
		secondLevelCenterPanel.add(stickPanel);
	}

	centerPanel.setLayout(new BorderLayout());
	centerPanel.add(secondLevelTopPanel, BorderLayout.NORTH);
	centerPanel.add(secondLevelCenterPanel, BorderLayout.CENTER);
	centerPanel.add(secondLevelBottomPanel, BorderLayout.SOUTH);

	gameNumberPanel mygameNumberPanel = new gameNumberPanel(secondLevelTopPanel);
	commonTopPanel myCommonTopPanel = new commonTopPanel(topPanel);
	commonBottomPanel myCommonBottomPanel = new commonBottomPanel(mainFrame, bottomPanel);	

	mainFrame.setContentPane(mainFrame.myPanel);
	if(playerGroup.player[0].isHuman)
	{
		mainFrame.setVisible(true);
	}
    }
}
