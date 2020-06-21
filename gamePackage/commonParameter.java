package gamePackage;
import java.awt.Color;
import jsonPackage.JSONArray;
import jsonPackage.JSONObject;
import jsonPackage.JSONTokener;
import jsonPackage.JSONException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class commonParameter
{
    private static final commonParameter instance = new commonParameter();
    private String trainingFileName = "./trainingTableValue.json";
    private String parameterFileName = "./myParameterValue.json";
    private int[] backgroundColor = new int[3];
    public Color myColor;
    public String myDevName;
    public int nbStick;
    public int nbStickToChoose;
    public int[][] trainingTable;

    public commonParameter()
    {
	readParameterFile();
	saveParameterFile();
	readTrainingFile();
    }	
    public static final commonParameter getInstance() 
    {
        return instance;
    }

    public void readParameterFile()
    {
	boolean isGood = true;
	File file = new File(parameterFileName);
        InputStream is = null;
	try
	{
		is = new FileInputStream(file);
	}
	catch(FileNotFoundException e)
	{
		isGood = false;
	}
	if (isGood) 
	{
		try
		{
			JSONTokener tokener = new JSONTokener(is);
        		JSONObject object = new JSONObject(tokener);
			nbStick = (int)object.get("nbStick");
			nbStickToChoose = (int)object.get("nbStickToChoose");
			myDevName = (String)object.get("developerName");
			if(nbStick >= 27)
			{
				nbStick = 27;
			}
			trainingTable = new int[nbStick][nbStickToChoose];
			JSONArray jsonBackgroundColor = object.getJSONArray("backgroundColor");
			if(jsonBackgroundColor.length() == 3)
			{
				for(int i = 0; i < 3; ++i)
				{
					int value = (int)jsonBackgroundColor.get(i);
					if (0 > value)
					{
						value = 0;
					}
					if(value > 255)
					{
						value = 255;
					}
					backgroundColor[i] = value;
				}
				myColor = new Color(backgroundColor[0], backgroundColor[1], backgroundColor[2]);
			}
			else
			{
				isGood = false;
			}
		}
		catch(JSONException e)
		{
			isGood = false;
		}
		try
		{
			is.close();
		}
		catch(IOException e)
		{
		}
	}
	if (isGood == false) 
	{
		backgroundColor[0] = 243;
		backgroundColor[1] = 247;
		backgroundColor[2] = 246;
		myColor = new Color(backgroundColor[0], backgroundColor[1], backgroundColor[2]);
    		myDevName = "MrRobot";
   		nbStick = 6;
    		nbStickToChoose = 2;
		trainingTable = new int[nbStick][nbStickToChoose];
	}
    }

    public void saveParameterFile() 
    {
      	JSONObject object = new JSONObject();
        
        JSONArray jsonBackgroundColor = new JSONArray();
	for(int i_p = 0; i_p < 3; i_p++)
	{
		jsonBackgroundColor.put(backgroundColor[i_p]);
	}

        object.put("backgroundColor", jsonBackgroundColor);
	object.put("developerName", myDevName);
	object.put("nbStick", nbStick);
	object.put("nbStickToChoose", nbStickToChoose);

        String jsonString = object.toString(2);
	File file = new File(parameterFileName);
	OutputStream os = null;
	try
	{
		os = new FileOutputStream(file);
	}
	catch(FileNotFoundException e)
	{
	}
	PrintStream printStream = new PrintStream(os);
	printStream.print(jsonString);
	printStream.close();
    }

    public void readTrainingFile()
    {
	boolean isGood = true;
	File file = new File(trainingFileName);
        InputStream is = null;
	try
	{
		is = new FileInputStream(file);
	}
	catch(FileNotFoundException e)
	{
		isGood = false;
	}
	if (isGood) 
	{
		try
		{
			JSONTokener tokener = new JSONTokener(is);
        		JSONObject object = new JSONObject(tokener);
			JSONArray jsonTrainingTable_Columns = object.getJSONArray("trainingTable");
			int minRow = 10000, maxRow = 0;
			int nbColumns = jsonTrainingTable_Columns.length();
        		for (int i = 0; i < jsonTrainingTable_Columns.length(); i++) 
			{
				JSONArray jsonTrainingTable_Rows = (JSONArray)jsonTrainingTable_Columns.get(i);
				int nbRows = jsonTrainingTable_Rows.length();
				if(minRow > nbRows)
				{
					minRow = nbRows;
				}
				if(maxRow < nbRows)
				{
					maxRow = nbRows;
				}
			}
			
			if(nbStick == nbColumns && minRow == maxRow && minRow == nbStickToChoose)
			{
        			for (int i = 0; i < jsonTrainingTable_Columns.length(); i++) 
				{
					JSONArray jsonTrainingTable_Rows = (JSONArray)jsonTrainingTable_Columns.get(i);
					for (int j = 0; j < jsonTrainingTable_Rows.length(); j++) 
					{
            					trainingTable[i][j] = (int)jsonTrainingTable_Rows.get(j);
					}	
				}
        		}
			else
			{
				isGood = false;
			}
		}
		catch(JSONException e)
		{
			isGood = false;
		}
		try
		{
			is.close();
		}
		catch(IOException e)
		{
		}
	}

	if (isGood == false) 
	{
		for(int i_p = 0; i_p < nbStick; i_p++)
		{	
			for(int i_n = 0; i_n < nbStickToChoose; i_n++)
			{
				trainingTable[i_p][i_n] = 0;
				if(i_n < nbStick - i_p)
				{
					trainingTable[i_p][i_n] = 10;
				}
			}
		}	
	}
    }

    public void saveTrainingFile() 
    {
      	JSONObject object = new JSONObject();
        
        JSONArray jsonTrainingTable = new JSONArray();
	for(int i_p = 0; i_p < nbStick; i_p++)
	{
		JSONArray jsonTrainingTable_P = new JSONArray();
		for(int i_n = 0; i_n < nbStickToChoose; i_n++)
		{
			jsonTrainingTable_P.put(trainingTable[i_p][i_n]);
		}
		jsonTrainingTable.put(jsonTrainingTable_P);
	}

        object.put("trainingTable", jsonTrainingTable);

        String jsonString = object.toString(2);
	File file = new File(trainingFileName);
	OutputStream os = null;
	try
	{
		os = new FileOutputStream(file);
	}
	catch(FileNotFoundException e)
	{
	}
	PrintStream printStream = new PrintStream(os);
	printStream.print(jsonString);
	printStream.close();
    }
}

