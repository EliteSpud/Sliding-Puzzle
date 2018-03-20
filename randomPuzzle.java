import java.util.Random;
import javax.swing.JButton;
import javax.swing.ImageIcon;
public class randomPuzzle
{
	public ImageIcon[] randomise(ImageIcon[] icons)
	{
		Random r = new Random();
		int[] buttonNumbers = {0,1,2,3,4,5,6,7,8,9,10,11};
		int moves = r.nextInt(250 - 50 + 1) + 50; //generates a random number between 50 and 250 inclusive
		//int moves = 10;
		System.out.println("MOVES = "+moves);
		int greyTile  = 0;
		int lastTile = -1;
		int temp = -1;
		int[] possibleMoves = new int[4];
		int possibleMovesCount = -1;
		for(int i = 0; i < moves; i++)
		{
			possibleMovesCount = -1;
			/*if(i == 0)
			{
				int firstTile = r.nextInt(1);
				if(firstTile == 0)
				{
					swap(greyTile,1,icons);
					lastTile = 0;
					greyTile = 1;
				}
				else
				{
					swap(greyTile,4,icons);
					lastTile = 0;
					greyTile = 4;
				}
			}
			if((greyTile - 1 >= 0) && (greyTile - 1 != lastTile))
			{
				possibleMovesCount++;
				possibleMoves[possibleMovesCount] = greyTile - 1;
				//System.out.println("Possible Moves Count = "+possibleMovesCount);
			}
			if((greyTile + 1 >= 0) && (greyTile + 1 != lastTile))
			{
				possibleMovesCount++;
				possibleMoves[possibleMovesCount] = greyTile + 1;
				//System.out.println("Possible Moves Count = "+possibleMovesCount);
			}
			if((greyTile - 4 >= 0) && (greyTile - 4 != lastTile))
			{
				possibleMovesCount++;
				possibleMoves[possibleMovesCount] = greyTile - 4;
				//System.out.println("Possible Moves Count = "+possibleMovesCount);
			}
			if((greyTile + 4 >= 0) && (greyTile + 4 != lastTile))
			{
				possibleMovesCount++;
				possibleMoves[possibleMovesCount] = greyTile + 4;
				//System.out.println("Possible Moves Count = "+possibleMovesCount);
			}*/
			if(greyTile == 0)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 1;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 4;
				}
			}
			if(greyTile == 1)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 0;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 2;
				}
				if(lastTile != 1)
				{
					possibleMoves[2] = 5;
				}
			}
			if(greyTile == 2)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 1;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 3;
				}
				if(lastTile != 1)
				{
					possibleMoves[2] = 6;
				}
			}
			if(greyTile == 3)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 2;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 7;
				}
			}
			if(greyTile == 4)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 0;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 5;
				}
				if(lastTile != 1)
				{
					possibleMoves[2] = 8;
				}
			}
			if(greyTile == 5)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 1;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 4;
				}
				if(lastTile != 1)
				{
					possibleMoves[2] = 6;
				}
				if(lastTile != 1)
				{
					possibleMoves[3] = 9;
				}
			}
			if(greyTile == 6)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 2;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 5;
				}
				if(lastTile != 1)
				{
					possibleMoves[2] = 7;
				}
				if(lastTile != 1)
				{
					possibleMoves[3] = 10;
				}
			}
			if(greyTile == 7)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 3;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 6;
				}
				if(lastTile != 1)
				{
					possibleMoves[2] = 11;
				}
			}
			if(greyTile == 8)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 4;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 9;
				}
			}
			if(greyTile == 9)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 5;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 8;
				}
				if(lastTile != 1)
				{
					possibleMoves[2] = 10;
				}
			}
			if(greyTile == 10)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 6;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 9;
				}
				if(lastTile != 1)
				{
					possibleMoves[2] = 11;
				}
			}
			if(greyTile == 11)
			{
				if(lastTile != 1)
				{
					possibleMoves[0] = 7;
				}
				if(lastTile != 1)
				{
					possibleMoves[1] = 10;
				}
			}
			int possibleMovesIndex = r.nextInt(2);
			System.out.println("Possible Moves Count = "+possibleMovesCount);
			System.out.println("Possible Moves Index = "+possibleMovesIndex);
			System.out.println("LAST TILE = "+lastTile);
			System.out.println("GREY TILE = "+greyTile);
			swap(greyTile,possibleMoves[possibleMovesIndex],icons);
			lastTile = greyTile;
			greyTile = possibleMoves[possibleMovesIndex];
		}
		return icons;
	}
	public void swap(int grey, int tile, ImageIcon[] iconArray)
	{
		ImageIcon tempIcon = iconArray[tile];
		iconArray[tile] = iconArray[grey];
		iconArray[grey] = tempIcon;
		System.out.println("MOVED TO "+tile);
	}
}