package hw4;
import api.*;

public class IPiece extends AbstractPiece 
{

		public IPiece(Position position, Icon[] icons)
		{
			setPosition(position);
			Cell[] iShape = new Cell[3];
			if(icons.length != iShape.length)
			{
				throw new IllegalArgumentException();
			}
			iShape[0] = new Cell(icons[0], new Position(0,1));
			iShape[1] = new Cell(icons[1], new Position(1,1));
			iShape[2] = new Cell(icons[2], new Position(2,1));
			setCells(iShape);
		}
		
		@Override
		public void transform()
		{
			//Does nothing
		}
}
