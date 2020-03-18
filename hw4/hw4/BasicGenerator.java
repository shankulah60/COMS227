
package hw4;

import java.util.Random;

import api.Generator;
import api.Icon;
import api.Piece;
import api.Position;
import examples.SamplePiece;

/**
 * Generator for Piece objects in BlockAddiction. Icons are 
 * always selected uniformly at random, and the Piece types
 * are generated with the following probabilities:
 * <ul>
 * <li>LPiece - 10%
 * <li>DiagonalPiece - 25%
 * <li>CornerPiece - 15%
 * <li>SnakePiece - 10%
 * <li>IPiece - 40%
 * </ul>
 * The initial position of each piece is based on its
 * vertical size as well as the width of the grid (given
 * as an argument to getNext).  The initial column is always
 * width/2 - 1.  The initial row is:
 *  * <ul>
 * <li>LPiece - row = -2
 * <li>DiagonalPiece - row = -1
 * <li>CornerPiece - row = -1
 * <li>SnakePiece - row = -1
 * <li>IPiece - row = -2
 * </ul>
 * 
 */
public class BasicGenerator implements Generator
{
  private Random rand;

  /**
   * Constructs a BasicGenerator that will use the given
   * Random object as its source of randomness.
   * @param givenRandom
   *   instance of Random to use
   */
  public BasicGenerator(Random givenRandom)
  {
    rand = givenRandom;
  }

  @Override
  public Piece getNext(int width)
  {
    Random prob = new Random();
    int num = prob.nextInt(101); //From 1 - 100
    if(num <= 40)
    {
    	//40% - IPiece
    	Icon[] randomized = new Icon[3];
    	for(int i = 0; i < randomized.length; i++)
    	{
    		randomized[i] = randomIcon();
    	}
    	return new IPiece(new Position(-2, width/2 +1), randomized);
    }
    else if( num > 40 && num <= 50)
    {
    	//10% - Snake
    	Icon[] randomized = new Icon[4];
    	for(int i = 0; i < randomized.length; i++)
    	{
    		randomized[i] = randomIcon();
    	}
    	return new SnakePiece(new Position(-1, width/2 +1), randomized);
    }
    else if(num > 50 && num <= 60)
    {
    	//10% - LPiece
    	Icon[] randomized = new Icon[4];
    	for(int i = 0; i < randomized.length; i++)
    	{
    		randomized[i] = randomIcon();
    	}
    	//return new LPiece(new Position(-2, width/2 +1));
    	return new LPiece(new Position(-2, width/2 +1), randomized);
    }
    else if(num > 60 && num <= 75)
    {
    	//15% - Corner
    	Icon[] randomized = new Icon[3];
    	for(int i = 0; i < randomized.length; i++)
    	{
    		randomized[i] = randomIcon();
    	}
    	return new CornerPiece(new Position(-1, width/2 +1), randomized);
    }
    else
    {
    	//25% - Diagonal
    	Icon[] randomized = new Icon[2];
    	for(int i = 0; i < randomized.length; i++)
    	{
    		randomized[i] = randomIcon();
    	}
    	return new DiagonalPiece(new Position(-1, width/2 +1), randomized);
    }
  }


  @Override
  public Icon randomIcon()
  {
    return new Icon(Icon.COLORS[rand.nextInt(Icon.COLORS.length)]);
  }

}
