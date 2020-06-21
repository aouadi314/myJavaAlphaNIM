package gamePackage;
import gamePackage.gameSystemPanel;

public class playerClass
{
    protected gameStateSingleton StateGame = gameStateSingleton.getInstance();
    protected commonParameter singleton = commonParameter.getInstance();
    public int[] idxStickAvail = new int[singleton.nbStick];
    public int[] nbStickChosen = new int[singleton.nbStick];
    public int nbStep;
    public boolean isHuman;
    public playerClass()
    {
	reset();
    }

    public boolean gameStateMove()
    {
	if(StateGame.numberPlayerCheck > 0)
	{
		StateGame.stickAvailable-= StateGame.numberPlayerCheck;
		set(StateGame.stickAvailable + StateGame.numberPlayerCheck, StateGame.numberPlayerCheck);
		if(StateGame.stickAvailable > 0)
		{	
			for(int i_p = 0; i_p < singleton.nbStick; i_p++)
			{
				if(StateGame.stickIsCheck[i_p])
				{
					StateGame.stickIsAvailable[i_p] = false;
				}
			}
			StateGame.idPlayer = (StateGame.idPlayer + 1) % 2;
			return true;
		}
		else
		{
			return false;
		}
	}
	else
	{
		return true;
	}
    }

    public void reset()
    {
	nbStep = 0;
    }

    public void set(int idxStAvail, int nbStChos)
    {
	idxStickAvail[nbStep] = idxStAvail;
	nbStickChosen[nbStep] = nbStChos;
	nbStep++;
    }

    public void saveState(boolean isWinner)
    {
	for(int i_st = 0; i_st < nbStep; i_st++)
	{
		if(isWinner)
		{
			singleton.trainingTable[singleton.nbStick - idxStickAvail[i_st]][nbStickChosen[i_st] - 1]++;
		}
		else
		{
			singleton.trainingTable[singleton.nbStick - idxStickAvail[i_st]][nbStickChosen[i_st] - 1]--;
			if(singleton.trainingTable[singleton.nbStick - idxStickAvail[i_st]][nbStickChosen[i_st] - 1] <= 0)
			{
				singleton.trainingTable[singleton.nbStick - idxStickAvail[i_st]][nbStickChosen[i_st] - 1] = 1;
			}
		}
	}
	singleton.saveTrainingFile();
    }

    public void gamePlayerMove(gameSystemPanel mygamePanel)
    {
    }
}
