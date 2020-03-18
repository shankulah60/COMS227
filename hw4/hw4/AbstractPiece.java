package hw4;
import java.util.Random;
import api.*;
import examples.SamplePiece;

public abstract class AbstractPiece implements Piece
{
	private Position position;
	private Cell[] cells;
	private BasicGenerator g;
	private Random rand;
	
	public AbstractPiece()
	{
		g = new BasicGenerator(rand);
	}
	
	
	@Override
	public Piece clone()
	{
		try
	    {
	      // call the Object clone() method to create a shallow copy
	      AbstractPiece s = (AbstractPiece) super.clone();

	      // then make it into a deep copy (note there is no need to copy the position,
	      // since Position is immutable, but we have to deep-copy the cell array
	      // by making new Cell objects 
	      
	      s.cells = new Cell[cells.length];
	      for (int i = 0; i < cells.length; ++i)
	      {
	      s.cells[i] = new Cell(cells[i]);
	      }
	      return s;	         	      	      	      
	    }
	    catch (CloneNotSupportedException e)
	    {
	      // can't happen, since we know the superclass is cloneable
	      return null;
	    }    
	  }
	


	@Override
	public void cycle()
	{
		//1,2,3,4 -> 2341
		Cell[] in = getCells();
		Cell[] out = new Cell[in.length]; 
		for(int i=1;i<in.length; i++)
		{
			out[i]= new Cell(in[i]);
			out[i].setIcon(in[i-1].getIcon());	
			
		}
		out[0]= new Cell(in[0]);
		out[0].setIcon(in[in.length-1].getIcon());
		setCells(out);
	}
	
	
	 @Override
	public Cell[] getCells()
	{
		Cell[] copy = new Cell[cells.length];
		for(int i = 0; i < cells.length; i++)
		{
			copy[i] = new Cell(cells[i]);
		}
		return copy;
	}
	 
	 
	 @Override
	public Cell[] getCellsAbsolute() 
	{
		Cell[] ret = new Cell[cells.length];
		int curRow, curCol;
		
		for(int i = 0; i < cells.length; i++)
		{
			curRow = cells[i].getRow() + position.row();
			curCol = cells[i].getCol() + position.col();
			Icon b = cells[i].getIcon();
			ret[i] = new Cell(b, new Position(curRow, curCol));
		}
	    return ret;
	}
	
	 @Override
	public Position getPosition()
	{
		return position;
	}
	 
	
	protected int getPositionRow()
	{
		return position.row();
	}
	
	protected int getPositionCol()
	{
		return position.col();
	}
	

	protected void setPosition(Position p)
	{
		position = p;
	}
	
	
	@Override
	public void setCells(Cell[] givenCells)
	{
		cells = new Cell[givenCells.length];
		for(int i = 0; i < givenCells.length; i++)
		{
			cells[i] = givenCells[i];
		}
	}
	
	
	 @Override
	public void shiftDown()
	{
		position = new Position(position.row() + 1, position.col());
	}
	 @Override
	public void shiftLeft()
	{
		position = new Position(position.row(), position.col() - 1);
	}
	 @Override
	public void shiftRight()
	{
		position = new Position(position.row(), position.col() + 1);
	}
	 @Override
	public void transform()
	{
		
	}

	
}
