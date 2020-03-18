package mini1;
import java.util.*;

/**
 * 
 * @author shank
 * Class for MiniAssignment #1
 */
public class FromLoopToNuts 
{
	/**
	 * Private constructor disables instantiation.
	 */
	 private FromLoopToNuts() 
	 { 
		 
	 }
	
	
	/**
	 * Counts how many random numbers must be generated to get three consecutive values that are the same, using the given Random object and the given bound.
	 * @param rand
	 * 	Given random number generator
	 * @param bound
	 * 	Upper bound on generated numbers
	 * @return
	 * 	The number of random numbers that must be generated to get three consecutve values.
	 */
	public static int threeInARow(java.util.Random rand, int bound)
	{
		int third = rand.nextInt(bound);
		int last = rand.nextInt(bound);
		int now = rand.nextInt(bound);
		int counter = 3;
		while(true)
		{
			if(now == third && now == last)
			{
				break;
			}
			else
			{
				third = last;
				last = now;
				now = rand.nextInt(bound);
			}
			counter ++;
		}
		return counter;
	}
	
	/**
	 * Determines how many iterations of the following operation are required until the condition (a * a + b * b) > 4 is reached
	 * @param x
	 * 	Given x value
	 * @param y
	 * 	Given y value
	 * @param maxIterations
	 * 	Maximum number of iterations to attempt
	 * @return
	 * 	 Number of iterations required to get (a * a + b * b) > 4, or maxIterations
	 */
	public static int findEscapeCount(double x, double y, int maxIterations)
	{
		double a = 0.00, b = 0.00 , tempA = 0.00, tempB = 0.00;
		int count = 1;
		for(int i = 0; i < maxIterations; i++)
		{
			tempA = Math.pow(a,2) - Math.pow(b,2) + x;
			tempB = (2 * a * b) + y;
			a = tempA;
			b = tempB;
			
			if((Math.pow(a,2) + Math.pow(b,2)) > 4)
			{
				break;
			}
			count ++; 
		}
		if(count > maxIterations)
		{
			count = maxIterations;
		}
		
		return count;
	}
	
	/**
	 * Counts the number of positions in a pair of strings that have matching characters.
	 * @param s
	 * 	Any String
	 * @param t
	 * 	Any String
	 * @return
	 * 	The number of matches between two strings
	 */
	public static int countMatches(java.lang.String s, java.lang.String t)
	{
		int count = 0;
		int maxIterations = Math.max(s.length(), t.length());
		int diff = Math.abs(s.length() - t.length());
		if(diff != 0)
		{
			if(s.length() - t.length() > 0)
			{
				for(int j = 0; j < diff; j++)
				{
					t += " ";
				}
			}
			else
			{
				for(int j = 0; j < diff; j++)
				{
					s += " ";
				}
			}
		}
		
		for(int i = 0; i < maxIterations; i++)
		{
			if(s.charAt(i) == t.charAt(i))
			{
				count += 1;
			}
		}
		return count;
	}
	
	/**
	 * Given a string of text containing numbers separated by commas, returns true if the numbers form an arithmetic sequence (a sequence in which each value differs from the previous one by a fixed amount).
	 * @param text
	 * 	A string of text containing numbers separated by commas
	 * @return
	 * 	If the numbers are in a arithmetic series
	 */
	public static boolean isArithmetic(java.lang.String text)
	{
		boolean arith = true;
		if(text.equals("") || text.equals(null)|| text.length() == 0)
		{
			return arith;
		}
		ArrayList <String> numList = new ArrayList<String>(Arrays.asList(text.split(",")));
		ArrayList <Integer> list = new ArrayList <Integer>();
		
		for(String e : numList)
		{
			try
			{
				list.add(Integer.parseInt(e));
			}
			catch(Exception y)
			{
				arith = false;
				return arith;
			}
		}
		
		int difference = list.get(1) - list.get(0);
		
		for(int i = 0; i < text.length() - 1; i++)
		{
			if( i+1 > list.size() - 1)
			{
				break;
			}
			
			if(list.get(i+1) - list.get(i) != difference)
			{
				arith = false;
				break;
			}
		}
		return arith;
	}
	
	/**
	 * Returns a string with runs of consecutive characters removed.
	 * @param s
	 * 	Given String (Possibly Empty)
	 * @return
	 * 	String without duplicate characters
	 */
	public static java.lang.String eliminateRuns(java.lang.String s)
	{
		if(s.equals(null)||s.equals(""))
		{
			return s;
		}
		char letters[] = s.toCharArray();
		char lastLetter = letters[0];
		String word = lastLetter + "";
		for(int i = 0; i < s.length(); i++)
		{
			if(letters[i] != lastLetter)
			{
				word += letters[i] + "";
			}
			lastLetter = letters[i];
		}
		return word;
	}
	
	/**
	 * Determines whether s and t differ by having exactly one pair of distinct adjacent characters reversed.
	 * @param s
	 * 	Given String
	 * @param t
	 * 	Given String
	 * @return
	 * True if s and t are the same except for one pair of adjacent characters that are switched
	 */
	 public static boolean differByOneSwap(java.lang.String s, java.lang.String t)
	    {
	       char str1[] = s.toCharArray();
	       char str2[] = t.toCharArray();
	        
	       int swapcnt=0;
	       if(s.length() != t.length())
	       {
	           return false;
	       }
	       if(s.equals(t))
	       {
	           return false;
	       }
	       
	       for(int i = 0; i < s.length()-1 ; i++)
	       {
	           if(str1[i] != str2[i])
	           {
	               //i++;
	            
	               if(str1[i]==str2[i+1]) 
	               {
	                   if(str1[i+1]==str2[i])
	                   {
	                       //found swap
	                       swapcnt++;
	                   }
	               }
	            }        
	       }
	       
	       if(swapcnt==1)
	       {
	    	   return true;
	       }
	       else
	       {
	    	   return false;
	       }
	    }
	
	/**
	 * Counts the number of times that one string occurs as a substring in another, without allowing the occurrences to overlap.
	 * @param t
	 * 	string we are looking for ("target")
	 * @param s
	 * 	string in which we are looking ("source")
	 * @return
	 * 	The number of substrings that fit into the source string (No overlaps)
	 */
	public static int countSubstrings(java.lang.String t, java.lang.String s)
	{
	    int iterations = s.length() - t.length();
	    int count = 0, start = 0, end = t.length();
	    for(int i = 0; i < iterations; i++)
	    {            
	        String temp = s.substring(start, end);
	        if(temp.equals(t))
	        {
	            count += 1;
	            start = end;
	            end += t.length();
	        }
	        start = start+1;
	        end = start+ t.length();
	        if(end>s.length()) break;
	    }
	    return count;
	}
		
	/**
	 * Counts the number of times that one string occurs as a substring in another, allowing the occurrences to overlap.
	 * @param t
	 * 	string we are looking for ("target")
	 * @param s
	 * 	string in which we are looking ("source")
	 * @return
	 * 	The number of substrings that fit into the source string (With overlaps)
	 */
	public static int countSubstringsWithOverlap(java.lang.String t, java.lang.String s)
	{
		int count = 0;
		int indexS = 0;
		while((indexS = s.indexOf(t, indexS)) != -1)
		{
			indexS += 1;
			count += 1;
		}
		return count;
	}
}