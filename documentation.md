# Greenfoot-CLI Documentation<br>
This is an attempt at writing a Command Line Interface for Linux with Greenfoot.<br>
<br>
###### 1. [Usage](#usage)
2. [Commands](#commands)
   1. [brainfuck](#brainfuck)
   2. [push](#push)
   3. [pop](#pop)
   4. [echo/print/println](#print)
   5. [exit/stop](#exit)
   6. [clear](#clear)

3. [Development](#dev)
   1. [cli.java](#cli)
   2. [lib.java](#lib)
      1. [cliMap](#cliMap)
      2. [buffer](#buffer)
      3. [cursorX](#cursorX)
      4. [pwd](#pwd)
      5. [pwdA](#pwdA)
      6. [bufferOverflowProtection](#bop)
      7. [println()](#println)
      8. [print()](#printC)
      9. [commandCheck()](#commandCheck)
      10. [newline()](#newline)
      
   3. [Util.java](#util)
      1. [enum OS/getOS()](#os)
      2. [asciiToInt()](#ascii)
      3. [brainfuckSyntaxCheck()](#brainfuckSyntaxCheck)
      4. [isBetween()](#isBetween)
      5. [spaceBefore()](#spaceBefore)
      6. [commandLength()](#commandLength)
      7. [spaceAfter()](#spaceAfter)
      8. [parameterLength()](#parameterLength)
   
   4. [Commands.java](#commands.j)
      1. [commands()](#commandsC)
      
   5. [Stack.java](#stack.j)
      1. [stack](#stack)
      2. [stackPointer](#stackPointer)
      3. [push()](#pushC)
      4. [pop()](#popC)
      
   6. [BrainfuckInterpreter.java](#brainfuckInterpreter.j)
      1. [brainfuckInterpreter()](#brainfuckInterpreterC)
      
   7. [Objects](#objects)
      1. [Cursor.java](#cursorOb)
      2. [Font.java](#fontOb)
<br>
## <a name="usage">Usage</a>
You can clone the repository and open the project.greenfoot file with Greenfoot. Alternatively, you can download the entire Jar and execute it with Java.<br>
After you start the program, press run to be able to use it.<br>
The program can correctly interpret any non-extended US-ASCII input without a modifier key, and with shift as modifier, the input is interpreted as the corresponding character on a US ANSI QWERTY keyboard.<br>
<br>
### <a name="commands">Commands</a>
Commands are not case-sensitive, but their parameters might be.
<br>
#### <a name="brainfuck">brainfuck</a>
Usage: `brainfuck [Program]`<br>
This command can interpret a brainfuck program that is given as a parameter.
<br>
#### <a name="push">push</a>
Usage: `push [string]`<br>
This command pushes a string onto the stack.
<br>
#### <a name="pop">pop</a>
Usage: `pop`<br>
This command pops the current String on the stack and prints it.
<br>
#### <a name="print">echo/print/println</a>
Usage: `[command] [String]`<br>
This command prints a string.
<br>
#### <a name="exit">exit/stop</a>
Usage: `[command]`<br>
This command stops the execution of the program.
<br>
#### <a name="clear">clear</a>
Usage: `clear`<br>
This commands clears the interface.
<br>
<br>
## <a name="dev">Development</a>
<br>
### <a name="cli">cli.java</a>
Contains the World-initialization and Greenfoots act method.
<br>
### <a name="lib">lib.java</a>
Library of the program.
Contains the following static public functions and data structures:
<br>
#### <a name="cliMap">cliMap:</a>
`final static char[][]`<br>
Contains the status of each field of the interface.
<br>
#### <a name="buffer">buffer:</a>
`static char[]`<br>
Buffer for keyboard input.
<br>
#### <a name="cursorX">cursorX:</a>
`static int`<br>
The position of the cursor.
<br>
#### <a name="pwd">pwd:</a>
`static List<Character>`<br>
Contains the position in the filesystem, but there is no working command to manipulate it at the moment.
<br>
#### <a name="prefix">prefix:</a>
`static List<Character>`<br>
List that contains the prefix of the input line.
<br>
#### <a name="pwdA">pwdA:</a>
`static char[]`<br>
pwd as array.
<br>
#### <a name="bop">bufferOverflowProtection:</a>
`static int`<br>
Number that specifies by how much the buffer must be shorter than the length of the interface to avoid an ArrayIndexOutOfBounds Exception.
<br>
#### <a name="println">println(String):</a>
Prints a String with a newline.
<br>
#### <a name="printC">print(String):</a>
Prints a String.
<br>
#### <a name="commandCheck">commandCheck():</a>
Splits the buffer into command and parameter and executes Commands.commands with these variables.
<br>
#### <a name="newline">newline():</a>
Generates a new line and writes the input buffer onto the interface.
<br>
### <a name="util">Util.java:</a>
Utility commands used in the program.
<br>
#### <a name="os">enum OS/getOS():</a>
Checks the OS of the machine running the program.
<br>
#### <a name="ascii">asciiToInt(char):</a>
returns the US-ASCII value of the input.
<br>
#### <a name="brainfuckSyntaxCheck">brainfuckSyntaxCheck(String):</a>
Checks the input for invalid brainfuck syntax.
<br>
#### <a name="isBetween">isBetween(int, int, int):</a>
Returns wether or not the first int is between the second as the low end and the third as the high end.
<br>
#### <a name="spaceBefore">spaceBefore(int, char[]):</a>
returns the number of spaces before the next non-space character.
The int is the startpoint of the check, the target is the char array.
<br>
#### <a name="commandLength">commandLength(int, char[]):</a>
returns the number of continuus non-space characters after the startpoint.
The int the startpoint, and the char[] is the target.
<br>
#### <a name="spaceAfter">spaceAfter(int, char[]):</a>
returns the number of spaces before the next non-space character.
int is the startpoint and char[] is the target.
<br>
#### <a name="parameterLength">parameterLength(int, char[]):</a>
returns the number of characters after the startpoint.
int is the startpoint and char[] is the target.
<br>
### <a name="commands.j">Commands.java:</a>
Contains the commands that can be executed from the interface.
<br>
#### <a name="commandsC">commands(`String, String`):</a>
Executes the interface commands.
The first Sring is the command name and the second one is the parameter.
<br>
### <a name="stack.j">Stack.java:</a>
Contains the stack and its functions.
<br>
#### <a name="stack">stack:</a>
`static String[]`<br>
The stack itself.
<br>
#### <a name="stackPointer">stackPointer:</a>
`static int`<br>
The stack pointer.
<br>
#### <a name="pushC">push(String):</a>
Pushes the String onto the stack.
<br>
#### <a name="popC">pop():</a>
Pops the String and prints it.
<br>
### <a name="brainfuckInterpreter.j">BrainfuckInterpreter.java:</a>
Contains the brainfuck interpreter.
<br>
#### <a name="brainfuckInterpreterC">brainfuckInterpreter(String):</a>
The interpretation program.
<br>
### <a name="objects">Objects:</a>
#### <a name="cursorOb">Cursor.java:</a>
The cursor object.
#### <a name="fontOb">Font.java:</a>
The font object.
