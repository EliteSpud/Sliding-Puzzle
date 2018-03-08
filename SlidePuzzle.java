import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SlidePuzzle extends JFrame implements ActionListener
{
	JPanel pnl;
	JTabbedPane tp = new JTabbedPane();
	JButton buttons[] = new JButton[12];
	JLabel emptyTile;
	
	public void run()
	{
		this.setSize(500,400);
		this.setLocation(500,500);
		this.setTitle("Sliding Puzzle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createObjects();
		
		tp.addTab("Sliding Puzzle",pnl);
		this.add(tp);
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
				buttons[count].addActionListener(this);
				pnl.add(buttons[count]);
				x += 100;
				count++;
			}
			x = 0;
			y += 100;
		}
		loadIcons();
	}
	public void loadIcons()
	{
		ImageIcon iconArray[] = new ImageIcon[12];
		for(int i = 0;i<iconArray.length; i++)
		{
			String currentFile = ("bart" + String.valueOf(i));
			System.out.println("Current file : "+currentFile);
			iconArray[i] = new ImageIcon(currentFile);
			buttons[i].setIcon(iconArray[i]);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == buttons[0])
		{
			System.out.println("Button 0");
		}
		if(e.getSource() == buttons[1])
		{
			System.out.println("Button 1");
		}
		if(e.getSource() == buttons[2])
		{
			System.out.println("Button 2");
		}
		if(e.getSource() == buttons[3])
		{
			System.out.println("Button 3");
		}
		if(e.getSource() == buttons[4])
		{
			System.out.println("Button 4");
		}
		if(e.getSource() == buttons[5])
		{
			System.out.println("Button 5");
		}
		if(e.getSource() == buttons[6])
		{
			System.out.println("Button 6");
		}
		if(e.getSource() == buttons[7])
		{
			System.out.println("Button 7");
		}
		if(e.getSource() == buttons[8])
		{
			System.out.println("Button 8");
		}
		if(e.getSource() == buttons[9])
		{
			System.out.println("Button 9");
		}
		if(e.getSource() == buttons[10])
		{
			System.out.println("Button 10");
		}
		if(e.getSource() == buttons[11])
		{
			System.out.println("Button 11");
		}
	}
	public static void main(String[] args)
	{
		SlidePuzzle sp = new SlidePuzzle();
		sp.run();
	}
}