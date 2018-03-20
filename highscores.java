import javax.swing.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class highscores extends JFrame
{
	JPanel pnl = new JPanel(null);
	JTable tbl = new JTable(20,2);
	public void run(int highscore)
	{
		this.setSize(400,500);
		this.setLocation(600,300);
		this.setTitle("Highscores");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //only close this window when X clicked; not entire program
		
		createObjects();
		
		this.add(pnl);
		this.setVisible(true);
		
		int numScores = loadScores();
		checkScore(highscore,numScores); 
	}
	public void createObjects()
	{
		tbl.setSize(325,400);
		tbl.setLocation(25,25);
		tbl.setRowHeight(20);
		tbl.setBorder(BorderFactory.createLineBorder(Color.black)); //gives JTable a border
		pnl.add(tbl);
	}
	public int loadScores()
	{		
		String readArray[] = new String[20];
		int f = 0; //used as count
		String read = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("highscores.txt")); //allows lines of text to be read from file line by line
			while((read = br.readLine()) != null) //while line read contains text
			{
				readArray[f] = read; //set array at f to value that is read
				f++;
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"An error occurred"); //display error message
		}
		fillTable(readArray,f);
		return f; //returns number of scores
	}
	public void fillTable(String[] values,int count)
	{
		String[] scoresSplit;
		int row = 0;
		for(int p = 0;p<count;p++)
		{
			scoresSplit = values[p].split(":");
			tbl.setValueAt(scoresSplit[0],row,0);
			tbl.setValueAt(scoresSplit[1],row,1);
			row++;
		}
	}
	public void checkScore(int score,int numScores)
	{
		int[] scores = new int[20];
		boolean scoreEntered = false; //used to make sure each score is only entered once
		int addPosition = -1; //rogue value
		for(int w = 0;w < numScores; w++)
		{
			scores[w] = Integer.parseInt(tbl.getModel().getValueAt(w,1).toString()); //seemingly convoluted way of getting an int from a JTable
			if(score < scores[w] && scoreEntered == false) //if score is less than the score read...
			{
				addPosition = w; //...set position to add new score into table as w (current row)
				scoreEntered = true;
			}
			if(score == scores[w] && scoreEntered == false) //if score is equal, put new score below it
			{
				addPosition = w+1;
				scoreEntered = true;
			}
		}
		if(addPosition != -1) //if addPosition has been set, i.e. if the score is good enough to go into the table.
		{
			addScore(scores,score,addPosition); //addPosition = position in the highscores where new score should be added
		}
	}
	public void addScore(int[] scoreArray,int newScore,int row)
	{
		//System.out.println("///////////// addScore /////////////");
		/*String[] nameArray = new String[20];
		int tempScore1 = -1;
		int tempScore2 = -1;
		
		tempScore1 = scoreArray[counter];
		nameArray[counter] = (tbl.getModel().getValueAt(counter,0).toString());
		
		tbl.setValueAt("ENTER NAME",counter,0); //sets name field to "ENTER NAME"
		
		scoreArray[counter] = newScore;
		tbl.setValueAt(newScore,counter,1);
		counter++;
		while(counter != scoreArray.length) //CLEAN THIS UP
		{
			if(counter == scoreArray.length)
			{
				break;
			}
			nameArray[counter] = (tbl.getModel().getValueAt(counter,0).toString());
			tbl.setValueAt(nameArray[counter-1],counter,0);
			tempScore2 = scoreArray[counter];
			scoreArray[counter] = tempScore1;
			tbl.setValueAt(tempScore1,counter,1);
			counter++;
			if(counter == scoreArray.length)
			{
				break;
			}
			nameArray[counter] = (tbl.getModel().getValueAt(counter,0).toString());
			tbl.setValueAt(nameArray[counter-1],counter,0);
			tempScore1 = scoreArray[counter];
			scoreArray[counter] = tempScore2;
			tbl.setValueAt(tempScore2,counter,1);
			counter++;
			
		}*/
		int x = 19;
		do
		{
			if(x == row)
			{
				tbl.setValueAt("ENTER NAME",row,0);  //if current row is where the new score should be added, add the new score and prompt user to enter name
				tbl.setValueAt(newScore,row,1); 
				x = -1;
			}
			else
			{
				tbl.setValueAt(tbl.getModel().getValueAt(x-1,0),x,0); //go upwards through table, shuffling scores down
				tbl.setValueAt(tbl.getModel().getValueAt(x-1,1),x,1);
				x--;
			}
		}
		while(x != -1);
	}
}