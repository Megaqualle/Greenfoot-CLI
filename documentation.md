# Greenfoot-CLI Documentation<br>
This is an attempt at writing a Command Line Interface for Linux with Greenfoot.<br>
<br>
###### [Usage](#usage)
1. [Commands](#commands)
   1. [brainfuck](#brainfuck)
   2. [push](#push)
   3. [pop](#pop)
   4. [echo/print/println](#print)
   5. [exit/stop](#exit)
   6. [clear](#clear)

2. [Development](#dev)
   1. [cli.java](#cli)
   2. [lib.java](#lib)
      1. [cliMap](#cliMap)
      2. [buffer](#buffer)
      3. [cursorX](#cursorX)
      4. [pwd](#pwd)
      5. [pwdA](#pwdA)
      6. [bufferOverflowProtection](#bop)
      7. [prefix](#prefix)
      8. [println()](#println)
      9. [print()](#printC)
      10. [commandCheck()](#commandCheck)
      11. [newline()](#newline)
      
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
---
## <a name="usage">Usage</a>
You can clone the repository and open the project.greenfoot file with Greenfoot. Alternatively, you can download the entire Jar and execute it with Java.<br>
After you start the program, press run to be able to use it.<br>
The program can correctly interpret any non-extended US-ASCII input without a modifier key, and with shift as modifier, the input is interpreted as the corresponding character on a US ANSI QWERTY keyboard.<br>
<br>
### <a name="commands">1 Commands</a>
Commands are not case-sensitive, but their parameters might be.
<br>
#### <a name="brainfuck">1.1 brainfuck</a>
Usage: `brainfuck [Program]`<br>
This command can interpret a brainfuck program that is given as a parameter.
<br>
#### <a name="push">1.2 push</a>
Usage: `push [string]`<br>
This command pushes a string onto the stack.
<br>
#### <a name="pop">1.3 pop</a>
Usage: `pop`<br>
This command pops the current String on the stack and prints it.
<br>
#### <a name="print">1.4 echo/print/println</a>
Usage: `[command] [String]`<br>
This command prints a string.
<br>
#### <a name="exit">1.5 exit/stop</a>
Usage: `[command]`<br>
This command stops the execution of the program.
<br>
#### <a name="clear">1.6 clear</a>
Usage: `clear`<br>
This commands clears the interface.
<br>
<br>
## <a name="dev">2 Development</a>
<br>
### <a name="cli">2.1 cli.java</a>
Contains the World-initialization and Greenfoots act method.
<br>
### <a name="lib">2.2 lib.java</a>
Library of the program.
Contains the following static public functions and data structures:
<br>
#### <a name="cliMap">2.2.1 cliMap:</a>
`final static char[][]`<br>
Contains the status of each field of the interface.
<br>
#### <a name="buffer">2.2.2 buffer:</a>
`static char[]`<br>
Buffer for keyboard input.
<br>
#### <a name="cursorX">2.2.3 cursorX:</a>
`static int`<br>
The position of the cursor.
<br>
#### <a name="pwd">2.2.4 pwd:</a>
`static List<Character>`<br>
Contains the position in the filesystem, but there is no working command to manipulate it at the moment.
<br>
#### <a name="pwdA">2.2.5 pwdA:</a>
`static char[]`<br>
pwd as array.
<br>
#### <a name="bop">2.2.6 bufferOverflowProtection:</a>
`static int`<br>
Number that specifies by how much the buffer must be shorter than the length of the interface to avoid an ArrayIndexOutOfBounds Exception.
<br>
#### <a name="prefix">2.2.7 prefix:</a>
`static List<Character>`<br>
List that contains the prefix of the input line.
<br>
#### <a name="println">2.2.8 println(String):</a>
Prints a String with a newline.
<br>
#### <a name="printC">2.2.9 print(String):</a>
Prints a String.
<br>
#### <a name="commandCheck">2.2.10 commandCheck():</a>
Splits the buffer into command and parameter and executes Commands.commands with these variables.
<br>
#### <a name="newline">2.2.11 newline():</a>
Generates a new line and writes the input buffer onto the interface.
<br>
### <a name="util">2.3 Util.java:</a>
Utility commands used in the program.
<br>
#### <a name="os">2.3.1 enum OS/getOS():</a>
Checks the OS of the machine running the program.
<br>
#### <a name="ascii">2.3.2 asciiToInt(char):</a>
returns the US-ASCII value of the input.
<br>
#### <a name="brainfuckSyntaxCheck">2.3.3 brainfuckSyntaxCheck(String):</a>
Checks the input for invalid brainfuck syntax.
<br>
#### <a name="isBetween">2.3.4 isBetween(int, int, int):</a>
Returns wether or not the first int is between the second as the low end and the third as the high end.
<br>
#### <a name="spaceBefore">2.3.5 spaceBefore(int, char[]):</a>
returns the number of spaces before the next non-space character.
The int is the startpoint of the check, the target is the char array.
<br>
#### <a name="commandLength">2.3.6 commandLength(int, char[]):</a>
returns the number of continuus non-space characters after the startpoint.
The int the startpoint, and the char[] is the target.
<br>
#### <a name="spaceAfter">2.3.7 spaceAfter(int, char[]):</a>
returns the number of spaces before the next non-space character.
int is the startpoint and char[] is the target.
<br>
#### <a name="parameterLength">2.3.8 parameterLength(int, char[]):</a>
returns the number of characters after the startpoint.
int is the startpoint and char[] is the target.
<br>
### <a name="commands.j">2.4 Commands.java:</a>
Contains the commands that can be executed from the interface.
<br>
#### <a name="commandsC">2.4.1 commands(`String, String`):</a>
Executes the interface commands.
The first Sring is the command name and the second one is the parameter.
<br>
### <a name="stack.j">2.5 Stack.java:</a>
Contains the stack and its functions.
<br>
#### <a name="stack">2.5.1 stack:</a>
`static String[]`<br>
The stack itself.
<br>
#### <a name="stackPointer">2.5.2 stackPointer:</a>
`static int`<br>
The stack pointer.
<br>
#### <a name="pushC">2.5.3 push(String):</a>
Pushes the String onto the stack.
<br>
#### <a name="popC">2.5.4 pop():</a>
Pops the String and prints it.
<br>
### <a name="brainfuckInterpreter.j">2.6 BrainfuckInterpreter.java:</a>
Contains the brainfuck interpreter.
<br>
#### <a name="brainfuckInterpreterC">2.6.1 brainfuckInterpreter(String):</a>
The interpretation program.
<br>
### <a name="objects">2.7 Objects:</a>
#### <a name="cursorOb">2.7.1 Cursor.java:</a>
The cursor object.
#### <a name="fontOb">2.7.2 Font.java:</a>
The font object.
