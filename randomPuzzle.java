import java.util.Random;
import javax.swing.JButton;
import javax.swing.ImageIcon;
public class randomPuzzle
{
	public ImageIcon[] randomise(ImageIcon[] icons) //takes an array of ImageIcons and returns it randomised. given ImageIcon array must always be in the "completed" order.
	{
		Random r = new Random(); //new object of class random
		int moves = r.nextInt(250 - 50 + 1) + 50; //generates a random number between 50 and 250 inclusive
		int greyTile  = 0;
		int lastTile = -1;
		int[] possibleMoves = new int[4]; //array used to contain numbers of tiles that the grey one can be moved to
		int possibleMovesCount = -1; //counts how many possible moves there are. if there's only 1, this variable is 0. Helps with indexing of arrays.
		for(int i = 0; i < moves; i++)
		{
			possibleMovesCount = -1;
			if(i == 0) //this makes the first move
			{
				swap(greyTile,1,icons);
				lastTile = 0;
				greyTile = 1;
			}
			
			if((greyTile - 1 >= 0) && (greyTile - 1 != lastTile)) //if grey tile has a tile left of it that hasn't just been moved then that tile is a possible move
			{
				if(greyTile == 4 || greyTile == 8)
				{
					possibleMovesCount++;
					possibleMoves[possibleMovesCount] = greyTile - 1;
				}
			}
			if((greyTile + 1 <= 11) && (greyTile + 1 != lastTile))//if grey tile has a tile right of it that hasn't just been moved then that tile is a possible move
			{
				if(greyTile == 3 || greyTile == 7)
				{
					possibleMovesCount++;
					possibleMoves[possibleMovesCount] = greyTile + 1;
				}
			}
			if((greyTile - 4 >= 0) && (greyTile - 4 != lastTile))//if grey tile has a tile above it that hasn't just been moved then that tile is a possible move
			{
				possibleMovesCount++;
				possibleMoves[possibleMovesCount] = greyTile - 4;
			}
			if((greyTile + 4 <= 11) && (greyTile + 4 != lastTile))//if grey tile has a tile below it that hasn't just been moved then that tile is a possible move
			{
				possibleMovesCount++;
				possibleMoves[possibleMovesCount] = greyTile + 4;
			}
			int possibleMovesIndex = r.nextInt(2); //randomly chooses one of the possible moves, of which there can only ever be 3
			swap(greyTile,possibleMoves[possibleMovesIndex],icons); 
			lastTile = greyTile;
			greyTile = possibleMoves[possibleMovesIndex];
		}
		return icons; //returns randomised icon array
	}
	public void swap(int grey, int tile, ImageIcon[] iconArray) //swaps the 2 icons selected
	{
		ImageIcon tempIcon = iconArray[tile];
		iconArray[tile] = iconArray[grey];
		iconArray[grey] = tempIcon;
	}
}