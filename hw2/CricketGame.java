package hw2;

import static api.Defaults.*;
import static api.Outcome.*;
import api.Outcome;


/**
 * This class is a simulation of the game of cricket.
 * @author Shounak Lahiri
 **/
public class CricketGame 
{
	/**
	 * @param MAXBOWLS
	 * 	The maximum number of bowls per over
	 **/
	private int MAXBOWLS;
	/**
	 * @param MAXOVERPERINN
	 * 	The maximum number of overs per inning
	 **/
	private int MAXOVERPERINN;
	/**
	 * @param MAXINN
	 * 	The maximum number of innings in a game
	 **/
	private int MAXINN;
	/**
	 * @param MAXPLAYERS
	 * 	The maximum number of playes per team
	 **/
	private int MAXPLAYERS;
	
	/**
	 * @param score0
	 * 	The score for team 0
	 **/
	private int score0;
	/**
	 * @param score1
	 * 	The score for team 1
	 **/
	private int score1;
	/**
	 * @param outs0
	 * 	The number of outs for team 0
	 **/
	private int outs0;
	/**
	 * @param outs1
	 * 	The number of outs for team 1
	 **/
	private int outs1;
	/**
	 * @param bowls0
	 * 	The number of bowls for team 0
	 **/
	private int bowls0;
	/**
	 * @param bowls1
	 * 	The number of bowls for team 1
	 **/
	private int bowls1;
	/**
	 * @param overs
	 * 	The number of overs in the current inning
	 **/
	private int overs;
	/**
	 * @param innings
	 * 	The number of innings played during the current game
	 **/
	private int innings;
	/**
	 * @param inPlay
	 * 	Whether the ball is currently in play or not
	 **/
	private boolean inPlay;
	/**
	 * @param running
	 * 	Whether the player is currently running or not
	 **/
	private boolean running;
	
	
	/**
	 * Constructs a CricketGame using the public default values.
	 **/
	public CricketGame()
	{
		MAXBOWLS = 0;
		MAXOVERPERINN= 0;
		MAXINN = 0;
		MAXPLAYERS = 0;
		score0 = 0;
		score1 = 0;
		outs0 = 0;
		outs1 = 0;
		bowls0 = 0;
		bowls1 = 0;
		overs = 0;
		innings = 0;
	}
	
	/**
	 * Constructs a CricketGame with the given configuration parameters.
	 * @param givenBowlsPerOver
	 * 	The max number of bowls per over
	 * @param givenOversPerInnings
	 * 	The max number of overs per inning
	 * @param givenTotalInnings
	 * 	The max number of innings 
	 * @param givenNumPlayer
	 * 	The max number of players per team
	 **/
	public CricketGame(int givenBowlsPerOver, int givenOversPerInnings, int givenTotalInnings, int givenNumPlayers )
	{
		MAXBOWLS = givenBowlsPerOver;
	    MAXOVERPERINN= givenOversPerInnings;
	    if((givenTotalInnings%2)!=0)
		   MAXINN = givenTotalInnings+1;
	    else
	       MAXINN = givenTotalInnings;
		MAXPLAYERS = givenNumPlayers;
		score0 = 0;
		score1 = 0;
		outs0 = 0;
		outs1 = 0;
		bowls0 = 0;
		bowls1 = 0;
		overs = 0;
		innings = 0;
	}
	
	/**
	 * Bowls the ball and updates the game state depending on the given outcome.
	 * @param outcome
	 * 		the outcome of the thrown ball
	 **/
	public void bowl(Outcome outcome)
	{
		if(outcome == WIDE)
		{
			if(whichSideIsBatting() == 0)
			{
				score0 += 1;
			}
			else
			{
				score1 += 1;
			}
			
		}
		else if(outcome == NO_BALL)
		{
			if(whichSideIsBatting() == 0)
			{
				score0 += 1;	
			}
			else
			{
				score1 += 1;
			}
		}
		else if(outcome == BOUNDARY_SIX)
		{
			if(!inPlay && !isGameEnded() ) 
			{
				if(whichSideIsBatting() == 0)
				{
					score0 += 6;
					bowls0 += 1;
					bowlCalibrater();
				}
				else
				{
					score1 += 6;
					bowls1 += 1;
					bowlCalibrater();
				}
			}
		}
		else if(outcome == BOUNDARY_FOUR)
		{
			if(!inPlay ) 
			{
				if(whichSideIsBatting() == 0)
				{
					score0 += 4;
					bowls0 += 1;
					bowlCalibrater();
				}
				else
				{
					score1 += 4;
					bowls1 += 1;
					bowlCalibrater();
				}
			}
		}
		else if(outcome == WICKET)
		{
			if(!inPlay ) 
			{
				if(whichSideIsBatting() == 0)
				{
					outs0 += 1;
					bowls0 += 1;
					bowlCalibrater();
				}
				else
				{
					outs1 += 1;
					bowls1 += 1;
					bowlCalibrater();
				}
		   }
			running = false;
		}
		else if(outcome == HIT)
		{
			inPlay = true;
			if(whichSideIsBatting() == 0)
			{
				bowls0 += 1;
				bowlCalibrater();
			}
			else
			{
				bowls1 += 1;
				bowlCalibrater();
			}
		}
		else if(outcome == CAUGHT_FLY || outcome == LBW)
		{
			if(whichSideIsBatting() == 0)
			{
				outs0 += 1;
				bowls0 += 1;
				bowlCalibrater();
			}
			else
			{
				outs1 += 1;
				bowls1 += 1;
				bowlCalibrater();
			}
			running = false;
		}
	}
	
	/**
	 * Transitions from ball in play to ball not in play, without an out.
	 **/
	public void safe()
	{
		inPlay = false;
		
		if(running == true)
		{
			
			//innings just ended, then add score to old innings
			//
			
			if(bowls0==0 && bowls1==0 && overs==0)
			{
				
				if(whichSideIsBatting() == 0)
				{
					score1 += 1;
				}
				else
				{
					score0 += 1;
				}
			}
			
			
			else
			{
			if(whichSideIsBatting() == 0)
			{
				score0 += 1;
			}
			else
			{
				score1 += 1;
			}
			}
		}
		running=false;
	}
	
	/**
	 * Returns true if the ball is currently in play.
	 * @return
	 * 		Returns if the ball is in play
	 **/
	public boolean isInPlay()
	{
		return inPlay;
	}
	
	/**
	 * Runs the batsman out (i.e., fielders knock over wicket while batsmen are running).
	 **/
	public void runOut()
	{
		if(inPlay && isRunning())
		{
			if(whichSideIsBatting() == 0)
			{
				outs0 += 1;
			}
			else
			{
				outs1 += 1;
			}
		}
		running = false;
		inPlay = false;
	}
	
	/**
	 * Returns true if the game has ended, false otherwise.
	 * @return 
	 * 		Returns true if the game has ended
	 **/
	public boolean isGameEnded()
	{
		if(scoreEnd() == true)
		{
			return true;
		}
		
		if(innings >= MAXINN)
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	/**
	 * Checks to see if the game has ended based on the score in the inning prior to the last inning.
	 **/
	private boolean scoreEnd() 
	{
		if(innings == (MAXINN - 1))
		{
			if(whichSideIsBatting() == 0)
			{
				if(score0 > score1)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				if(score1 > score0)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * Returns true if bats-men are currently running.
	 * @return
	 * 	Returns true if the bats-men are running
	 **/
	public boolean isRunning()
	{
		return running;
	}
	
	/**
	 * Starts the bats-men running from one end of the pitch to the other.
	 **/
	public void tryRun()
	{
		if(isInPlay())
		{
		if(running == true)
		{
			if(whichSideIsBatting() == 0)
			{
				score0 += 1;
			}
			else
			{
				score1 += 1;
			}
		}
		else
		{
			running = true;
		}
		}
	}
	
	/**
	 * Adjusts the number of overs based on the number of bowls for which ever team is currently batting.
	 **/
	private void bowlCalibrater()
	{
		if(whichSideIsBatting() == 0)
		{
			if(bowls0 >= MAXBOWLS)
			{
				overs += 1;
				bowls0 = 0;
			}
		}
		else
		{
			if(bowls1 >= MAXBOWLS)
			{
				overs += 1;
				bowls1 = 0;
			}
		}
		oversCalibrater();
		inningsCalibrater();
	}
	
	/**
	 * Adjusts the number of innings based on the current number of overs.
	 **/
	private void oversCalibrater()
	{
		if(overs >= MAXOVERPERINN)
		{
			innings += 1;
			overs = 0;
			outs0=0;
			outs1=0;
			if(whichSideIsBatting() == 0)
			{
				bowls0 = 0;
			}
			else
			{
				bowls1 = 0;
			}
		}
	}
	
	/**
	 * Adjusts the number of innings based on the current number of overs.
	 **/
	private void inningsCalibrater()
	{
		int out;
		if(whichSideIsBatting() == 0)
		{
			out = outs0;
		}
		else
		{
			out=outs1;
		}
		
		if(innings>=MAXINN)
		{
			//game end
			overs = 0;
			outs0=0;
			outs1=0;
			if(whichSideIsBatting() == 0)
			{
				bowls0 = 0;
			}
			else
			{
				bowls1 = 0;
			}
		}
		
		
		if(out >= (MAXPLAYERS-1) )
		{
			innings += 1;
			overs = 0;
			outs0=0;
			outs1=0;
			if(whichSideIsBatting() == 0)
			{
				bowls0 = 0;
			}
			else
			{
				bowls1 = 0;
			}
		}
	}
	
	
	/**
	 * Returns the number of innings that have been completed.
	 * @return 
	 * 	Returns the number of completed innings
	 **/
	public int getCompletedInnings()
	{
		return innings;
	}
	
	/**
	 * Returns the number of players out in the current innings.
	 * @return 
	 * 	Returns the number of outs the batting team has gotten.
	 **/
	public int getOuts()
	{
		if(whichSideIsBatting() == 0)
		{
			return outs0;
		}
		else
		{
			return outs1;
		}
	}
	
	/**
	 * Returns the number of completed overs for the current innings.
	 * @return
	 * 	Returns the number of completed overs
	 **/
	public int getOverCount()
	{
		return overs;
	}
	
	/**
	 * Returns the number of times the bowler has bowled so far during the current over, not counting wides or no-balls.
	 * @return
	 * 	Returns the number of bowls the current batting team has.
	 **/
	public int getBowlCount()
	{
		if(whichSideIsBatting() == 0)
		{
			return bowls0;
		}
		else
		{
			return bowls1;
		}
	}
	
	/**
	 * Returns the score for one of the two sides.
	 * @param battingSide
	 * 	Which side is batting
	 * @return
	 * 	Returns the score of which ever side is entered
	 **/
	public int getScore(boolean battingSide)
	{
		if(battingSide == true) //Return score of batting side
		{
			if(whichSideIsBatting() == 0)
			{
				return score0;
			}
			else
			{
				return score1;
			}
		}
		else //battingSide == false
		{
			if(whichSideIsBatting() == 1)//Return score of non-batting side
			{
				return score0;
			}
			else
			{
				return score1;
			}
		}
	}
	
	/**
	 * Returns 0 if side 0 is batting or 1 if side 1 is batting.
	 * @return
	 * 	Returns which side is batting based on 0 or 1.
	 **/
	public int whichSideIsBatting()
	{
		return (innings % 2);
	}
}