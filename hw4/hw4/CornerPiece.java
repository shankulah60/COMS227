package hw4;
import api.*;

public class CornerPiece extends AbstractPiece
{
	public CornerPiece(Position position, Icon[] icons)
	{
		setPosition(position);
		Cell[] corn = new Cell[3];
		if(icons.length != corn.length)
		{
			throw new IllegalArgumentException();
		}
		corn[0] = new Cell(icons[0], new Position(0,0));
		corn[1] = new Cell(icons[1], new Position(1,0));
		corn[2] = new Cell(icons[2], new Position(1,1));
		setCells(corn);		
	}
	
	@Override
	public void transform()
	{
		int curRow = getPositionRow();
		int curCol = getPositionCol();
		
		if(curRow == 0 && curCol == 0)
		{
			curCol += 1;
		}
		else if(curRow == 0 && curCol == 1)
		{
			curRow += 1;
		}
		else if(curRow == 1 && curCol == 1)
		{
			curCol -= 1;
		}
		else
		{
			curRow -= 1;
		}
		
		Position mine = new Position(curRow, curCol);
		setPosition(mine);
	}
}
