import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SlidePuzzle extends JFrame implements ActionListener
{
	private ImageIcon iconArray[];
	private ImageIcon iconArrayRandom[];
	private JPanel pnl;
	private JTabbedPane tp = new JTabbedPane();
	private JButton buttons[] = new JButton[12];
	private JButton btnRandom = new JButton("Randomise");
	private JLabel lblScore;
	private int score = 0;
	
	public void run()
	{
		this.setSize(1200,675); //16:9
		this.setLocation(200,200);
		this.setTitle("Sliding Puzzle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createObjects();
		
		tp.addTab("Sliding Puzzle",pnl);
		this.add(tp);
		this.setVisible(true);
	}
	public void createObjects()
	{
		loadIcons();
		
		pnl = new JPanel(null);
		lblScore = new JLabel("Score : 0");
		lblScore.setSize(100,30);
		lblScore.setLocation(500,50);
		pnl.add(lblScore);
		btnRandom.setSize(100,50);
		btnRandom.setLocation(500,400);
		btnRandom.addActionListener(this);
		pnl.add(btnRandom);
		
		randomPuzzle rP = new randomPuzzle();
		iconArrayRandom = rP.randomise(iconArrayRandom);
		setButtons(iconArrayRandom);
	}
	public void setButtons(ImageIcon[] images)
	{
		int x = 0;
		int y = 0;
		int count = 0;
		for(int i = 0; i < 3; i++)
		{
			for(int k = 0; k < 4; k++)
			{
				buttons[count] = new JButton();
				buttons[count].setIcon(images[count]);
				buttons[count].setSize(images[count].getIconWidth(),images[count].getIconHeight());
				buttons[count].setLocation(x,y);
				buttons[count].setBorder(null);
				buttons[count].addActionListener(this);
				pnl.add(buttons[count]);
				x += (images[count].getIconWidth());
				count++;
			}
			x = 0;
			if(count < 12)
			{
				y += images[count-1].getIconHeight();
			}
		}
	}
	public void loadIcons()
	{
		iconArray = new ImageIcon[12];
		iconArrayRandom = new ImageIcon[12];
		for(int i = 0;i<iconArray.length; i++)
		{
			String currentFile = ("bart" + String.valueOf(i) + ".jpg");
			//System.out.println("Current file : "+currentFile+".jpg");
			iconArray[i] = new ImageIcon(currentFile);
			iconArrayRandom[i] = iconArray[i];
		}
	}
	public void move(int button)
	{
		
		//System.out.println("move");
		Icon temp = new ImageIcon();
		if(buttons[button].getIcon() != iconArray[0]) //if the button clicked is NOT the blank one
		{
			if(button - 1 >= 0) //if button number -1 is greater than or equal to zero
			{
				if(buttons[button - 1].getIcon().equals(iconArray[0])) //if button to the left is the blank one
				{
					temp = buttons[button].getIcon();
					buttons[button].setIcon(iconArray[0]);
					buttons[button - 1].setIcon(temp);
					changeScore();
				}
			}
			if(button + 1 <= 11) //if button number +1 is less than or equal to eleven
			{
				if(buttons[button + 1].getIcon().equals(iconArray[0])) //if button to the right is the blank one
				{
					temp = buttons[button].getIcon();
					buttons[button].setIcon(iconArray[0]);
					buttons[button + 1].setIcon(temp);
					changeScore();
				}
			}
			if(button - 4 >= 0) //if button number -4 is greater than or equal to zero
			{
				if(buttons[button - 4].getIcon().equals(iconArray[0])) //if button above is the blank one
				{
					temp = buttons[button].getIcon();
					buttons[button].setIcon(iconArray[0]);
					buttons[button - 4].setIcon(temp);
					changeScore();
				}
			}
			if(button + 4 <= 11) //if button number +4 is less than or equal to eleven
			{
				if(buttons[button + 4].getIcon().equals(iconArray[0])) //if button below is the blank one
				{
					temp = buttons[button].getIcon();
					buttons[button].setIcon(iconArray[0]);
					buttons[button + 4].setIcon(temp);
					changeScore();
				}
			}
		}
		boolean complete = checkComplete();
		if(complete == true)
		{
			int showHighscores = finish();
			if(showHighscores == 0)
			{
				highscores(score);
			}
		}
	}
	public void changeScore()
	{
		score++;
		lblScore.setText("Score : "+score);
	}
	public boolean checkComplete()
	{
		boolean check = true;
		for(int i = 0;i<iconArray.length;i++)
		{
			if(buttons[i].getIcon().equals(iconArray[i])==false)
			{
				check = false;
			}
		}
		return check;
	}
	public int finish()
	{
		return JOptionPane.showConfirmDialog(this,"Show Highscores?","Puzzle Complete!",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE);
	}
	public void highscores(int highscore)
	{
		highscores hs = new highscores();
		hs.run(highscore);
	}
	public void removeIcons()
	{
		for(int u = 0;u < buttons.length;u++)
		{
			pnl.remove(buttons[u]);
		}
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == buttons[0])
		{
			move(0);
		}
		if(e.getSource() == buttons[1])
		{
			move(1);
		}
		if(e.getSource() == buttons[2])
		{
			move(2);
		}
		if(e.getSource() == buttons[3])
		{
			move(3);
		}
		if(e.getSource() == buttons[4])
		{
			move(4);
		}
		if(e.getSource() == buttons[5])
		{
			move(5);
		}
		if(e.getSource() == buttons[6])
		{
			move(6);
		}
		if(e.getSource() == buttons[7])
		{
			move(7);
		}
		if(e.getSource() == buttons[8])
		{
			move(8);
		}
		if(e.getSource() == buttons[9])
		{
			move(9);
		}
		if(e.getSource() == buttons[10])
		{
			move(10);
		}
		if(e.getSource() == buttons[11])
		{
			move(11);
		}
		
		if(e.getSource() == btnRandom)
		{
			removeIcons();
			randomPuzzle rPuzz = new randomPuzzle();
			ImageIcon newRandomIcons[] = rPuzz.randomise(iconArray);
			setButtons(newRandomIcons);
			repaint();
		}
	}
	public static void main(String[] args)
	{
		SlidePuzzle sp = new SlidePuzzle();
		sp.run();
	}
}