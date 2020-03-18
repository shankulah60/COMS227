package hw4;
import api.*;

public class SnakePiece extends AbstractPiece
{
	private static final Position[] sequence =
		 {
		 new Position(0, 0),
		 new Position(0, 1),
		 new Position(0, 2),
		 new Position(1, 2),
		 new Position(1, 1),
		 new Position(1, 0),
		 new Position(2, 0),
		 new Position(2, 1),
		 new Position(2, 2),
		 new Position(1, 2),
		 new Position(1, 1),
		 new Position(1, 0),
		 };
	private int counter = 0;
	private boolean fromBelow = false;
	private boolean fromAbove = false;
	
	public SnakePiece(Position position, Icon[] icons)
	{
		setPosition(position);
		Cell[] snake = new Cell[4];
		if(icons.length != snake.length)
		{
			throw new IllegalArgumentException();
		}
		snake[0] = new Cell(icons[0], new Position(0,0));
		snake[1] = new Cell(icons[1], new Position(1,0));
		snake[2] = new Cell(icons[2], new Position(1,1));
		snake[3] = new Cell(icons[3], new Position(1,2));
		setCells(snake);
	}
	
	
	public void transform()
	{
		Cell[] copied = getCellsAbsolute();
		
		counter += 1;
		copied[0].setPosition(sequence[checkcounter(counter)]);
		copied[1].setPosition(sequence[checkcounter(counter - 1)]);
		copied[2].setPosition(sequence[checkcounter(counter - 2)]);
		copied[3].setPosition(sequence[checkcounter(counter - 3)]);
		
		setCells(copied);
	}
		
	private int checkcounter(int i)
	{
		int j=0;
		if(i<0)
			j=12+i;
		else
			j=i%12;
		return j;
	}
			
		
		

	}
