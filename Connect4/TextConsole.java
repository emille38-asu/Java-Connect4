/**
 * This program contains the UI for a connect 4 game.
 * 12 Hours
 * 
 * @Edward_Miller
 * @version_1.0
 */

import java.util.Scanner;

public class Connect4TextConsole 
{

final static int WIDTH = 7;
final static int HEIGHT = 8;
public final static int LAST_ROW = WIDTH - 1;

public static char[][] board = new char[WIDTH][HEIGHT];
static Scanner scanner = new Scanner(System.in);
public static void Draw() 

{
for (int w = 1; WIDTH > w; w += 1) {
for (int h = 1; HEIGHT > h; h += 1) {
board[w][h] = '|';
}
}
}
//Printing the board
public static void Print() 
{
for (int w = 1; WIDTH > w; w += 1)
{
for (int h = 1; HEIGHT > h; h += 1) 
{
System.out.print(board[w][h]);
}
System.out.println();
}
System.out.println();

}
public static void PlayerX()
{
int counter = 1;
System.out.println("PlayerX - your turn.Choose a column number from 1-7.");
int column = scanner.nextInt();
while(true)
{
if(column > WIDTH){
System.out.println("Invalid column Input.");
break;
}
if (board[LAST_ROW][column] == '|')
{
board[LAST_ROW][column] = 'X';
break;
}else if(board[LAST_ROW][column] == 'X' || board[LAST_ROW][column] == 'O')
{
if(board[LAST_ROW - counter][column] == '|')
{
board[LAST_ROW - counter][column] = 'X';
break;
}
}
counter += 1;
if(counter == WIDTH)
{
System.out.println("This column is full.");
break;
}
}
}
public static void Player0()
{
int counter = 1;
System.out.println("Player0 - your turn.Choose a column number from 1-7.");
int column = scanner.nextInt();
while(true)
{
if(column > WIDTH)
{
System.out.println("Invalid column Input.");
break;
}
if (board[LAST_ROW][column] == '|') 
{
board[LAST_ROW][column] = 'O';
break;
}else if(board[LAST_ROW][column] == 'X' || board[LAST_ROW][column] == 'O')
{
if(board[LAST_ROW - counter][column] == '|')
{
board[LAST_ROW - counter][column] = 'O';
break;
}
}
counter += 1;
if(counter == WIDTH)
{
System.out.println("This column is full.");
break;
}
}
}
public static boolean CheckHorizontal()
{
boolean flag = true;
int counter = 0;
while(flag){
for(int w = 0; WIDTH > w; w += 1)
{
for(int h = 0; HEIGHT > h; h += 1)
{
if(board[w][h] == 'X')
{
counter += 1;
}else{
counter = 0;
}
if(counter >= 4)
{
System.out.println("Player X won the Game.");
flag = false;
}
}
}
break;
}
return flag;
}

public static boolean CheckingXVertically()
{
boolean flag = true;
int counter = 0;
while(flag){
for(int h = 0; HEIGHT > h; h += 1)
{
for(int w = 0; WIDTH > w; w += 1)
{
if(board[w][h] == 'X')
{
counter += 1;
}else
{
counter = 0;
}
if(counter >= 4){
System.out.println("Player X won the Game.");
flag = false;
}
}
}
break;
}
return flag;
}

//Check 4 x's
public static boolean CheckDiagonal()
{
boolean flag = true;
int counter = 0;
Boolean check = false;
int checkColumn = 1;
int checkRow = 1;
while(flag)
{
for(int w = 0; WIDTH > w; w += 1)
{
for(int h = 0; HEIGHT > h; h += 1)
{
if(board[w][h] == 'X')
{
counter += 1;
check = true;
while(check){
if(checkColumn + w <= WIDTH - 1&& checkRow + h <= HEIGHT - 1)
{
if(board[w + checkColumn][h + checkRow] == 'X')
{
counter += 1;
}
}
checkColumn += 1;
checkRow += 1;
if(checkColumn == WIDTH -1 || checkRow == HEIGHT -1)
{
check = false;
break;
}
if(counter >= 4)
{
System.out.println("Player X won the Game.");
check = false;
flag = false;
break;
}
}
}
if(counter >= 4)
{
flag = false;
break;
}
//resets counter and checkers
counter = 0;
checkColumn = 1;
checkRow = 1;
}
}
break;
}
return flag;
}
//Check if 4 x's

public static boolean CheckingDiagonally()
{
boolean flag = true;
int counter = 0;
Boolean check = false;
int checkColumn = 1;
int checkRow = 1;
while(flag){
for(int w = 0; WIDTH > w; w += 1)
{
for(int h = 0; HEIGHT > h; h += 1)
{
if(board[w][h] == 'X')
{
counter += 1;
check = true;
while(check){
if(w - checkColumn >= 0 && h - checkRow >= 0)
{
if(board[w - checkColumn][h - checkRow] == 'X')
{
counter += 1;
}
}
checkColumn += 1;
checkRow += 1;
if(checkColumn == 0 || checkRow == HEIGHT -1)
{
check = false;
break;
}
if(counter >= 4)
{
System.out.println("Player X won the Game.");
check = false;
flag = false;
break;
}
}
}
if(counter >= 4)
{
flag = false;
break;
}
counter = 0;
checkColumn = 1;
checkRow = 1;
}
}
break;
}
return flag;
}
public static boolean CheckX()
{
boolean flag = true;
if(!CheckingXVertically() || !CheckHorizontal()|| !CheckingDiagonally()|| !CheckingDiagonally()){
flag = false;
}
return flag;
}
//Check vertical
public static boolean Checking0Vertically()
{
boolean flag = true;
int counter = 0;
while(flag){
for(int h = 0; HEIGHT > h; h += 1)
{
for(int w = 0; WIDTH > w; w += 1)
{
if(board[w][h] == 'O')
{
counter += 1;
}else{
counter = 0;
}
if(counter >= 4)
{
System.out.println("Player 0 won the Game.");
flag = false;
}
}
}
break;
}
return flag;
}
//Check if 4 0's are connected Horizontally
public static boolean Checking0Horizontally()
{
boolean flag = true;
int counter = 0;
while(flag){
for(int w = 0; WIDTH > w; w += 1)
{
for(int h = 0; HEIGHT > h; h += 1)
{
if(board[w][h] == 'O')
{
counter += 1;
}else{
counter = 0;
}
if(counter >= 4){
System.out.println("Player 0 won the Game.");
flag = false;
}
}
}
break;
}
return flag;
}
//Check if 4 0's are connected Forward Diagonal
public static boolean Checking0ForwardDiagonally(){
boolean flag = true;
int counter = 0;
Boolean check = false;
int checkColumn = 1;
int checkRow = 1;
while(flag){
for(int w = 0; WIDTH > w; w += 1){
for(int h = 0; HEIGHT > h; h += 1){
if(board[w][h] == 'O'){
counter += 1;
check = true;
while(check){
if(checkColumn + w <= WIDTH - 1&& checkRow + h <= HEIGHT - 1){
if(board[w + checkColumn][h + checkRow] == 'O'){
counter += 1;
}
}
checkColumn += 1;
checkRow += 1;
if(checkColumn == WIDTH -1 || checkRow == HEIGHT -1){
check = false;
break;
}
if(counter >= 4){
System.out.println("Player 0 won the Game");
check = false;
flag = false;
break;
}
}
}
if(counter >= 4){
flag = false;
break;
}
counter = 0;
checkColumn = 1;
checkRow = 1;
}
}
break;
}
return flag;
}
//Check if 4 0's are connected Back Diagonal
public static boolean Checking0BackDiagonally()
{
boolean flag = true;
int counter = 0;
Boolean check = false;
int checkColumn = 1;
int checkRow = 1;
while(flag){
//goes through until an O is found
for(int w = 0; WIDTH > w; w += 1){
for(int h = 0; HEIGHT > h; h += 1){
if(board[w][h] == 'O'){
counter += 1;
check = true;
while(check){
if(w - checkColumn >= 0 && h - checkRow >= 0)
{
if(board[w - checkColumn][h - checkRow] == 'O')
{
counter += 1; //if O is found, add 1 to counter
}
}
checkColumn += 1;
checkRow += 1;
if(checkColumn == 0 || checkRow == HEIGHT -1)
{
check = false;
break;
}
if(counter >= 4){
System.out.println("Player 0 won the Game.");
check = false;
flag = false;
break;
}
}
}
if(counter >= 4)
{
flag = false;
break;
}
//resets counter and checkers
counter = 0;
checkColumn = 1;
checkRow = 1;
}
}
break;
}
return flag;
}

public static boolean CheckO()
{
boolean flag = true;
//checks all Os at once, for clearner main loop
if(!Checking0Vertically() || !Checking0Horizontally() || !Checking0BackDiagonally() || !Checking0ForwardDiagonally())
{
flag = false;
}
return flag;
}

}
