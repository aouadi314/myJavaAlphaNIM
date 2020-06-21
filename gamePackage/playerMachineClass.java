package gamePackage;
import gamePackage.gameSystemPanel;
import java.awt.Robot;
import java.awt.AWTException;

class playerMachineThread extends Thread
{
    private gameSystemPanel mygamePanel;
    private gameStateSingleton StateGame = gameStateSingleton.getInstance();
    private commonParameter singleton = commonParameter.getInstance();
    public playerMachineThread(gameSystemPanel thegamePanel)
    {
	mygamePanel = thegamePanel;
    }
    public void run()
    {
	try
	{
		Robot C3P0 = new Robot();
		int sum = 0;
		int idx = singleton.nbStick - StateGame.stickAvailable, numStick = 0;
		for(int i_n = 0; i_n < singleton.nbStickToChoose; ++i_n)
		{
			sum += singleton.trainingTable[idx][i_n];
		}
		int rand = (int)Math.floor(Math.random()*sum);
		int min = 0, max = 0;
		for(int i_n = 0; i_n < singleton.nbStick; ++i_n)
		{
			max += singleton.trainingTable[idx][i_n];
			if(min <= rand && rand < max)
			{
				numStick = i_n + 1;
				break;
			}
			min += singleton.trainingTable[idx][i_n];
		}
		if(StateGame.noHuman)
		{
			C3P0.delay(1);	
		}
		else
		{
			C3P0.delay(1000);
		}
		for(int i_n = 0; i_n < numStick; ++i_n)
		{
			mygamePanel.myCheckBox[i_n].setEnabled(true);
			mygamePanel.myCheckBox[i_n].doClick();
			mygamePanel.myCheckBox[i_n].setEnabled(false);
			if(StateGame.noHuman)
			{
				C3P0.delay(1);	
			}
			else
			{
				C3P0.delay(1000);
			}
		}
		System.out.println("Le joueur "+String.valueOf(StateGame.idPlayer + 1) + " joue et choisi de prendre " + String.valueOf(numStick) + " allumette(s).");
		for(int i_n = 0; i_n < StateGame.stickAvailable; ++i_n)
		{
			mygamePanel.myCheckBox[i_n].setEnabled(true);
		}		
		mygamePanel.EndTurnButton.doClick();
	}
	catch(AWTException awtE)
	{
	}
    }

}


public class playerMachineClass extends playerClass
{
    public playerMachineClass()
    {
	super();
	isHuman = false;
    }

    @Override
    public void gamePlayerMove(gameSystemPanel mygamePanel)
    {
	for(int i_n = 0; i_n < StateGame.stickAvailable; ++i_n)
	{
		mygamePanel.myCheckBox[i_n].setEnabled(false);
	}
	playerMachineThread myThread = new playerMachineThread(mygamePanel);
	myThread.start();
    }
}
