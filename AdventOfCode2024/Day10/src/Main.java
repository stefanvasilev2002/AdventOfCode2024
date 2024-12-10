import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> input = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            input.add(line);
        }

        int[][] map = new int[input.size()][input.getFirst().length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                map[i][j] = input.get(i).charAt(j) - '0';
            }
        }

        System.out.println("Total score of all trailheads: " + calculateTotalTrailheadScore(map));
        System.out.println("Total rating of all trailheads: " + calculateTotalTrailheadRating(map));
    }

    private static int calculateTotalTrailheadScore(int[][] map) {
        int totalScore = 0;
        int rows = map.length;
        int cols = map[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 0) {
                    totalScore += calculateTrailheadScore(map, i, j);
                }
            }
        }

        return totalScore;
    }

    private static int calculateTrailheadScore(int[][] map, int startRow, int startCol) {
        int rows = map.length;
        int cols = map[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Set<String> reachableNines = new HashSet<>();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int height = current[2];

            if (map[row][col] == 9) {
                reachableNines.add(row + "," + col);
                continue;
            }

            for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                        && !visited[newRow][newCol]
                        && map[newRow][newCol] == height + 1) {
                    visited[newRow][newCol] = true;
                    queue.add(new int[]{newRow, newCol, height + 1});
                }
            }
        }

        return reachableNines.size();
    }

    private static int calculateTotalTrailheadRating(int[][] map) {
        int totalRating = 0;
        int rows = map.length;
        int cols = map[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map[i][j] == 0) {
                    totalRating += calculateTrailheadRating(map, i, j);
                }
            }
        }

        return totalRating;
    }

    private static int calculateTrailheadRating(int[][] map, int startRow, int startCol) {
        int rating;

        Map<String, Integer> memo = new HashMap<>();

        rating = countDistinctTrails(map, startRow, startCol, 0, memo);

        return rating;
    }

    private static int countDistinctTrails(int[][] map, int row, int col, int currentHeight, Map<String, Integer> memo) {
        int rows = map.length;
        int cols = map[0].length;

        if (row < 0 || row >= rows || col < 0 || col >= cols || map[row][col] != currentHeight) {
            return 0;
        }

        if (map[row][col] == 9) {
            return 1;
        }

        String key = row + "," + col + "," + currentHeight;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int totalTrails = 0;
        for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            totalTrails += countDistinctTrails(map, newRow, newCol, currentHeight + 1, memo);
        }

        memo.put(key, totalTrails);
        return totalTrails;
    }
}
