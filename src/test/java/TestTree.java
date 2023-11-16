import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class TestTree 
{
	
	Scanner comp;
	PrintStream me;
	boolean show = true; //if set to true, will print out respones to and from the game.  Set to false to make it faster.
	
    
    //initializes the Testing environment with piped streams (Queues)
	public TestTree() throws IOException
	{
		PipedOutputStream inputHandle = new PipedOutputStream();
		PipedInputStream input= new PipedInputStream(inputHandle);
		
		PipedOutputStream output = new PipedOutputStream();
		PipedInputStream outputHandle= new PipedInputStream(output);
		
		QTree game = new QTree(input,new PrintStream(output));

		Thread t = new Thread(()->{game.playGame();});
		t.start();

        comp = new Scanner(outputHandle);
		me = new PrintStream(inputHandle);

	
	}

    /*
        Helper methods for IO and testing
    
        These methods are beefed up versions of assert.  
    
    */
    
    //Use this to "check" if the string from the program is correct.
	public void check(String s)
	{
		String observed = comp.nextLine();
		if(show) {System.out.println("observed:"+observed);}
		//will not work with simple assert statements due to multithreading	
		if( ! observed.equals(s))
		{
			System.out.println("expected "+s+" but got "+observed);
			System.exit(1);
		}
	}
	
	public void say(String s)
	{
		me.println(s);
		me.flush(); //greatly increases speed of program, lets other side know there is new data.
		if(show) {System.out.println("said:"+s);}
	}
	
	
	public void run()
	{
		// Check duck is there
		check(Strings.IS_IT_ALIVE);
        say("Y");
        //now what? Think of all the input and outputs here...
		check(Strings.IS_IT_A + Strings.DUCK + "?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		// Check rock is there
		check(Strings.IS_IT_ALIVE);
		say("N");
		check(Strings.IS_IT_A + Strings.ROCK + "?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("Y");

		// Wolf time
		check(Strings.IS_IT_ALIVE);
		say("Y");
		check(Strings.IS_IT_A + Strings.DUCK + "?");
		say("N");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("Wolf");
		check(Strings.NEW_QUESTION + Strings.DUCK + " and a " + "Wolf");
		say("Does it have fur?");
		check("Answering yes to " + "Does it have fur?" + " means " + "Wolf" + "?");
		say("Y");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		// Check duck is still there
		check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Does it have fur?");
        say("N");
		check(Strings.IS_IT_A + Strings.DUCK + "?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		// Check wolf is there
		check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Does it have fur?");
        say("Y");
		check(Strings.IS_IT_A + "Wolf" + "?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		// Snake time
		check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Does it have fur?");
        say("N");
		check(Strings.IS_IT_A + Strings.DUCK + "?");
		say("N");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("Snake");
		check(Strings.NEW_QUESTION + Strings.DUCK + " and a " + "Snake");
		say("Is it a bird?");
		check("Answering yes to " + "Is it a bird?" + " means " + "Snake" + "?");
		say("N");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		// Check duck is still there
		check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Does it have fur?");
        say("N");
        check("Is it a bird?");
        say("Y");
		check(Strings.IS_IT_A + Strings.DUCK + "?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		// Check snake is there
		check(Strings.IS_IT_ALIVE);
        say("Y");
        check("Does it have fur?");
        say("N");
        check("Is it a bird?");
        say("N");
		check(Strings.IS_IT_A + "Snake" + "?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		// Computer time
		check(Strings.IS_IT_ALIVE);
		say("N");
		check(Strings.IS_IT_A + Strings.ROCK + "?");
		say("N");
		check(Strings.WHAT_IS_THE_ANSWER);
		say("Computer");
		check(Strings.NEW_QUESTION + Strings.ROCK + " and a " + "Computer");
		say("Does it run on electricity?");
		check("Answering yes to " + "Does it run on electricity?" + " means " + "Computer" + "?");
		say("Y");
		check(Strings.THANKS);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		// Check rock is still there
		check(Strings.IS_IT_ALIVE);
		say("N");
		check("Does it run on electricity?");
		say("N");
		check(Strings.IS_IT_A + Strings.ROCK + "?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("Y");
		
		// Check computer is there
		check(Strings.IS_IT_ALIVE);
		say("N");
		check("Does it run on electricity?");
		say("Y");
		check(Strings.IS_IT_A + "Computer" + "?");
		say("Y");
		check(Strings.I_WIN);
		check(Strings.PLAY_AGAIN);
		say("N");
        
        //close the streams at the end to enrue good behavior.
		comp.close();
		me.close();
	}





	public static void main(String[] args) 
	{
		System.out.print("Test is running");
		try
		{
			TestTree test = new TestTree();
			test.run();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
		System.out.print("you there halt");
		
	}
	
	
}
