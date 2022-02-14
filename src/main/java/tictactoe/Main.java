package tictactoe;

import java.util.Scanner;

public class Main {
    static Character[][] ticTacToeGrid;
    static Scanner scanner = new Scanner(System.in);
    static int zero = 0;
    static int size = 3;
    static boolean xTurn = true;

    public static void main(String[] args) {
        initializeTicTacToeGrid();
        printBoard();
        while (true) {
            getCoordinate();
            printBoard();
            String status = checkStatus();
            if (isEnd(status)) {
                break;
            }
        }

    }

    private static boolean isEnd(String status) {
        boolean exit = false;
        boolean draw = status.equals("Draw");
        boolean xWins = status.equals("X wins");
        boolean oWins = status.equals("O wins");
        if (draw || xWins || oWins) {
            exit = true;
        }
        return exit;
    }

    private static void printBoard() {
        System.out.println("---------");
        for (Character[] array : ticTacToeGrid) {
            System.out.print("| ");
            for (Character play : array) {
                System.out.printf("%s ", play);
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    private static void initializeTicTacToeGrid() {
        ticTacToeGrid = new Character[][]{
                {'_', '_', '_'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        };
    }

    private static String checkStatus() {
        int numberX = zero;
        int numberO = zero;
        int numberSpace = zero;
        String result = "";
        for (int i = zero; i < size; i++) {
            for (int j = zero; j < size; j++) {
                if (ticTacToeGrid[i][j] == 'X') {
                    numberX++;
                }
                if (ticTacToeGrid[i][j] == 'O') {
                    numberO++;
                }
                if (ticTacToeGrid[i][j] == '_') {
                    numberSpace++;
                }
            }
        }

        if (Math.abs(numberX - numberO) > 1 || checkStatus('X') && checkStatus('O')) {
            System.out.println("Impossible");
            result = "Impossible";
        } else if (checkStatus('X')) {
            System.out.println("X wins");
            result = "X wins";
        } else if (checkStatus('O')) {
            System.out.println("O wins");
            result = "O wins";
        } else if (numberSpace == zero) {
            System.out.println("Draw");
            result = "Draw";
        } else if (numberSpace > zero) {
            System.out.println("Game not finished");
            result = "Game not finished";
        }
        return result;
    }

    private static boolean checkStatus(char ch) {
        boolean row0 = ticTacToeGrid[0][0] == ch && ticTacToeGrid[0][1] == ch && ticTacToeGrid[0][2] == ch;
        boolean row1 = ticTacToeGrid[1][0] == ch && ticTacToeGrid[1][1] == ch && ticTacToeGrid[1][2] == ch;
        boolean row2 = ticTacToeGrid[2][0] == ch && ticTacToeGrid[2][1] == ch && ticTacToeGrid[2][2] == ch;

        boolean col0 = ticTacToeGrid[0][0] == ch && ticTacToeGrid[1][0] == ch && ticTacToeGrid[2][0] == ch;
        boolean col1 = ticTacToeGrid[0][1] == ch && ticTacToeGrid[1][1] == ch && ticTacToeGrid[2][1] == ch;
        boolean col2 = ticTacToeGrid[0][2] == ch && ticTacToeGrid[1][2] == ch && ticTacToeGrid[2][2] == ch;

        boolean left = ticTacToeGrid[0][0] == ch && ticTacToeGrid[1][1] == ch && ticTacToeGrid[2][2] == ch;
        boolean right = ticTacToeGrid[0][2] == ch && ticTacToeGrid[1][1] == ch && ticTacToeGrid[2][0] == ch;

        return row0 || row1 || row2 || col0 || col1 || col2 || left || right;
    }

    private static void getCoordinate() {
        int x = -1;
        int y = -1;
        while (true) {
            System.out.print("Enter the coordinates: ");
            try {
                x = scanner.nextInt() - 1;
                y = scanner.nextInt() - 1;
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            if (x < 0 || x > 2 || y < 0 || y > 2) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            } else if (ticTacToeGrid[x][y] != '_') {
                System.out.println("This cell is occupied!");
                continue;
            } else {
                if (xTurn) {
                    ticTacToeGrid[x][y] = 'X';
                    xTurn = false;
                    break;
                } else {
                    ticTacToeGrid[x][y] = 'O';
                    xTurn = true;
                    break;
                }

            }
        }

    }

}