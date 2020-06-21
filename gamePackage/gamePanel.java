package gamePackage;
import gamePackage.myFrame;
import gamePackage.menuPanel;
import javax.swing.JPanel;

public class gamePanel
{
    private commonParameter singleton = commonParameter.getInstance(); 
    public gamePanel()
    {
	myFrame gameFrame = new myFrame("JSB: alphaNIM créé par " + singleton.myDevName);
	menuPanel myMenuPanel = new menuPanel(gameFrame);
    }
}
