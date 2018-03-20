import javax.swing.*;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.awt.event.*;
public class highscores extends JFrame implements ActionListener
{
	JPanel pnl = new JPanel(null);
	JTable tbl = new JTable(20,2);
	JButton btnSubmit = new JButton("Submit");
	JTextField txtName = new JTextField();
	int addPosition = -1; //rogue value
	public void run(int highscore)
	{
		this.setSize(500,600);
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
		
		btnSubmit.setSize(100,50);
		btnSubmit.setLocation(375,375);
		btnSubmit.addActionListener(this);
		pnl.add(btnSubmit);
		
		txtName.setSize(100,30);
		txtName.setLocation(375,340);
		pnl.add(txtName);
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
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnSubmit)
		{
			try
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter("highscores.txt")); //opens a new bufferedwriter to write into highscores file
				for(int i = 0;i<tbl.getModel().getRowCount();i++)
				{
					if(i == addPosition) //when reached the row to enter the new score, enter the new score and name
					{
						bw.write(txtName.getText() + ":" + tbl.getModel().getValueAt(i,1));
						bw.newLine();
					}
					else //writing the other scores
					{
						bw.write(tbl.getModel().getValueAt(i,0) + ":" + tbl.getModel().getValueAt(i,1));
						bw.newLine();
					}
				}
				bw.close(); //close buffer to write all text to file. not closing buffer results in a blank file.
			}
			catch(Exception t)
			{
				JOptionPane.showMessageDialog(null,"Something went wrong with writing highscores"); //show error message
				System.out.println(t);
			}
			
			tbl.getModel().setValueAt(txtName.getText(),addPosition,0); //sets field with "ENTER NAME" to the name given in the text box
			repaint(); //refreshes all JComponents so the JTable will show the new name
		}
	}
}