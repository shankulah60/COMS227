package hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import api.AbstractGame;
import api.Generator;
import api.Icon;
import api.Position;
import javafx.scene.paint.Color;

public class BlockAddiction extends AbstractGame
{

	public BlockAddiction(int height, int width, Generator gen, int preFillRows)
	{
		super(height,width,gen);
		//prefill rows
		//start
		
		if(preFillRows> 0)
		{
			int start= getHeight()-preFillRows;
			
			for(int i=start; i<getHeight();i++)
			{
				for(int j=0; j<getWidth(); j++)	
	            {
					//check for both even and both odd
					if(((i%2==0)&&(j%2==0)) || (!(i%2==0)&&!(j%2==0)))
					setBlock( i, j, gen.randomIcon());
			    }
			}
		}
		
	}
	
	public BlockAddiction(int height, int width, Generator gen)
	{
		super(height,width,gen);
	}
	
	
	
	  public  List<Position> determinePositionsToCollapse()
	  {
		  List<Position> positions = new ArrayList<>();
		  List<Position> positions2 = new ArrayList<>();
		  List<Position> neighbors = new ArrayList<>();
		  List<Position> temp = new ArrayList<>();
		  int neighcnt =0;
		  Icon icn;
		    
		  
		  for (int row = 0; row < getHeight(); ++row)
		    {
		    for (int col = 0; col < getWidth(); ++col)
		        {
		    	 icn=(getIcon(row,col));
		    	
		    	neighbors= getNeighbors( row,col );
		          
		          for(int i =0;i<neighbors.size(); i++)
		            {
		        	 
		        	  Icon c= getIcon(neighbors.get(i).row(),neighbors.get(i).col());
		        	  if((c!=null) && (icn!=null) && icn.equals(c))
		        	  {
		        		  neighcnt++;
		        		  temp.add(neighbors.get(i));
		        		  
		        	  }
		  		    }
		          if(neighcnt>=2)
		          {
		    	     positions.add(new Position(row, col));
		    	     for(int i=0;i<temp.size();i++)
		    	     {
		    	    	 positions.add(new Position(temp.get(i).row(),temp.get(i).col()));
		    	    	 
		    	     }
		    	     
		          }
		          neighcnt=0;
		          temp.clear();
		        }
		      }
		    
		    
		    //check for duplicates
		    for(int i=0;i<positions.size();i++)
		    {
		    	if(!(positions2.contains(positions.get(i))))
		    	{
		    	  positions2.add(positions.get(i));
		    	}
		    			
		    }
		    //now sort
		    Collections.sort(positions2);
		    return positions2;
	  }
	
	  //this method ewill return the neighbors of a cell
	  private List<Position> getNeighbors( int x, int y ) {
		    List<Position> neighbors = new ArrayList<>();
            int maxX= getHeight();
            int maxY = getWidth();
		    neighbors = new ArrayList<Position>();
		    if (x-1 > 0 && y>0 && x-1<maxX && y<maxY)
		        neighbors.add(new Position(x-1, y));
		    if (x > 0 && y-1>0 && x<maxX && y-1<maxY)
		    	 neighbors.add(new Position(x, y-1));
		       
		    if (x+1 > 0 && y>0 && x+1<maxX && y<maxY)
		    	 neighbors.add(new Position(x+1, y));
		      
		    if (x> 0 && y+1>0 && x<maxX && y+1<maxY)
		    	 neighbors.add(new Position(x, y+1));
		    
		 
		    

		    return neighbors;
		}
}
