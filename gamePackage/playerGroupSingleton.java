package gamePackage;
import gamePackage.playerClass;
import gamePackage.playerHumanClass;
import gamePackage.playerMachineClass;

public class playerGroupSingleton
{
    private static final playerGroupSingleton instance = new playerGroupSingleton();
    public playerClass[] player = new playerClass[2];   

    public playerGroupSingleton()
    {
    }	
    public static final playerGroupSingleton getInstance() 
    {
        return instance;
    }
   
    public void set(int kindGame)
    {
	if(kindGame > 0)
	{
		player[1] = new playerMachineClass();
		if(kindGame > 1)
		{
			player[0] = new playerMachineClass();
		}
		else
		{
			player[0] = new playerHumanClass();	
		}	
	}
	else
	{
		player[0] = new playerHumanClass();
		player[1] = new playerHumanClass();
	}
    }	
}
