import java.util.Random;
import java.util.Scanner;

public class Assn1_17jcm {
	public static final int MAX_SCORE = 100;									//setting winning score
	
	public static final boolean PLAYER = false;									//assigning a boolean to differentiate between...
	public static final boolean COMPUTER = true;								//player and computer turns

	public static void main(String[] args) {
		boolean gameWinner = runGame();											//running the game method and setting the winner equal...
		if(gameWinner)															//a boolean, if true player wins, if false computer wins
			System.out.println("*****PLAYER WINS!!*****");
		else
			System.out.println("*****Computer wins, again.*****");
	}
	
	static Scanner scan = new Scanner (System.in);								//preparing to take input across numerous methods
	static Random generator = new Random(System.currentTimeMillis());			//preparing random number generator for die rolls, seeded with current time
	
	public static boolean runGame() {											//over-arching game method
		boolean winner;
		int playerScore = 0;													//initializing player and computer scores
		int compScore = 0;
		int[] totals = {playerScore, compScore};								//initializing an array to hold both scores for ease of passing
		int roundNum = 1;														//initializing a round counter for output
		while(totals[0] < MAX_SCORE && totals[1] < MAX_SCORE){					//checking for a winner
		totals = round(totals[0], totals[1]);									//playing a round
			if(totals[0] < MAX_SCORE && totals[1] < MAX_SCORE) {				//checking for a winner during the round
				roundNum ++;
				System.out.println("Press <enter> to start round " + roundNum);	//beginning a new round
				scan.nextLine();
				scan.nextLine();
				System.out.println("ROUND " + roundNum + " IS BEGINNING\n");
		
			}
		}	
		if(totals[0] >= MAX_SCORE)
			winner = true;														//totals[0] holds player's score and as noted above...
		else																	//the boolean 'winner' is true if player wins hence...
			winner = false;														//winner is true if player score > 100 and false otherwise
		return winner;
	}
	
	public static int[] round(int playerScore, int compScore) {					//round method called in run game: consists of 2 turn...
		playerScore = turn(playerScore, PLAYER);								// calls, 1 for player and 1 for computer
		System.out.println("\nPlayer's sum is: " + playerScore + ", Computer's sum is: " + compScore + ".\n");
		
		if(playerScore < MAX_SCORE) {
		compScore = turn(compScore, COMPUTER);
		System.out.println("\nPlayer's sum is: " + playerScore + ", Computer's sum is: " + compScore+ ".\n");
		}
		int[] scores = new int[] {playerScore, compScore};						//turn scores for the round are saved in an array for ease of passing
		return scores;
	}
	
	public static int turn(int gameScore, boolean playerOrComp) {				//turn method called in round
		int turnScore = 0;														//initializing turn score to zero
		int tempGameScore = gameScore;											//initializing a temporary total game score for the player or computer...
																				//to be output following each roll without changing true total game score
		boolean next = true;													//initializing a boolean to determine if the turn ends or dice are rolled	
		String whosTurn;														//initializing a string used to output player or computer when required

		if (playerOrComp) whosTurn = "Computer";
		else whosTurn = "Player";
		
		System.out.println(whosTurn + "'s turn:");
		
		while(next && tempGameScore < MAX_SCORE) {								//ensuring turn continues based on last roll and score
		int[] rolls = roll();													//rolling two dice and calculating score
		
		if((rolls[0] == 1 && rolls[1] != 1)||(rolls[0] != 1 && rolls[1] == 1))	//CASE: (1,x) is rolled, setting turn score to 0
			turnScore = 0;
		else {
			turnScore += rolls[2];												//adding roll to turn score
			tempGameScore += rolls[2];											//adding roll to prospective game score
		}
		String firstRoll = rollConvert(rolls[0]);								//converting rolls from ints to strings
		String secondRoll = rollConvert(rolls[1]);
				
		System.out.println(whosTurn + " rolled: " + firstRoll + " + " + secondRoll);			//dealing with necessary output
		turnOutput(rolls[0], rolls[1], turnScore, tempGameScore, playerOrComp, whosTurn); 		//calling turn output method for remaineder of required output
		next = nextTurn(rolls[0], rolls[1], rolls[2], playerOrComp);			//checking if turn continues
		}
		return gameScore + turnScore;
	}
	
	public static void turnOutput(int rollOne, int rollTwo, int turnScore, int tempGameScore, boolean playerOrComp, String whosTurn) {
		if(rollOne == 1 && rollTwo == 1) {									
			System.out.println("DOUBLE ONES!");
			System.out.println(whosTurn + "'s turn sum is: " + turnScore + " and game sum would be: " + tempGameScore);
			System.out.println(whosTurn + " must roll again!");

		}	
		else if(rollOne == 1 || rollTwo == 1) {
			System.out.println("TURN OVER! Turn sum is zero!");					//this method ensures correct output occurs on differing dice rolls
		}																		 
		else if(rollOne == rollTwo) {
			System.out.println("DOUBLES!");
			System.out.println(whosTurn + "'s turn sum is: " + turnScore + " and game sum would be: " + tempGameScore);
			System.out.println(whosTurn + " must roll again!");

		}	
		else 
			System.out.println(whosTurn + "'s turn sum is: " + turnScore + " and game sum would be: " + tempGameScore);
	}
	
	public static boolean rollChoice() {										//method to accept user input when required
		boolean choice;
		System.out.println("Roll Again? (Enter 'y' or 'n')");					//as specified 'y' or 'n' to continue a turn when rolls are..
		String input = scan.next();												//not a special case
		if(input.equals("n")) {
			choice = false;
		}
		else 
			choice = true;
		return choice;
	}
	
	public static boolean choose() {											//this method randomly outputs a boolean choice for the 
		boolean c = generator.nextBoolean();									//computer to continue or end a turn
		return c;	
	}
	
	public static boolean nextTurn(int rollOne, int rollTwo, int score, boolean playerOrComp) {
		boolean next = false;
		if(score >= MAX_SCORE)
			next = false;														//when next = 0, the turn ends, when next = 1 the
		else if(rollOne == 1 && rollTwo == 1)									//player or computer decide, and when next = 2, roll again
			next = true;
		else if(rollOne == 1 || rollTwo == 1)
			next = false;
		else if(rollOne == rollTwo)
			next = true;
		else if (!playerOrComp){ 												//playerOrComp is false when it is the players turn, hence
			next = rollChoice();												//user input is only required when it is the user's turn
		}
		else 
			next = choose();													//random choice is made for computer
		return next;
	}
	
	public static String rollConvert(int value) {								//integer to string conversion
		String num;
		if(value == 1)
			num = "one";
		else if(value == 2)
			num = "two";														//output required ints one through 6 display as strings...
		else if(value == 3)														//this method completes the conversion
			num = "three";
		else if(value == 4)
			num = "four";
		else if(value == 5)
			num = "five";
		else
			num = "six";
		
		return num;
	}
	
	public static int turnScore(int rollOne, int rollTwo) {						//method to calculate score achieved when 2 dice are rolled
		int score;
		if(rollOne == 1 && rollTwo == 1)
			score = 25;
		else if(rollOne == 1 || rollTwo == 1)
			score = 0;
		else if(rollOne == rollTwo)
			score = 2*(rollOne + rollTwo);
		else
			score = rollOne + rollTwo;	
		return score;
	}
	
	public static int[] roll() {												//rolling 2 dice and storing rolls in an array for ease of passing
		int[] dice= new int [2];
		dice[0] = rollDie();
		dice[1] = rollDie();
		
		int score = turnScore(dice[0], dice[1]);
				
		int[] results = {dice[0], dice[1], score};
				
		return results;
	}
	
	public static int rollDie() {
		int r = generator.nextInt(6)+1;											//this method randomly outputs an integer between 1 and 6
		return r;																//to simulate a rolled die
	}
}
