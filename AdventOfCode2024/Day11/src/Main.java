import java.util.*;

public class Main {
    private static final Map<String, Long> cache = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] numbers = line.split(" ");

        long sum25 = 0;
        long sum75 = 0;
        for (String num : numbers) {
            sum25 += solve(num, 25);
            sum75 += solve(num, 75);
        }

        System.out.println(sum25);
        System.out.println(sum75);
    }

    private static long solve(String n, int steps) {
        String key = n + "#" + steps;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (steps == 0) {
            return 1;
        }

        long result;
        if (n.equals("0")) {
            result = solve("1", steps - 1);
        } else if (n.length() % 2 == 0) {
            int mid = n.length() / 2;
            String left = String.valueOf(Long.parseLong(n.substring(0, mid)));
            String right = String.valueOf(Long.parseLong(n.substring(mid)));
            result = solve(left, steps - 1) + solve(right, steps - 1);
        } else {
            result = solve(String.valueOf(Long.parseLong(n) * 2024), steps - 1);
        }

        cache.put(key, result);
        return result;
    }
}