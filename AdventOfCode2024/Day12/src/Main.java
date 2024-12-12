import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> list = new ArrayList<>();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            list.add(line);
        }

        char[][] grid = new char[list.size()][list.getFirst().length()];

        for(int i = 0; i < list.size(); i++){
            for(int j = 0; j < list.get(i).length(); j++){
                grid[i][j] = list.get(i).charAt(j);
            }
        }

        calculateFence(grid);

    }

    private static void calculateFence(char[][] grid) {
        long fencePrice = 0;
        long fenceDiscount = 0;
        boolean [][] visitedArea = new boolean[grid.length][grid[0].length];
        boolean [][] visitedPerimeter = new boolean[grid.length][grid[0].length];
        boolean [][] visitedSides = new boolean[grid.length][grid[0].length];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                long area;
                long perimeter;
                long fenceSides;
                if (!visitedArea[i][j]){
                    area = dfsArea(grid, visitedArea, i, j, grid[i][j]);
                    perimeter = dfsPerimeter(grid, visitedPerimeter, i, j, grid[i][j]);
                    fenceSides = dfsSides(grid, visitedSides, i, j, grid[i][j]);

                    fencePrice += area * perimeter;
                    fenceDiscount += area * fenceSides;
                }
            }
        }
        System.out.println(fencePrice);
        System.out.println(fenceDiscount);
    }

    private static int dfsSides(char[][] grid, boolean[][] visited, int i, int j, char c) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] != c) {
            return 0;
        }

        int sides = 0;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{i, j});

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            int x = curr[0], y = curr[1];

            if (visited[x][y]) continue;
            visited[x][y] = true;

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] != c) {
                    boolean isContinuation = false;
                    if (dir % 2 == 0) {
                        if (y > 0 && grid[x][y-1] == c &&
                                (nx < 0 || nx >= grid.length || grid[nx][y-1] != c)) {
                            isContinuation = true;
                        }
                    } else {
                        if (x > 0 && grid[x-1][y] == c &&
                                (ny < 0 || ny >= grid[0].length || grid[x-1][ny] != c)) {
                            isContinuation = true;
                        }
                    }
                    if (!isContinuation) sides++;
                } else {
                    stack.push(new int[]{nx, ny});
                }
            }
        }

        return sides;
    }

    private static long dfsPerimeter(char[][] grid, boolean[][] visited, int i, int j, char c) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || grid[i][j] != c){
            return 1;
        }
        if(visited[i][j]){
            return 0;
        }
        visited[i][j] = true;
        return dfsPerimeter(grid, visited, i + 1, j, c) +
                dfsPerimeter(grid, visited, i - 1, j, c) +
                dfsPerimeter(grid, visited, i, j + 1, c) +
                dfsPerimeter(grid, visited, i, j - 1, c);
    }

    private static long dfsArea(char[][] grid, boolean[][] visited, int i, int j, char c) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || visited[i][j] || grid[i][j] != c){
            return 0;
        }
        visited[i][j] = true;
        return 1 +
                dfsArea(grid, visited, i + 1, j, c) +
                dfsArea(grid, visited, i - 1, j, c) +
                dfsArea(grid, visited, i, j + 1, c) +
                dfsArea(grid, visited, i, j - 1, c);
    }
}