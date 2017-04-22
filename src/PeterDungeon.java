/**
 * 
 * @author peterniemeyer and Toph
 * @date 2017/04/20
 * @version 2.0
 * 
 * 5-10 Steps to get out (rnd)
 * Move or stay: if move, 1/3 Gnome, 1/3 Dragon, 1/3 noting.
 * You 10 HP :: 1-8 Attack.
 * Gnome: 2-4 HP, Dragon: 8-12 HP :: Gold = 0-2x HP, Attack = 0-.5x HP
 * Attack or run = step, run = no gold
 * 
 */

import java.util.Scanner;
import java.util.Random;

public class PeterDungeon 
{
	public static void main(String[] args) 
	{
		String rerun = "y";
		while (rerun.equalsIgnoreCase("y"))
		{		
// Strings and variables...			
			Scanner kb = new Scanner(System.in);
			int playerHP = 10;	
			int playerGold = 0;	
			int monsterHP = 0;	
			Random rnd = new Random();	
			String name = ""; // name of character		
			String move = ""; // move or stay put		
			String fight = ""; // fight of run		
			String player = "alive";	// status in game	
			String[] choice = {"What would you like to do?", "What is your next decision?", "What now?"};				
			String[] turntext = {"You are still in this dark cave.", "Darkness surrounds you.", "The damp, dark, still air presses on you from all sides.", "The walls echo your pounding heart as you stop to listen."}; 	
			String[] encounter = {"", "gnome", "dragon"}; 				

// Intro text...	
			System.out.println("D*U*N*G*E*O*N.");
			System.out.println();
			System.out.println("!!!...");
			System.out.println("You suddenly and alarmingly awake to find youself in a dark cave.");
			System.out.println("A quick self-check lets you know that you are unharmed (HP " + playerHP + ") but confused.");
			System.out.println("You have little with you but your wits and a few belongings... and a lurking fear that you are in danger.");
			System.out.println("Keep moving and you might survive.");
			System.out.println("");
			System.out.println("...a few moments pass; you remember your name, but not much else... What is it?"); // get name
			name = kb.next();
			System.out.println("Good luck " + name + ". You will need it.");
		
// Begin loop for turns...			
			while (player.equals("alive")) // alive as opposed to out or dead...
			{
				int turn = rnd.nextInt(5) + 5;		// selects a game that will run 5-10 moves
				for (int i = 0; i < turn; i++) 
				{				
					if (player.equals("alive")) 				
					{					
						move = "s";					
						while (move.equalsIgnoreCase("s")) // standing still does not move counter nor does anything useful... it's just a choice
						{						
							int description = rnd.nextInt(turntext.length); // Random selection for "turntext"						
							System.out.println("");						
							if (i == turn-2)
							{														
								System.out.println("You see faint light up ahead."); // alternate text toward end of dungeon
							}						
							else if (i == turn-1) 
							{
								System.out.println("The light up ahead is a touch brighter.");	// alternate text near end of game	
							}						
							else 
							{							
								System.out.println(turntext[description]);	// basic description from 3 options
							}						
							System.out.println("You have " + playerHP + " hit points and " + playerGold + " gold.");	
							int chce = rnd.nextInt(choice.length); // select a choice prompt from choice array
							System.out.println(choice[chce]);						
							System.out.println("move on (m) or sit still (s)");						
							move = kb.next();	
							while( !(move.equalsIgnoreCase("m") || move.equalsIgnoreCase("s")))  // user input control loop
							{
						System.out.println("Please select the (letter) of your choice: ");
						move = kb.next();
					}
						}						
						int monster = rnd.nextInt(encounter.length); // Random selection for "encounter"					
						if (monster == 1 || monster == 2) {					
							if (monster == 1) 
							{ 					
								monsterHP = rnd.nextInt(3) + 2; // gnome encounter HP assignment				
							}					
							else 
							{					
								monsterHP = rnd.nextInt(5) + 8; // dragon encounter	HP assignment				
							}											
							int monsterGold = rnd.nextInt((monsterHP * 2) + 1);			// gold assigned per monster based upon HP		
							System.out.println();					
							System.out.println("You have encountered a " + encounter[monster]);											
							int chce = rnd.nextInt(choice.length); // select a choice prompt from choice array
							System.out.println(choice[chce]);
							System.out.println("fight (f) or run away (r)");					
							fight = kb.next();
							while( !(fight.equalsIgnoreCase("f") || fight.equalsIgnoreCase("r")))  // user input control loop
									{
								System.out.println("Please select the (letter) of your choice: ");
								fight = kb.next();
							}
							while (fight.equalsIgnoreCase("f")) // fight loop... running away breaks loop
							{																							
								int monsterDamage = rnd.nextInt((monsterHP / 2) + 1);						
								int playerAttack = rnd.nextInt(8) + 1;						
								playerHP = playerHP - monsterDamage;						
								monsterHP = monsterHP - playerAttack;						
								if (playerHP <= 0) // player death breaks loop
								{							
									System.out.println("You have died! The " + encounter[monster] + " killed you!");							
									player = "dead";							
									break;													
								}							
								else 
								{								
									System.out.println("You did " + playerAttack + " points of damage to the " + encounter[monster] + ".");								
									System.out.println("The " + encounter[monster] + " did " + monsterDamage + " points of damage to you.");								
									if (monsterHP <= 0) // monster death breaks loop
									{								
										System.out.println("You killed the " + encounter[monster] + "! (and got " + monsterGold + " gold.)");									
										playerGold = playerGold + monsterGold;									
										break;								
									}								
									System.out.println("(You are both still alive.)");						
								}							
								chce = rnd.nextInt(choice.length); // select a choice prompt from choice array
								System.out.println(choice[chce]);						
								System.out.println("fight (f) or run away (r)");							
								fight = kb.next();
								while( !(fight.equalsIgnoreCase("f") || fight.equalsIgnoreCase("r"))) 
								{
							System.out.println("Please select the (letter) of your choice: ");
							fight = kb.next();
						}
								
							}					
						}									
					}				
					else  
					{				  
						player = "dead";				
						break;				
					}	
					/** 
					 * not sure if i needed all these assignments of "dead" but I got the program to work nonetheless. 
					 * room for improvement would be to KNOW before trial and error how to resolve the loops for "dead" and "out" without calling
					 * a new class (we haven't learned that yet)
					 */
				}			
				if (!player.equals("dead")) 
				{		
					player = "out";		
				}		
				else 
				{			
					player = "dead";			
				}	
			}					
			if (!player.equals("dead")) 
			{	
				System.out.println();	
				System.out.println("You breathe a deep sigh of relief as you step into the light");			
				System.out.println("You've escaped the dungeon with "  + playerHP + " health and " + playerGold + " gold.");	
			}
			
// CLOSE OUT and do it all again...						
			System.out.println();		
			System.out.println("Do you like to play again? (y or n);");		
			rerun = kb.next();	
			while( !(rerun.equalsIgnoreCase("y") || rerun.equalsIgnoreCase("n")))   // user input control loop
			{
		System.out.println("Please select the (letter) of your choice: ");
		rerun = kb.next();
	}
			System.out.println();	
		}		
		System.out.println();			
		System.out.println("(Thank you for playing D*U*N*G*E*O*N.)");	
		
	}	
}
