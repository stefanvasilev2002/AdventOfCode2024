import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            list.add(line);
        }

        char[][] grid = new char[list.size()][list.getFirst().length()];
        for (int i = 0; i < list.size(); i++) {
            grid[i] = list.get(i).toCharArray();
        }

        DayEight(grid);
    }

    private static void DayEight(char[][] grid) {
        int count = 0, countPartTwo = 0;
        boolean [][]uniqueAntinode = new boolean[grid.length][grid[0].length];
        boolean [][]uniqueAntinodePartTwo = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != '.') {
                    lookForAntiNodes(
                            grid,
                            i,
                            j,
                            grid[i][j],
                            uniqueAntinode,
                            uniqueAntinodePartTwo);
                }
            }
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (uniqueAntinode[i][j]) {
                    count++;
                }
                if (uniqueAntinodePartTwo[i][j]) {
                    countPartTwo++;
                }
            }
        }
        System.out.println(count);
        System.out.println(countPartTwo);
    }

    private static void lookForAntiNodes(char[][] grid, int i, int j, char c, boolean[][] uniqueAntinode, boolean[][] uniqueAntinodePartTwo) {
        for (int k = 0; k < grid.length; k++) {
            for (int l = 0; l < grid[0].length; l++) {
                if (grid[k][l] == c) {
                    if (k == i && l == j) {
                        continue;
                    }
                    if(isInBounds(k + (k - i), grid.length) &&
                            isInBounds(l + (l - j), grid[0].length)) {
                        uniqueAntinode[k + (k - i)][l + (l - j)] = true;
                    }
                    for (int m = 0; ; m++){
                        if(isInBounds(k + (k - i) * m, grid.length) &&
                                isInBounds(l + (l - j) * m, grid[0].length)) {
                            uniqueAntinodePartTwo[k + (k - i) * m][l + (l - j) * m] = true;
                        }
                        else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean isInBounds(int i, int length) {
        return i >= 0 && i < length;
    }
}