# Greenfoot-CLI Documentation<br>
<br>
This is an attempt at writing a Command Line Interface for Linux with Greenfoot.<br>
1. [Usage][#Usage]
2. 
<br>
<br>
## Usage<br>
You can clone the repository and open the project.greenfoot file with Greenfoot. Alternatively, you can download the entire Jar and execute it with Java.<br>
After you start the program, press run to be able to use it.<br>
The program can correctly interpret any non-extended US-ASCII input without a modifier key, and with shift as modifier, the input is interpreted as the corresponding character on a US ANSI QWERTY keyboard.<br>
<br>
### Commands<br>
Commands are not case-sensitive, but their parameters might be.<br>
<br>
#### brainfuck<br>
Usage: `brainfuck [Program]`<br>
This command can interpret a brainfuck program that is given as a parameter.<br>
<br>
#### push<br>
Usage: `push [string]`<br>
This command pushes a string onto the stack.<br>
<br>
#### pop<br>
Usage: `pop`
This command pops the current String on the stack and prints it.<br>
<br>
#### echo/print/println<br>
Usage: `[command] [String]`<br>
This command prints a string.<br>
<br>
#### exit/stop<br>
Usage: `[command]`<br>
This command stops the execution of the program.<br>
<br>
#### clear<br>
Usage: `clear`<br>
This commands clears the interface.<br>
<br>
<br>
## Development<br>
<br>
### cli.java<br>
Contains the World-initialization and Greenfoots act method.<br>
<br>
### lib.java<br>
Library of the program.<br>
Contains the following static public functions and data structures:<br>
<br>
#### cliMap:<br>
`final static char[][]`<br>
Contains the status of each field of the interface.<br>
<br>
#### buffer:<br>
`static char[]`<br>
Buffer for keyboard input.<br>
<br>
#### cursorX:<br>
`static int`<br>
The position of the cursor.
<br>
#### pwd:<br>
`static List<Character>`<br>
Contains the position in the filesystem, but there is no working command to manipulate it at the moment.<br>
<br>
#### prefix:<br>
`static List<Character>`<br>
List that contains the prefix of the input line.<br>
<br>
#### pwdA:<br>
`static char[]`<br>
pwd as array.<br>
<br>
#### bufferOverflowProtection:<br>
`static int`<br>
Number that specifies by how much the buffer must be shorter than the length of the interface to avoid an ArrayIndexOutOfBounds Exception.<br>
<br>
#### println(String):<br>
Prints a String.<br>
<br>
#### commandCheck():<br>
Splits the buffer into command and parameter and executes Commands.commands with these variables.<br>
<br>
#### newline():<br>
Generates a new line and writes the input buffer onto the interface.<br>
<br>
### Util.java:<br>
Utility commands used in the program.<br>
<br>
#### enum OS/getOS():<br>
Checks the OS of the machine running the program.<br>
<br>
#### asciiToInt(char):<br>
returns the US-ASCII value of the input.<br>
<br>
#### brainfuckSyntaxCheck(String):<br>
Checks the input for invalid brainfuck syntax.<br>
<br>
#### isBetween(int, int, int):<br>
Returns wether or not the first int is between the second as the low end and the third as the high end.<br>
<br>
#### spaceBefore(int, char[]):<br>
returns the number of spaces before the next non-space character.<br>
The int is the startpoint of the check, the target is the char array.<br>
<br>
#### commandLength(int, char[]):<br>
returns the number of continuus non-space characters after the startpoint.<br>
The int the startpoint, and the char[] is the target.<br>
<br>
#### spaceAfter(int, char[]):<br>
returns the number of spaces before the next non-space character.<br>
int is the startpoint and char[] is the target.<br>
<br>
#### parameterLength(int, char[]):<br>
returns the number of characters after the startpoint.<br>
int is the startpoint and char[] is the target.<br>
<br>
### Commands.java:<br>
Contains the commands that can be executed from the interface.<br>
<br>
#### commands(`String, String`):<br>
Executes the interface commands.<br>
The first Sring is the command name and the second one is the parameter.<br>
<br>
### Stack.java:<br>
Contains the stack and its functions.<br>
<br>
#### stack:<br>
`static String[]`<br>
The stack itself.<br>
<br>
#### stackPointer:<br>
`static int`<br>
The stack pointer.<br>
<br>
#### push(String):<br>
Pushes the String onto the stack.<br>
<br>
#### pop():<br>
Pops the String and prints it.<br>
<br>
### BrainfuckInterpreter.java:<br>
Contains the brainfuck interpreter.<br>
<br>
#### brainfuckInterpreter(String):<br>
The interpretation program.<br>
<br>
### Objects:<br>
#### Cursor.java:<br>
The cursor object.<br>
#### Font.java:<br>
The font object.<br>
