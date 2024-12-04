import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int[][] DIRECTIONS = {
            {0, 1},   // Horizontal right
            {0, -1},  // Horizontal left
            {1, 0},   // Vertical down
            {-1, 0},  // Vertical up
            {1, 1},   // Diagonal down-right
            {1, -1},  // Diagonal down-left
            {-1, -1}, // Diagonal up-left
            {-1, 1}   // Diagonal up-right
    };
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> input= new ArrayList<>();

        while(scanner.hasNextLine()){
            input.add(scanner.nextLine());
        }

        char [][] matrix = new char[input.size()][input.getFirst().length()];

        for(int i = 0; i < input.size(); i++){
            matrix[i] = input.get(i).toCharArray();
        }

        DayFourPartOne(matrix);
        DayFourPartTwo(matrix);
    }
    public static void DayFourPartOne(char[][] grid){
        String target = "XMAS";

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                for (int[] direction : DIRECTIONS) {
                    int dRow = direction[0];
                    int dCol = direction[1];
                    if (isWordPresent(grid, row, col, target, dRow, dCol)) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }
    private static boolean isWordPresent(char[][] grid, int row, int col, String target, int dRow, int dCol) {
        int rows = grid.length;
        int cols = grid[0].length;
        int targetLength = target.length();

        for (int i = 0; i < targetLength; i++) {
            int newRow = row + i * dRow;
            int newCol = col + i * dCol;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return false;
            }

            if (grid[newRow][newCol] != target.charAt(i)) {
                return false;
            }
        }

        return true;
    }
    public static void DayFourPartTwo(char[][] grid){
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (isMas(grid, row, col)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
    private static boolean isMas(char[][] grid, int row, int col) {
        if (grid[row][col] != 'A') {
            return false;
        }

        return (
                grid[row - 1][col - 1] == 'M' &&
                        grid[row + 1][col + 1] == 'S' &&
                        grid[row - 1][col + 1] == 'M' &&
                        grid[row + 1][col - 1] == 'S')
                || (
                grid[row - 1][col - 1] == 'M' &&
                        grid[row + 1][col + 1] == 'S' &&
                        grid[row - 1][col + 1] == 'S' &&
                        grid[row + 1][col - 1] == 'M')
                || (
                grid[row - 1][col - 1] == 'S' &&
                        grid[row + 1][col + 1] == 'M' &&
                        grid[row - 1][col + 1] == 'S' &&
                        grid[row + 1][col - 1] == 'M')
                || (
                grid[row - 1][col - 1] == 'S' &&
                        grid[row + 1][col + 1] == 'M' &&
                        grid[row - 1][col + 1] == 'M' &&
                        grid[row + 1][col - 1] == 'S');
    }

}