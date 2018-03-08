import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SlidePuzzle extends JFrame implements ActionListener
{
	JPanel pnl;
	JButton buttons[] = new JButton[12];
	JLabel emptyTile;
	
	public void run()
	{
		this.setSize(400,300);
		this.setLocation(500,500);
		this.setTitle("Sliding Puzzle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createObjects();
		
		this.add(pnl);
		this.setVisible(true);
	}
	public void createObjects()
	{
		int x = 0;
		int y = 0;
		int count = 0;
		pnl = new JPanel(null);
		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 4; k++)
			{
				buttons[count] = new JButton();
				buttons[count].setSize(100,100);
				buttons[count].setLocation(x,y);
				pnl.add(buttons[count]);
				x += 100;
				count++;
			}
			x = 0;
			y += 100;
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		
	}
	public static void main(String[] args)
	{
		SlidePuzzle sp = new SlidePuzzle();
		sp.run();
	}
}