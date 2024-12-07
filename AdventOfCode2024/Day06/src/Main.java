import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<String> input = new ArrayList<>();

        while(scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }

        char [][] matrix = new char[input.size()][input.getFirst().length()];

        for (int i = 0; i < input.size(); i++) {
            matrix[i] = input.get(i).toCharArray();
        }

        //DaySixPartOne(matrix);
        DaySixPartTwo(matrix);
    }

    private static void DaySixPartOne(char[][] matrix) {
        int x = 0, y = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '^') {
                    matrix[i][j] = '.';
                    x = i - 1;
                    y = j;
                }
            }
        }
        boolean [][]graph = new boolean[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                graph[i][j] = false;
            }
        }

        String direction = "UP";
        int count = 0;

        while(true){
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
                break;
            }
            if (matrix[x][y] == '#') {
                switch (direction) {
                    case "UP":
                        direction = "RIGHT";
                        x += 1;
                        y += 1;
                        break;
                    case "RIGHT":
                        direction = "DOWN";
                        x += 1;
                        y -= 1;
                        break;
                    case "DOWN":
                        direction = "LEFT";
                        x -= 1;
                        y -= 1;
                        break;
                    case "LEFT":
                        direction = "UP";
                        x -= 1;
                        y += 1;
                        break;
                }
            }
            if (matrix[x][y] == '.') {
                graph[x][y] = true;
                switch (direction) {
                    case "UP":
                        x -= 1;
                        break;
                    case "RIGHT":
                        y += 1;
                        break;
                    case "DOWN":
                        x += 1;
                        break;
                    case "LEFT":
                        y -= 1;
                        break;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (graph[i][j]) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void DaySixPartTwo(char[][] matrix) {
        int x = 0, y = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '^') {
                    matrix[i][j] = '.';
                    x = i - 1;
                    y = j;
                }
            }
        }

        stuckInLoop(matrix, x, y);
    }

    private static void stuckInLoop(char[][] matrix, int x, int y) {
        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (matrix[i][j] == '.') {
                    matrix[i][j] = '#';
                    counter += checkLoop(matrix, x, y) ? 1 : 0;
                    matrix[i][j] = '.';
                }
            }
        }
        System.out.println(counter);
    }

    private static boolean checkLoop(char[][] matrix, int x, int y) {
        Set<String> visitedStates = new HashSet<>();

        String direction = "UP";

       visitedStates.add(x + " " + y + " " + direction);

        while(true){
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
                break;
            }
            if (matrix[x][y] == '#') {
                switch (direction) {
                    case "UP":
                        direction = "RIGHT";
                        x += 1;
                        y += 1;
                        break;
                    case "RIGHT":
                        direction = "DOWN";
                        x += 1;
                        y -= 1;
                        break;
                    case "DOWN":
                        direction = "LEFT";
                        x -= 1;
                        y -= 1;
                        break;
                    case "LEFT":
                        direction = "UP";
                        x -= 1;
                        y += 1;
                        break;
                }
            }
            if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length) {
                break;
            }
            if (matrix[x][y] == '.') {
                switch (direction) {
                    case "UP":
                        x -= 1;
                        break;
                    case "RIGHT":
                        y += 1;
                        break;
                    case "DOWN":
                        x += 1;
                        break;
                    case "LEFT":
                        y -= 1;
                        break;
                }
            }
            if (visitedStates.contains(x + " " + y + " " + direction)) {
                return true;
            }
            visitedStates.add(x + " " + y + " " + direction);
        }
        return false;
    }
}