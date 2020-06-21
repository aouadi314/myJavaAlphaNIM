package gamePackage;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class myFrame extends JFrame
{
    public JPanel myPanel = new JPanel();
    public myFrame(String frameTitle)
    {
	this.setTitle(frameTitle);
	this.setSize(800,600);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
