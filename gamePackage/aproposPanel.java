package gamePackage;
import gamePackage.myFrame;
import gamePackage.commonBottomPanel;
import gamePackage.commonParameter;
import java.util.concurrent.TimeUnit;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class aproposPanel
{
    public JPanel topPanel = new JPanel();
    public JPanel centerPanel = new JPanel();
    public JPanel bottomPanel = new JPanel();
    public JPanel rightPanel = new JPanel();
    public JPanel leftPanel = new JPanel();
    private commonParameter singleton = commonParameter.getInstance();

    public aproposPanel(myFrame mainFrame)
    {
	mainFrame.myPanel =  new JPanel();

	JTextArea myLabel = new JTextArea("Ce programme a été développé par " + singleton.myDevName + 
	"\nau cours d'un stage réalisé les 5 et 6 mars 2020\n" + 
	"à Jeunes Science Bordeaux.\n" +
	"Ce stage était animé par Mehdi Aouadi:\n" +
	"mehdi.aouadi@jeunes-science.asso.fr");
	myLabel.setEditable(false);
	Font police = new Font("Tahoma", Font.BOLD, 20);
	myLabel.setFont(police);
	myLabel.setForeground(Color.RED);
	myLabel.setBackground(singleton.myColor);
	centerPanel.add(myLabel);

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

	topPanel.setPreferredSize(new Dimension(100,100));
	bottomPanel.setPreferredSize(new Dimension(350,100));
	centerPanel.setPreferredSize(new Dimension(100,400));
	leftPanel.setPreferredSize(new Dimension(50,100));
	rightPanel.setPreferredSize(new Dimension(50,100));

	commonTopPanel myCommonTopPanel = new commonTopPanel(topPanel);
	commonBottomPanel myCommonBottomPanel = new commonBottomPanel(mainFrame, bottomPanel);

	mainFrame.setContentPane(mainFrame.myPanel);
	mainFrame.setVisible(true);
    }
}
