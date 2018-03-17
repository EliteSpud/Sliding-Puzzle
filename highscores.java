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
		checkScore();
	}
	public void createObjects()
	{
		tbl.setSize(325,400);
		tbl.setLocation(25,25);
		tbl.setRowHeight(20);
		tbl.setBorder(BorderFactory.createLineBorder(Color.black));
		pnl.add(tbl);
	}
	public void checkScore()
	{		
		String readArray[] = new String[20];
		int f = 1;
		String read = "";
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("highscores.txt"));
			while((read = br.readLine()) != null)
			{
				System.out.println("read");
				f++;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			JOptionPane.showMessageDialog(null,"An error occurred");
		}
	}
}