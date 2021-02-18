/**
 * This program is the CPU logic for a connect 4 game.
 * 8 Hours
 * 
 * @Edward_Miller
 * @version_1.0
 */

import java.util.Random;

public class Connect4ComputerPlayer 
{
public static void CPU()
{
int counter = 1;

System.out.println("CPU will choose between 1-7.");
Random rand = new Random();


int cpuMove = rand.nextInt(6) + 1;
int column = cpuMove;

while(true)
{
	
if (Connect4TextConsole.board[Connect4TextConsole.LAST_ROW][column] == '|') 
{
Connect4TextConsole.board[Connect4TextConsole.LAST_ROW][column] = 'X';
break;

}else if(Connect4TextConsole.board[Connect4TextConsole.LAST_ROW][column] == 'X' 
		|| Connect4TextConsole.board[Connect4TextConsole.LAST_ROW][column] == 'O')
{
if(Connect4TextConsole.board[Connect4TextConsole.LAST_ROW - counter][column] == '|')
{
	
Connect4TextConsole.board[Connect4TextConsole.LAST_ROW - counter][column] = 'X';
break;
}

}
counter += 1;
}
}
}