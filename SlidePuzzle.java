/**
 * class SlidePuzzle generates a GUI to show a sliding puzzle.
 * It allows the user to click on JButtons with ImageIcons and move them around the puzzle to solve it.
 */




import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SlidePuzzle extends JFrame implements ActionListener
{
	private ImageIcon iconArray[];
	private ImageIcon iconArrayRandom[] = new ImageIcon[12];
	private JPanel pnl;
	private JTabbedPane tp = new JTabbedPane();
	private JButton buttons[] = new JButton[12];
	private JButton btnRandom = new JButton("Randomise");
	private JLabel lblScore;
	private int score = 0;
	
	/**
	 * run() sets the size, location, title, and default close operation of the JFrame extended by the class.
	 * @see javax.swing.JFrame
	 */
	public void run()
	{
		this.setSize(1200,675); //16:9
		this.setLocation(200,200); //size and location of JFrame set
		this.setTitle("Sliding Puzzle");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if main window is closed, program is terminated
		
		createObjects();
		
		tp.addTab("Sliding Puzzle",pnl); //adds tab to tabbedpane
		this.add(tp); //adds tabbedpane to JFrame
		this.setVisible(true);
	}
	/**
	 * createObjects() sets the size and location of JComponents declared globally in the class
	 * sets the JPanel's layout to null
	 * adds an ActionListener to each button, and a random ImageIcon to each button
	 * calls loadIcons() to load ImageIcons into arrays
	 * calls setButtons() to set size, location, ActionListener, and border of JButtons
	 * @see loadIcons()
	 * @see setButtons()
	 */
	public void createObjects()
	{
		loadIcons();
		
		pnl = new JPanel(null); //null layout of JPanel because I like it
		lblScore = new JLabel("Score : 0");
		lblScore.setSize(100,30);
		lblScore.setLocation(500,50);
		pnl.add(lblScore);
		btnRandom.setSize(100,50);
		btnRandom.setLocation(500,400);
		btnRandom.addActionListener(this); //if button is clicked then stuff should happen
		pnl.add(btnRandom);
		
		randomPuzzle rP = new randomPuzzle(); //new object of class randomPuzzle
		iconArrayRandom = rP.randomise(iconArrayRandom); //randomises image icon array
		setButtons(iconArrayRandom); //sets button icons to the random icons
	}
	/**
	 * setButtons() takes an array of ImageIcons, 12 in length
	 * it uses int variables x and y to keep track of the location of the buttons and to set the locations correctly
	 * a nested for loop ensures that the buttons take on a 2D alignment
	 * @param images, the ImageIcon array to set the button icons with
	 */
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
				buttons[count].setIcon(images[count]); //sets button's icon to icon from the array
				buttons[count].setSize(images[count].getIconWidth(),images[count].getIconHeight()); //set size of button to size of respective icon
				buttons[count].setLocation(x,y);
				buttons[count].setBorder(null);
				buttons[count].addActionListener(this); //if button is clicked then stuff should happen
				pnl.add(buttons[count]);
				x += (images[count].getIconWidth()); //used to place buttons correctly
				count++;
			}
			x = 0;
			if(count < 12)
			{
				y += images[count-1].getIconHeight(); //used to place buttons correctly
			}
		}
	}
	/**
	 * loadIcons() loads 12 images from file into iconArray, and copies each ImageIcon in this array to iconArrayRandom
	 */
	public void loadIcons() //loads image icons from files into an array, in the correct completed order
	{
		iconArray = new ImageIcon[12];
		for(int i = 0;i<iconArray.length; i++)
		{
			String currentFile = ("bart" + String.valueOf(i) + ".jpg");
			iconArray[i] = new ImageIcon(currentFile);
			iconArrayRandom[i] = iconArray[i];
		}
	}
	/**
	 * move() checks if the button clicked is not the empty space
	 * if this condition is met, it looks for the empty space. If the empty space is directly above, below, left of, or right of the button clicked,
	 * then the button clicked and the empty space swap their ImageIcons.
	 * each movement calls checkComplete. if this returns true, the user is prompted that the puzzle is complete, by calling finish().
	 * the user can then click "yes" or "no" to open the highscores, which happens when highscores() is called.
	 * @param button is the number of the button clicked by the user
	 * @see checkComplete()
	 * @see finish()
	 * @see highscores()
	 */
	public void move(int button)
	{
		Icon temp = new ImageIcon();
		if(buttons[button].getIcon() != iconArray[0]) //if the button clicked is NOT the blank one
		{
			if(button - 1 >= 0) //if button number -1 is greater than or equal to zero
			{
				if(buttons[button - 1].getIcon().equals(iconArray[0])) //if button to the left is the blank one
				{
					temp = buttons[button].getIcon(); //swaps icons
					buttons[button].setIcon(iconArray[0]);
					buttons[button - 1].setIcon(temp);
					changeScore();
				}
			}
			if(button + 1 <= 11) //if button number +1 is less than or equal to eleven
			{
				if(buttons[button + 1].getIcon().equals(iconArray[0])) //if button to the right is the blank one
				{
					temp = buttons[button].getIcon(); //swaps icons
					buttons[button].setIcon(iconArray[0]);
					buttons[button + 1].setIcon(temp);
					changeScore();
				}
			}
			if(button - 4 >= 0) //if button number -4 is greater than or equal to zero
			{
				if(buttons[button - 4].getIcon().equals(iconArray[0])) //if button above is the blank one
				{
					temp = buttons[button].getIcon(); //swaps icons
					buttons[button].setIcon(iconArray[0]);
					buttons[button - 4].setIcon(temp);
					changeScore();
				}
			}
			if(button + 4 <= 11) //if button number +4 is less than or equal to eleven
			{
				if(buttons[button + 4].getIcon().equals(iconArray[0])) //if button below is the blank one
				{
					temp = buttons[button].getIcon(); //swaps icons
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
			if(showHighscores == 0) //if user clicked "yes" then the highscores are shown, and they may enter their scores
			{
				highscores(score);
			}
		}
	}
	public void changeScore()
	{
		score++; //increases score by 1
		lblScore.setText("Score : "+score); //updates score label
	}
	/**
	 * checkComplete rather checks if the puzzle is incomplete
	 * if it is not incomplete, then it is complete, and true is returned.
	 * @return the completion state of the puzzle
	 */
	public boolean checkComplete()
	{
		boolean check = true;
		for(int i = 0;i<iconArray.length;i++)
		{
			if(buttons[i].getIcon().equals(iconArray[i])==false) //if any button doesn't have the correct icon then the check fails
			{
				check = false;
			}
		}
		return check;
	}
	/**
	 * opens a new window to prompt the user to tell them the puzzle is complete, and to ask if they want to open the highscores
	 */
	public int finish()
	{
		return JOptionPane.showConfirmDialog(this,"Show Highscores?","Puzzle Complete!",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE); //pops up with an option dialog for the user, with yes or no.
	}
	/**
	 * opens the highscores
	 */
	public void highscores(int highscore)
	{
		highscores hs = new highscores(); //new object of class highscores
		hs.run(highscore);
	}
	public void removeIcons() //actually removes buttons, but the name seems good to me
	{
		for(int u = 0;u < buttons.length;u++)
		{
			pnl.remove(buttons[u]); //removes each button from the panel so they may be added back with a different icon
		}
	}
	public void actionPerformed(ActionEvent e) //whichever button is clicked, the move() method is called with the corresponding button number
	{
		for(int d = 0;d<buttons.length;d++)
		{
			if(e.getSource() == buttons[d])
			{
				move(d);
			}
		}
		if(e.getSource() == btnRandom)
		{
			removeIcons();
			randomPuzzle rPuzz = new randomPuzzle(); //new object of class randomPuzzle
			for(int b = 0;b<iconArrayRandom.length;b++)
			{
				iconArrayRandom[b] = iconArray[b]; //resets random icons to the correct ones ready to be randomised again
			}
			ImageIcon newRandomIcons[] = rPuzz.randomise(iconArrayRandom); 
			setButtons(newRandomIcons);
			repaint(); //forces JComponents to be repainted. Used to make the icons on the buttons change visibly.
		}
	}
	public static void main(String[] args) //where the party begins
	{
		SlidePuzzle sp = new SlidePuzzle();
		sp.run();
	}
}