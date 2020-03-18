package hw4;
import api.*;

public class DiagonalPiece extends AbstractPiece
{
	public DiagonalPiece(Position position, Icon[] icons)
	{
		setPosition(position);
		Cell[] diag = new Cell[2];
		if(icons.length != diag.length)
		{
			throw new IllegalArgumentException();
		}
		diag[0] = new Cell(icons[0], new Position(0,0));
		diag[1] = new Cell(icons[1], new Position(1,1));
		setCells(diag);
	}
	
	@Override
	public void transform()
	{
		Cell[] myCells = getCells();
		int curRow = 0 , curCol = 0;
		for(int i = 0; i < myCells.length; i++)
		{
			curRow = myCells[i].getRow();
			curCol = myCells[i].getCol();
			if(curCol > 0)
			{
				curCol -= 1;
			}
			else
			{
				curCol += 1;
			}
			myCells[i].setRow(curRow);
			myCells[i].setCol(curCol);
		}
		setCells(myCells);
	}
}
