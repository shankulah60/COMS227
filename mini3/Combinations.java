package mini3;
import java.util.Arrays;

/**
 * 
 * @author shank
 *
 */
public class Combinations 
{
	/**
	 * Recursive method that computes all the possible combinations of a set of "ingredients", where the value of an "ingredient" is represented as a nonnegative integer. Returns a 2-D array of all possible combinations of "ingredients"
	 * @param choices
	 * @return
	 */
	public static int[][] getCombinations(int [] choices)
	{
		int dim = 1 , len = choices.length;
		int [][] temp;
		
		for(int i = 0; i < len; i++)
		{
			dim = dim * choices[i];
		}
		
		int[][] data = new int[dim][len];
		
		if(len == 1)
		{
			for(int i = 0; i < choices[0]; i++)
			{
				data[i][0] = i; //changed from i + 1
			}
			return data;
		}
		else
		{
			temp = combine(getCombinations(subarray(choices, len - 1)), choices[len - 1]);
			for(int i  = 0; i < temp.length; i++)
			{
				for(int j = 0; j < temp[0].length; j++)
				{
					data[i][j] = temp[i][j];
				}
			}
		}//End if statement
		
		Arrays.sort(data, new ArrayComparator());
		return data;
	}//End method
	
	
	/**
	 * Creates a 1-D sub array handling the n-1 options
	 * @param data
	 * @param rows
	 * @return
	 */
	private static int[] subarray(int [] data, int rows)
	{
		int[] res = new int[rows];
		for(int i = 0; i < rows; i++)
		{
			res[i] = data[i];
		}
		return res;
	}
	
	
	/**
	 * Combines the separate arrays that are created in the getCombinations method
	 * @param data
	 * @param options
	 * @return
	 */
	private static int [][] combine(int [][]data, int options)
	{
		int [][] res = new int [data.length * options][data[0].length + 1];
		int lastcol = data[0].length;
		int j = 0;
		
		//Copy array option number of times
		for(int i = 0; i < data.length; i++)
		{
			for(int k = 0; k < options; k++)
			{
				res[j] = Arrays.copyOf(data[i], data[i].length + 1);
				j++;
			}
		}
		
		j = 0;
		while(j < res.length)
		{
			for(int i = 0; i < options; i++)
			{
				res[j][lastcol] = i;
				j++;
			}
		}
		
		return res;		
	}
}


