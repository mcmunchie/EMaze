# EMaze
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
## Table of Contents
+ [About the Game](https://github.com/mcmunchie/emaze#about-the-game)
+ [Input File](https://github.com/mcmunchie/emaze#input-file)
+ [Gameplay](https://github.com/mcmunchie/emaze#gameplay)
+ [About the Program](https://github.com/mcmunchie/emaze#about-the-program)
+ [Example Output](https://github.com/mcmunchie/emaze#example-output)

## About the Game
EMaze is a mini maze game where the player must find a path out of the maze. The maze is represented by a 20 x 20 array of `1's` (i.e. hedges) and `0's` (i.e. foot paths). `E` is the only exit from the maze. 

## Input File
This mini game uses a 20 x 20 array of characters (`1's`, `0's`, and `E`) from the [`maze.txt`](maze.txt) text file.

## Gameplay
The game starts with the player entering a starting point (i.e. a row-column pair). The player can move vertically or horizontally in any direction that contains a `0`. But the player cannot move into a square where a `0` is surrounded by `1's` on all sides. If the player finds themselve's in a square with `1's` on all sides, they must go back and try another path. The player cannot move diagonally.

## About the Program
+ the maze is displayed to the player at the start of the program
+ with each starting point entry into the maze, the program prints the complete maze with an `S` at the entered row-column point followed by the sentence `"I am free!"`, if the player found a path out of the maze
+ otherwise, the program will print the sentence `"Help, I am trapped!"` if the player did not find a path out of the maze
+ the game will notify the player if they entered a square surrounded by `1's`
+ once the player finds a path out, a series of pluses (+) will be displayed showing the path out of the maze

## Example Output
> Trapped in a square
<img src=img\emaze-square-of-ones.png />

> Did not find a path out of the maze
<img src=img\emaze-trapped.png />

> Found a path out of the maze
<img src=img\emaze-freed.png />
