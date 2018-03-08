import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SlidePuzzle extends JFrame implements ActionListener
{
	private ImageIcon iconArray[];
	private JPanel pnl;
	private JTabbedPane tp = new JTabbedPane();
	private JButton buttons[] = new JButton[12];
	private JLabel emptyTile;
	
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
		loadIcons();
		pnl = new JPanel(null);
		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 4; k++)
			{
				buttons[count] = new JButton();
				buttons[count].setIcon(iconArray[count]);
				buttons[count].setSize(iconArray[count].getIconWidth(),iconArray[count].getIconHeight());
				buttons[count].setLocation(x,y);
				buttons[count].setBorder(null);
				buttons[count].addActionListener(this);
				pnl.add(buttons[count]);
				x += (iconArray[count].getIconWidth());
				count++;
			}
			x = 0;
			if(count < 12)
			{
				y += iconArray[count-1].getIconHeight();
			}
		}
	}
	public void loadIcons()
	{
		iconArray = new ImageIcon[12];
		for(int i = 0;i<iconArray.length; i++)
		{
			String currentFile = ("bart" + String.valueOf(i) + ".jpg");
			System.out.println("Current file : "+currentFile+".jpg");
			iconArray[i] = new ImageIcon(currentFile);
		}
	}
	public void move(int button)
	{
		System.out.println("move");
		Icon temp = new ImageIcon();
		if(buttons[button].getIcon() != iconArray[0]) //if the button clicked is NOT the blank one
		{
			if(button - 1 >= 0) //if button number -1 is greater than or equal to zero
			{
				if(buttons[button - 1].getIcon() == iconArray[0]) //if button to the left is the blank one
				{
					temp = buttons[button].getIcon();
					buttons[button].setIcon(iconArray[0]);
					buttons[button - 1].setIcon(temp);
				}
			}
			if(button + 1 <= 11) //if button number +1 is less than or equal to eleven
			{
				if(buttons[button + 1].getIcon() == iconArray[0]) //if button to the right is the blank one
				{
					temp = buttons[button].getIcon();
					buttons[button].setIcon(iconArray[0]);
					buttons[button + 1].setIcon(temp);
				}
			}
			if(button - 4 >= 0) //if button number -4 is greater than or equal to zero
			{
				if(buttons[button - 4].getIcon() == iconArray[0]) //if button above is the blank one
				{
					temp = buttons[button].getIcon();
					buttons[button].setIcon(iconArray[0]);
					buttons[button - 4].setIcon(temp);
				}
			}
			if(button + 4 <= 11) //if button number +4 is less than or equal to eleven
			{
				if(buttons[button + 4].getIcon() == iconArray[0]) //if button below is the blank one
				{
					temp = buttons[button].getIcon();
					buttons[button].setIcon(iconArray[0]);
					buttons[button + 4].setIcon(temp);
				}
			}
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == buttons[0])
		{
			System.out.println("Button 0");
			move(0);
		}
		if(e.getSource() == buttons[1])
		{
			System.out.println("Button 1");
			move(1);
		}
		if(e.getSource() == buttons[2])
		{
			System.out.println("Button 2");
			move(2);
		}
		if(e.getSource() == buttons[3])
		{
			System.out.println("Button 3");
			move(3);
		}
		if(e.getSource() == buttons[4])
		{
			System.out.println("Button 4");
			move(4);
		}
		if(e.getSource() == buttons[5])
		{
			System.out.println("Button 5");
			move(5);
		}
		if(e.getSource() == buttons[6])
		{
			System.out.println("Button 6");
			move(6);
		}
		if(e.getSource() == buttons[7])
		{
			System.out.println("Button 7");
			move(7);
		}
		if(e.getSource() == buttons[8])
		{
			System.out.println("Button 8");
			move(8);
		}
		if(e.getSource() == buttons[9])
		{
			System.out.println("Button 9");
			move(9);
		}
		if(e.getSource() == buttons[10])
		{
			System.out.println("Button 10");
			move(10);
		}
		if(e.getSource() == buttons[11])
		{
			System.out.println("Button 11");
			move(11);
		}
	}
	public static void main(String[] args)
	{
		SlidePuzzle sp = new SlidePuzzle();
		sp.run();
	}
}