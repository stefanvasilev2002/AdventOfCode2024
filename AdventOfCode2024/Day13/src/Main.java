import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        solve(scanner);
        }
    public static void solve(Scanner inputScanner) {
        List<Game> games = new ArrayList<>();
        while (inputScanner.hasNextLine()) {
            String[] buttonA = inputScanner.nextLine().split(":")[1].trim().split(", ");
            String[] buttonB = inputScanner.nextLine().split(":")[1].trim().split(", ");
            String[] prize = inputScanner.nextLine().split(":")[1].trim().split(", ");
            games.add(
                    new Game(
                            new int[]{Integer.parseInt(buttonA[0].split("\\+")[1]), Integer.parseInt(buttonA[1].split("\\+")[1])},
                            new int[]{Integer.parseInt(buttonB[0].split("\\+")[1]), Integer.parseInt(buttonB[1].split("\\+")[1])},
                            new int[]{Integer.parseInt(prize[0].split("=")[1]), Integer.parseInt(prize[1].split("=")[1])}
                    )
            );
            if (inputScanner.hasNextLine())
                inputScanner.nextLine();
        }

        System.out.println(find(games, 0));
        System.out.println(find(games, 10000000000000L));
    }

    record Game(int[] buttonA, int[] buttonB, int[] prize) {
    }

    static long find(List<Game> games, long add) {
        long totalTokensA = 0;
        long totalTokensB = 0;

        for (Game game : games) {
            long[] solutions = solveEquations(game.buttonA[0], game.buttonB[0], game.prize[0] + add,
                    game.buttonA[1], game.buttonB[1], game.prize[1] + add);
            if (solutions[0] != -1L && solutions[1] != -1L) {
                totalTokensA += solutions[0] * 3;
                totalTokensB += solutions[1];
            }
        }
        return totalTokensA + totalTokensB;
    }

    public static long[] solveEquations(long x1, long y1, long a1,
                                        long x2, long y2, long a2) {
        long det = x1 * y2 - y1 * x2;
        if (det == 0) {
            return new long[]{-1, -1};
        }

        long detX = a1 * y2 - y1 * a2;
        long detY = x1 * a2 - a1 * x2;
        if (detX % det != 0 || detY % det != 0) {
            return new long[]{-1, -1};
        }

        return new long[]{detX / det, detY / det};
    }
}