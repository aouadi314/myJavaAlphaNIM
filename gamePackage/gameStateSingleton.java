package gamePackage;
import gamePackage.commonParameter;
import gamePackage.playerClass;

public class gameStateSingleton
{
    private static final gameStateSingleton instance = new gameStateSingleton();
    private commonParameter singleton = commonParameter.getInstance(); 
    public int idPlayer;
    public int numberPlayerCheck;
    public boolean noHuman;
    public boolean[] stickIsCheck = new boolean[singleton.nbStick];
    public boolean[] stickIsAvailable = new boolean[singleton.nbStick];
    public int stickAvailable;    

    public gameStateSingleton()
    {
	reset();
    }	
    public static final gameStateSingleton getInstance() 
    {
        return instance;
    }
    public void resetForTurn()
    {
	numberPlayerCheck = 0;
	for(int i_p = 0; i_p < singleton.nbStick; i_p++)
	{
		stickIsCheck[i_p] = false;
	}
    }
    public void reset()
    {
	numberPlayerCheck = 0;
	idPlayer = (int)Math.floor(Math.random()*1000) % 2;
	stickAvailable = singleton.nbStick;
	for(int i_p = 0; i_p < stickAvailable; i_p++)
	{
		stickIsAvailable[i_p] = true;
	}
    }	
}
