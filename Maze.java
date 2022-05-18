import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Maze {

  private int startRow = 0;
  private int startCol = 0;

  public void maze() {
    Scanner scan = new Scanner(System.in);
    char[][] maze = new char[20][20];

    // read input from maze file
    try {
      scan = new Scanner(new File("maze.txt"));

      for (int i = 0; i < 20; i++) {
        String line = scan.nextLine();
        for (int j = 0; j < 20; j++) {
          maze[i][j] = line.charAt(j);
        }
      }

      scan.close();
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }

    // method call(s) to display starting maze and start maze game
    printMaze(maze);
    mazeGame(maze);
  }

  public void mazeGame(char[][] maze) {
    try (Scanner user = new Scanner(System.in)) {
      String userInput = "";
      do {
        do {
          startRow = -1;
          startCol = -1;

          // prompt user for row-column pair (starting point)
          while (startRow < 0 || startRow > 19) {
            System.out.print("Enter row: ");
            startRow = user.nextInt();

            if (startRow < 0 || startRow > 19) // VALIDATE!
              System.out.println("Invalid entry. That row is out of bounds. Please try again.");
          }
          while (startCol < 0 || startCol > 19) {
            System.out.print("Enter column: ");
            startCol = user.nextInt();

            if (startCol < 0 || startCol > 19) // VALIDATE!
              System.out.println("Invalid entry. That column is out of bounds. Please try again.");
          }

          if (maze[startRow][startCol] == '1') // prompt when user has entered a square with a '1'
            System.out.println("That square contains a 1. Please enter another row-column pair.");
        } while (maze[startRow][startCol] == '1');

        // search for exit path and print results
        printResult(maze, startRow, startCol);

        // prompt user to continue
        System.out.print("\nDo you want to continue? (yes/no): ");
        userInput = user.next();
      } while (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("y"));
    }
  }

  public void printResult(char[][] maze, int startRow, int startCol) {
    boolean pathFound = findPath(maze, startRow, startCol);
    System.out.println("Result:");
    if (!pathFound) {
      System.out.println("Help, I am trapped!");
    } else
      System.out.println("I am free!");
    exitMaze(maze, startRow, startCol);
  }

  public boolean findPath(char maze[][], int startRow, int startCol) {
    // find path out of maze
    if (findPath(maze, new boolean[maze.length][maze.length], startRow, startCol))
      return true;
    return false;
  }

  public boolean findPath(char maze[][], boolean[][] visited, int row, int col) {
    // navigate through maze to find correct exit path (to 'E')
    if (maze[row][col] == 'E')
      return true;

    maze[row][col] = '+'; // outline exit path
    visited[row][col] = true;

    Stack<int[]> stack = new Stack<>();

    // checks for valid square spaces (surrounding '1s' and '0s')
    if (row - 1 >= 0 && maze[row - 1][col] != '1' && !visited[row - 1][col])
      stack.push(new int[] { row - 1, col });
    if (row + 1 < maze.length && maze[row + 1][col] != '1' && !visited[row + 1][col])
      stack.push(new int[] { row + 1, col });
    if (col - 1 >= 0 && maze[row][col - 1] != '1' && !visited[row][col - 1])
      stack.push(new int[] { row, col - 1 });
    if (col + 1 < maze.length && maze[row][col + 1] != '1' && !visited[row][col + 1])
      stack.push(new int[] { row, col + 1 });

    // checks up, down, left, right directions
    while (!stack.empty()) {
      int[] square = stack.pop();

      int newRow = square[0];
      int newCol = square[1];

      if (findPath(maze, visited, newRow, newCol))
        return true;
    }
    return false;
  }

  public char[][] startingPoint(char[][] maze, int row, int column) {
    // finds the starting point entered in the 20 x 20 matrix and replaces the 0
    // with a S
    startRow = row;
    startCol = column;

    maze[startRow][startCol] = 'S';

    return maze;
  }

  public void exitMaze(char[][] maze, int row, int column) { // prints maze with user's starting point
    startingPoint(maze, row, column);
    System.out.print("   ");
    for (int i = 0; i < maze[0].length; i++) {
      System.out.print(String.format("%3d", i));
    }
    System.out.println();
    for (int i = 0; i < maze.length; i++) {
      System.out.print(String.format("%3d", i));
      for (int j = 0; j < maze[i].length; j++) {
        System.out.print(String.format("%3c", maze[i][j]));
      }
      System.out.println();
    }
  }

  public void printMaze(char[][] maze) {
    System.out.print("   ");
    for (int i = 0; i < maze[0].length; i++) {
      System.out.print(String.format("%3d", i));
    }
    System.out.println();
    for (int i = 0; i < maze.length; i++) {
      System.out.print(String.format("%3d", i));
      for (int j = 0; j < maze[i].length; j++) {
        System.out.print(String.format("%3c", maze[i][j]));
      }
      System.out.println();
    }
  }
}
