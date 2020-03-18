package mini2;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner; 
public class CS227Comp {
	 /**
	   * currOpcode for the read instruction.
	   */
	  public static final int READ      = 10;

	  /**
	   * currOpcode for the write instruction.
	   */
	  public static final int WRITE     = 20;

	  /**
	   * currOpcode for the load instruction.
	   */
	  public static final int LOAD      = 30;

	  /**
	   * currOpcode for the store instruction.
	   */
	  public static final int STORE     = 40;

	  /**
	   * currOpcode for the add instruction.
	   */
	  public static final int ADD       = 50;
	  
	  /**
	   * currOpcode for the sub instruction.
	   */  
	  public static final int SUB       = 51;
	  
	  /**
	   * currOpcode for the div instruction.
	   */  
	  public static final int DIV       = 52;
	  
	  /**
	   * currOpcode for the mod instruction.
	   */
	  public static final int MOD       = 53;
	  
	  /**
	   * currOpcode for the mul instruction.
	   */
	  public static final int MUL       = 54;

	  /**
	   * currOpcode for the jump instruction.
	   */
	  public static final int JUMP      = 60;
	  
	  /**
	   * currOpcode for the jumpn (jump if negative) instruction.
	   */
	  public static final int JUMPN     = 61;
	  
	  /**
	   * currOpcode for the jumpz (jump if zero) instruction.
	   */
	  public static final int JUMPZ     = 63;

	  /**
	   * currOpcode for the halt instruction.
	   */
	  public static final int HALT      = 80;
	  
	 // public int memSize;
	  private int[] memory;
	  //public int[] memImage;
	  private int currAC;
	  private int currIC;
	  private int currIR;
	  private int currOpcode;
	  private int currOperand;
	  private boolean isHalted;

	  /**
	   * Constructs a machine with the given number of words of memory, all words
	   * zero, all registers zero, in a halted state.
	   */
	  public CS227Comp(int memorySize)
	  {
		// memSize = memorySize;
		 memory = new int[memorySize];
	  }

	  /**
	   * Constructs a machine with the given initial values in the instruction
	   * counter and accumulator, and the given memory contents.  The memory
	   * size will be that of the given array.  Immediately crashes the machine
	   * if the given memory contains any invalid instructions, as
	   * specified in loadMemoryImage.
	   * @param initialIC
	   * @param initialAC
	   * @param memoryImage
	   */
	  public CS227Comp(int initialIC, int initialAC, int[] memoryImage)
	  {
		  this.currAC= initialAC;
		  this.currIC=initialIC;
		  //this.memImage= memoryImage;
		  this.memory=memoryImage;
		  //memSize= memoryImage.length;
	  }
	  
	  /**
	   * Returns the current value in the accumulator.
	   * @return
	   *   current value in the accumulator
	   */
	  public int getAC()
	  {
	    return currAC;
	  }
	  
	  /**
	   * Returns the current value of the instruction counter.
	   * @return
	   *   current value of the instruction counter
	   */
	  public int getIC()
	  {
	    return currIC;
	  }
	  
	  /**
	   * Returns the contents of the instruction register (most recently executed
	   * instruction)
	   * @return
	   *   contents of the instruction register
	   */
	  public int getIR()
	  {
	    return currIR;
	  }
	  
	  /**
	   * Returns the currOpcode for the most recently executed instruction
	   * (instruction register divided by 100).
	   * @return
	   *   currOpcode for the most recently executed instruction
	   */
	  public int getOpcode()
	  { 
	      
		  return currOpcode;
	  }
	  
	  /**
	   * Returns the operand for the most recently executed instruction
	   * (instruction register modulo 100).
	   * @return
	   *   operand for the most recently executed instruction
	   */
	  public int getOperand()
	  {
	      
	      return currOperand;	    
	  }
	  
	  /**
	   * Returns true if the machine is in a halted state, false otherwise.
	   * @return
	   *   true if the machine is in a halted state, false otherwise
	   */
	  public boolean isHalted()
	  {
	    return isHalted;
	  }
	  
	  /**
	   * Returns the contents of the memory cell at the given address.
	   * @param address
	   *   memory address
	   * @return
	   *   contents of memory cell at the given address
	   */
	  public int peekMemory(int address)
	  {
	    return memory[address];
	  }

	  /**
	   * Returns the number of words of memory this machine has.
	   * @return
	   *   the number of words of memory
	   */
	  public int getMemorySize()
	  {
	    return memory.length;
	  }
	  
	  /**
	   * Executes one instruction at a time, over and over, as long as the
	   * machine is not halted.
	   */
	  public void runProgram()
	  {
		  int i=0;
		  while(!isHalted && i<memory.length)
		  {
			  nextInstruction();
			  System.out.println(getIR());      // 6005
			    System.out.println(getOpcode());  // 60 (JUMP)
			    System.out.println(getOperand()); // 5
			    i++;
		  }
			  
	  }
	  
	  /**
	   * Displays complete machine state.  This one is done for you.  Observe the
	   * conversions that are used to print the values, as you'll need them
	   * elsewhere if you want a uniform look to your output.
	   */
	  public void dumpCore()
	  {
	    System.out.printf("REGISTERS:\n");
	    System.out.printf("%-20s %+05d\n", "accumulator", getAC());
	    System.out.printf("%-20s    %02d\n", "instruction counter",
	                      getIC());
	    System.out.printf("%-20s %+05d\n", "instruction register",
	                      getIR());
	    System.out.printf("%-20s    %02d\n", "operation code", getIR() / 100);
	    System.out.printf("%-20s    %02d\n", "operand", getIR() % 100);
	    System.out.printf("\nMEMORY:\n  ");
	    for (int i = 0; i < 10; i++) {
	      System.out.printf("%6d", i);
	    }
	    int row = 0;
	    for (int i = 0; i < getMemorySize(); i++)
	    {
	      if (i % 10 == 0)
	      {
	        row += 1;
	        System.out.printf("\n%2d ", row * 10);
	      }
	      System.out.printf("%+05d ", peekMemory(i));        
	    }
	    System.out.println();
	  }


	  /**
	   * Loads the given values into the machine's memory.  If the length of the
	   * given array is smaller than this machine's memory size, the remaining
	   * cells are filled with zeros; if larger, extra values are ignored.  If
	   * any value is encountered in the given array that is not a valid
	   * instruction, the machine crashes at that point with message "*** Invalid
	   * input ***".  The machine's memory size is not modified.  If no invalid
	   * instructions are encountered, the machine will be in a non-halted state.
	   * @param image
	   *   memory image to load
	   */
	  public void loadMemoryImage(int[] image)
	  {
		  if(memory.length>image.length)
		  {
			  for(int i=0;i<image.length; i++)
			  {
				  //if valid
				  memory[i]=image[i];
			  }
			  for(int i=image.length;i<memory.length; i++)
			  {
				  memory[i]=0;
			  
			  }
		  }
		  
		  else if(memory.length<image.length)
		  {
			  for(int i=0;i<memory.length; i++)
			  {
				  //if valid
				  memory[i]=image[i];
			  }
			  
		  }
	  }
	  
	  /**
	   * Reads instructions from the terminal, one per line, until the sentinel
	   * value (-99999) is read.  Instructions are decimal integers in the range
	   * [-9999,9999].  Any invalid input should immediately crash the machine
	   * with error message "*** Invalid input ***".  Each instruction should be
	   * prompted for with the zero-padded, two digit sequential instruction
	   * number followed by a question mark.  The instruction or sentinel should
	   * then be echoed as a four digit (or five, for the sentinel), signed,
	   * zero-padded decimal integer.  After successfully loading, display the
	   * message "*** Program Loaded ***" and move to a running state.
	   */
	  public void loadProgramFromConsole()
	  {
		 /* Scanner keyboard= new Scanner(System.in);

	        int counter;
	        int i= 0;
	        int num=0;
	        int sumOdd= 0;
	        int sumEven= 0;

	        
	        while(num!=-9999) {
	        System.out.println("0"+i+"? ");
	        num=keyboard.nextInt();
	        System.out.println("The numbers you entered are: ");

	        for (i =num; i !=0; i=i)
	                {
	                    if (i % 2 == 0)
	                        sumEven = sumEven + i;
	                    else
	                        sumOdd = sumOdd + i;
	                    i = keyboard.nextInt();
	                }
	        System.out.println("Even sum: " + sumEven);
	        System.out.println("Odd sum: " + sumOdd);
	    }
	  }*/
	  }

	  /**
	   * Reads instructions from the given file.  Instructions are then loaded
	   * into memory according to the specification for loadMemoryImage.
	   * @param filename
	   *   file from which to read instructions
	   */
	  public void loadProgramFromFile(String filename) throws FileNotFoundException
	  {
		  
		  ArrayList<String> list = new ArrayList<String>();
		  try {
		  
		  File file = new File(filename); 
		  Scanner sc = new Scanner(file); 
			  
			    while (sc.hasNextLine()) 
			    {
			      String nextline = sc.nextLine();
			      //System.out.println(nextline); 
			      if(!nextline.equals("-99999"))
			       list.add(nextline.substring(1,5));
			      else
			       break;
			    }
			  }
			  catch(FileNotFoundException e)
			    {
			    	System.out.println("File not found\n");
			    }
		  int[] temp= new int[list.size()];
		  for(int i=0;i<temp.length;i++) {
			  //System.out.println(list.get(i));
			  if(list.get(i)!="-99999")
			  temp[i]=Integer.parseInt(list.get(i));
		  }
		  loadMemoryImage(temp);
	  }
	  
	  /**
	   * Loads the next instruction from memory, parses it for the currOpcode and
	   * operand, and executes the instruction. The currOpcode is the high-order two
	   * digits of the instruction; the operand is the low-order two digits.
	   * Except in case of a jump, the instruction counter is incremented by one
	   * following execution of the instruction.
	   * <p>
	   * Invalid currOpcodes crash the machine.
	   * <p>
	   * Descriptions of all instructions follow:
	   *
	   * <ul>
	   * <li>READ:
	   * Executes the read instruction.  Reads a decimal word from the terminal
	   * into the address referenced by operand and updates the instruction
	   * counter.  Valid words are in the range [-9999,9999].  Out of range words
	   * are truncated on the right until within range before being stored; the
	   * truncated portion is discarded.  For example, -723471 will be truncated
	   * to -7234.
	   *
	   * <li>WRITE:
	   * Displays the value stored in memory at the address referenced by the
	   * operand as a signed, four digit, zero padded decimal integer and updates
	   * the instruction counter.
	   *
	   * <li>LOAD:
	   * Loads the value stored in memory at the address referenced by operand
	   * into the accumulator and updates the instruction counter.
	   *
	   * <li>STORE:
	   * Stores the value in the accumulator into memory at the address
	   * referenced by the operand and updates the instruction counter.
	   *
	   * <li>ADD:
	   * Adds the value stored in memory at the address referenced by operand to
	   * the accumulator, accounting for overflow, and updates the instruction
	   * counter.
	   *
	   * <li>SUB:
	   * Subtracts the value stored in memory at the address referenced by operand
	   * from the accumulator, accounting for overflow, and updates the
	   * instruction counter.
	   *
	   * <li>DIV:
	   * Divides the accumulator by the value stored in memory at the address
	   * referenced by operand, accounting for overflow, and updates the
	   * instruction counter.  All division is integer division.  Division by
	   * zero crashes the machine.
	   *
	   * <li>MOD:
	   * Calculates the remainder when dividing the accumulator by the value
	   * stored in memory at the address referenced by operand, accounting for
	   * overflow, stores the result in the accumulator, and updates the 
	   * instruction counter.  All division is integer division.  Division 
	   * by zero crashes the machine.
	   *
	   * <li>MUL:
	   * Multiplies the accumulator by the value stored in memory at the address 
	   * referenced by operand, accounting for overflow, and updates the
	   * instruction counter.
	   *
	   * <li>JUMP:
	   * Changes the instruction counter to operand.
	   *
	   * <li>JUMPN:
	   * If the accumulator is negative, changes the instruction counter to
	   * operand, otherwise updates the instruction counter normally.
	   *
	   * <li>JUMPZ:
	   * If the accumulator is zero, changes the instruction counter to operand,
	   * otherwise updates the instruction counter normally.
	   *
	   * <li>HALT:
	   * Displays the message "*** Program terminated normally ***", halts the
	   * machine, and dumps core.
	   * </ul>
	   * Arithmetic overflow occurs when the accumulator acquires a value outside
	   * of the range [-9999,9999].  It is handled by truncating the value of the
	   * accumulator to the low order four digits.
	   * <p>
	   * Instruction counter overflow occurs when when the value of the
	   * instruction counter matches or exceeds the memory size.  It is handled
	   * by setting the instruction counter to zero.
	   * <p>
	   * All crashes dump core.
	   */
	  public void nextInstruction()
	  {
		  String instruct =Integer.toString(memory[currIC]);
		  int insint = memory[currIC];
		  currOpcode =(memory[currIC]/100);
		  currOperand =(memory[currIC]%100);
		  currIR=(memory[currIC]);
		  int num ;
		  
		  if(currOpcode==READ)
		  {
			  
			  Scanner keyboard= new Scanner(System.in);
              System.out.println("Enter number: ");
		      num=keyboard.nextInt();
		      System.out.println("Done\n");
			  if(num>-9999 && num<9999)
			  {
			    memory[currOperand]=num;
			    currIC++;
			    }
			  else 
			  {
			  //truncate
				  //currIR=(memory[currIC]%10000);
				  memory[currOperand]=num%10000;
				  currIC++;
			  }
			  
		  }
		  else if(currOpcode==WRITE)
		  {
			  System.out.println("+00" + memory[currOperand]);
			  currIC++;
		  }
		  else if(currOpcode==LOAD)
		  {
			currAC=memory[currOperand];
			currIC++;
		  }
		  else if(currOpcode==STORE)
		  {
			  memory[currOperand]=currAC;
			  currIC++;
		  }
		  else if(currOpcode==ADD)
		  {
			  currAC = currAC+memory[currOperand];
			  currIC++;
		  }
		  else if(currOpcode==SUB)
		  {
			  currAC = currAC-memory[currOperand];
			  currIC++;
		  }
		  else if(currOpcode==DIV)
		  {
			 try {
			  currAC = currAC/(memory[currOperand]);
			  currIC++;}
			 catch(ArithmeticException e) {
				 isHalted=true;
				 dumpCore();
			 }
			 
		  }
		  else if(currOpcode==MOD)
		  {
			  currAC = currAC%memory[currOperand];
			  currIC++;
		  }
		  else if(currOpcode==MUL)
		  {
			  currAC = currAC*memory[currOperand];
			  currIC++;
		  }
		  else if(currOpcode==JUMP)
		  {
			  currIC=currOperand;
		  }
		  else if(currOpcode==JUMPN)
		  {
			  if(currAC<0)  currIC=currOperand;
			  else currIC++;
		  }
		  else if(currOpcode==JUMPZ)
		  {
			  if(currAC==0)  currIC=currOperand;
			  else currIC++;
		  }
		  else if(currOpcode==HALT)
		  {
			  isHalted= true;
			  System.out.println("Program terminated normally\n");
			  dumpCore();
			  
		  }
		  else
		  {
			  isHalted=true;
			  dumpCore();
		  }
		//overflow	  
	   if(currAC<-9999 || currAC>9999)
		   currAC= currAC%10000;
	   if(currIC>=memory.length)
		   currIC= 0;
	  }
	}