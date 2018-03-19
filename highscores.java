import javax.swing.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
public class highscores extends JFrame
{
	JPanel pnl = new JPanel(null);
	JTable tbl = new JTable(20,2);
	public void run(int highscore)
	{
		this.setSize(400,500);
		this.setLocation(600,300);
		this.setTitle("Highscores");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		createObjects();
		
		this.add(pnl);
		this.setVisible(true);
		
		
		System.out.println(highscore);
		int numScores = loadScores();
		checkScore(highscore,numScores);
	}
	public void createObjects()
	{
		tbl.setSize(325,400);
		tbl.setLocation(25,25);
		tbl.setRowHeight(20);
		tbl.setBorder(BorderFactory.createLineBorder(Color.black));
		pnl.add(tbl);
	}
	public int loadScores()
	{		
		String readArray[] = new String[20];
		int f = 0;
		String read = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("highscores.txt"));
			while((read = br.readLine()) != null)
			{
				System.out.println("read");
				readArray[f] = read;
				f++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null,"An error occurred");
		}
		fillTable(readArray,f);
		return f;
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
		boolean scoreEntered = false;
		int addPosition = -1;
		for(int w = 0;w < numScores; w++)
		{
			scores[w] = Integer.parseInt(tbl.getModel().getValueAt(w,1).toString());
			if(score < scores[w] && scoreEntered == false)
			{
				addPosition = w;
				scoreEntered = true;
			}
			if(score == scores[w] && scoreEntered == false)
			{
				addPosition = w+1;
				scoreEntered = true;
			}
		}
		if(addPosition != -1)
		{
			addScore(scores,score,addPosition); //addPosition = position in the highscores where new score should be added
			//changeTable(scores,addPosition);
		}
	}
	public void addScore(int[] scoreArray,int newScore,int row)
	{
		System.out.println("///////////// addScore /////////////");
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
		System.out.println("x = "+x);
		do
		{
			if(x == row)
			{
				tbl.setValueAt("ENTER NAME",row,0);
				tbl.setValueAt(newScore,row,1);
				x = -1;
			}
			else
			{
				System.out.println("x = "+x);
				tbl.setValueAt(tbl.getModel().getValueAt(x-1,0),x,0);
				tbl.setValueAt(tbl.getModel().getValueAt(x-1,1),x,1);
				x--;
			}
		}
		while(x != -1);
	}
}