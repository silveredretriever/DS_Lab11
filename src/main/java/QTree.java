import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class QTree
{
	private class Node 
	{
		String val;
		Node no;
		Node yes;
		
		public Node(String val, Node no, Node yes)
		{
			this.val = val;
			this.no = no;
			this.yes = yes;
		}
		
		public Node(String val)
		{
			this(val, null, null);
		}
	}
	
	Scanner in;
	PrintStream out;
	
	Node start;
	
    //initializes the game
	public QTree(InputStream in,PrintStream out)
	{
		this.out=out;
		this.in=new Scanner(in);
		//Please initialize your data here
		start = new Node(Strings.IS_IT_ALIVE);
		start.no = new Node(Strings.ROCK);
		start.yes = new Node(Strings.DUCK);
	}
	
	public Node askQuestion(Node question)
	{
		if (question.no == null || question.yes == null) {return question;}
		
		out.println(question.val);
		String answer = in.nextLine();
		if (answer.equals("Y") || answer.equals("y"))
		{
			return askQuestion(question.yes);
		}
		else
		{
			return askQuestion(question.no);
		}
	}
	
    
    //plays the game, be sure to grab input from the Scanner "in", and send your output to "out".
	public void playGame()
	{
		boolean playAgain = true;
		while (playAgain) {
			Node temp = askQuestion(start);
			out.println(Strings.IS_IT_A + temp.val + "?");
			String answer = in.nextLine();
			if (answer.equals("Y") || answer.equals("y"))
			{
				out.println(Strings.I_WIN);
			}
			else
			{
				out.println(Strings.WHAT_IS_THE_ANSWER);
				answer = in.nextLine();
				out.println(Strings.NEW_QUESTION + temp.val + " and a " + answer);
				String question = in.nextLine();
				out.println("Answering yes to " + question + " means " + answer + "?");
				String input = in.nextLine();
				if (input.equals("Y") || input.equals("y"))
				{
					temp.no = new Node(temp.val);
					temp.yes = new Node(answer);
					temp.val = question;
				}
				else
				{
					temp.yes = new Node(temp.val);
					temp.no = new Node(answer);
					temp.val = question;
				}
				out.println(Strings.THANKS);
			}
			out.println(Strings.PLAY_AGAIN);
			answer = in.nextLine();
			if (!(answer.equals("Y") || answer.equals("y")))
			{
				playAgain = false;
			}
		}
	}
	
	
	public static void main(String[] args)
	{
		QTree t = new QTree(System.in, System.out);
		t.playGame();
	}
	
	
}
