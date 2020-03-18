package hw4;
import api.*;
import ui.*;
import java.awt.*;

public class LPiece extends AbstractPiece
{
	
	
	
	public LPiece(Position position, Icon[] icons)
	{
		setPosition(position);
		Cell[] lShape = new Cell[4];
		if(icons.length != lShape.length)
		{
			throw new IllegalArgumentException();
		}
		lShape[0] = new Cell(icons[0], new Position(0,0));
		lShape[1] = new Cell(icons[1], new Position(0,1));
		lShape[2] = new Cell(icons[2], new Position(1,1));
		lShape[3] = new Cell(icons[3], new Position(2,1));	
		setCells(lShape);
	}
	
	@Override
	public void transform()
	{
		//Flips across vertical center line (L-Shape)
		int curRow = 0, curCol = 0;
		Cell[] in = getCells();
		for(int i =0; i < in.length; i++)
		{
			curRow = in[i].getRow();
			curCol = in[i].getCol();
			if(curRow == 0 && curCol == 0)
			{
				curCol = 2;
			}
			else if(curRow == 0 && curCol == 2)
			{
				curCol = 0;
			}
			in[i].setRow(curRow);
			in[i].setCol(curCol);
		}
		setCells(in);
	}
}
