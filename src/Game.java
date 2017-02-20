import java.util.Scanner;

public class Game {
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;
    private static String[][] playingField = new String[ROWS][COLUMNS];
    private static final String X = " X ", O = " O ", EMPTY = "   ";
    private static final int IN_PROGRESS = 0, WINNER_X = 1, WINNER_O = 2, STANDOFF = 3;
    private static int gameStatus = IN_PROGRESS;
    private static Scanner input = new Scanner(System.in);
    private static String activePlayer;

    public static void main(String[] args) {
        start();
    }

    public static boolean isAllFilled() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                if (playingField[row][column].equals(EMPTY))
                    return false;
            }
        }
        return true;
    }

    public static void checkGameStatus() {
        gameStatus = setGameStatus();

        if (gameStatus == 1) {
            System.out.println("Winner is X");
        } else if (gameStatus == 2) {
            System.out.println("Winner is O");
        } else if (gameStatus == 3) {
            System.out.println("Standoff");
        } else {
            gameStatus = IN_PROGRESS;
        }
    }

    public static int setGameStatus() {
        int count;
        for (int row = 0; row < ROWS; row++) {
            count = 0;
            for (int column = 0; column < COLUMNS; column++) {
                if (!playingField[row][0].equals(EMPTY) && playingField[row][0].equals(playingField[row][column])) {
                    count++;
                }
            }
            if (count == 3) {
                if (activePlayer.equals(X))
                    return WINNER_X;
                else
                    return WINNER_O;
            }
        }

        for (int column = 0; column < COLUMNS; column++) {
            count = 0;
            for (int row = 0; row < ROWS; row++) {
                if (!playingField[0][column].equals(EMPTY) && playingField[0][column].equals(playingField[row][column])) {
                    count++;
                }
            }
            if (count == 3) {
                if (activePlayer.equals(X))
                    return WINNER_X;
                else
                    return WINNER_O;
            }
        }

        if (!playingField[0][0].equals(EMPTY) && playingField[0][0].equals(playingField[1][1]) &&playingField[0][0].equals(playingField[2][2])) {
            if (activePlayer.equals(X))
                return WINNER_X;
            else
                return WINNER_O;
        }

        if (!playingField[0][2].equals(EMPTY) && playingField[0][2].equals(playingField[1][1]) &&playingField[0][2].equals(playingField[2][0])) {
            if (activePlayer.equals(X))
                return WINNER_X;
            else
                return WINNER_O;
        }

        if (isAllFilled()) {
            return STANDOFF;
        }

        return IN_PROGRESS;
    }

    public static void move() {

        boolean isInputRight = false;
        do {
            System.out.println("Player " + activePlayer + " input row 1-3 and column 1-3");
            int row_num = input.nextInt() - 1;
            int column_num = input.nextInt() - 1;
            if (row_num >= 0 && row_num < ROWS && column_num >= 0 && column_num < COLUMNS && playingField[row_num][column_num].equals(EMPTY)) {
                playingField[row_num][column_num] = activePlayer;
                isInputRight = true;
            } else {
                System.out.println("Wrong input data, please try again...");
            }
        } while (!isInputRight);
    }

    public static void changePlayer() {
        activePlayer = (activePlayer.equals(X)) ? O : X ;
    }

    public static void showPlayingField() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                System.out.print(playingField[row][column]);
                if (column != COLUMNS - 1)
                    System.out.print("|");
            }
            System.out.println();
            if (row != ROWS - 1)
                System.out.println("-----------");
        }
        System.out.println();
    }

    public static void fillAllFields() {
        for (int row = 0; row < ROWS; row++) {
            for (int column = 0; column < COLUMNS; column++) {
                playingField[row][column] = EMPTY;
            }
        }
    }

    public static void start() {
        fillAllFields();
        activePlayer = X;
        showPlayingField();

        do {
            move();
            showPlayingField();
            checkGameStatus();
            changePlayer();
        } while (gameStatus == IN_PROGRESS);
    }
}
