import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Long, List<Long>> map = new HashMap<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String []parts = line.split(": ");

            long key = Long.parseLong(parts[0]);
            String []values = parts[1].split(" ");

            map.put(key, Arrays.stream(values).map(Long::parseLong).toList());
        }
        long sum1 = 0;
        long sum2 = 0;

        for (Map.Entry<Long, List<Long>> entry : map.entrySet()) {
            long key = entry.getKey();
            List<Long> values = entry.getValue();

            if (equalsTarget(values, key, 1)){
                sum1 += key;
            }
            if (equalsTarget(values, key, 2)){
                sum2 += key;
            }
        }
        System.out.println(sum1);

        System.out.println(sum2);
    }

    private static boolean equalsTarget(List<Long> values, long key, int part) {
        List<String> combinations;
        if (part == 1) {
            combinations = generateCombinations(values.size() - 1, 1);
        } else {
            combinations = generateCombinations(values.size() - 1, 2);
        }

        for (String combination : combinations) {
            if (evaluateExpression(values, combination) == key) {
                return true;
            }
        }
        return false;
    }
    private static long evaluateExpression(List<Long> values, String combination) {
        String []operators = combination.split("");
        long result = values.getFirst();

        for (int i = 1; i < values.size(); i++) {
            if (operators[i - 1].equals("+")) {
                result += values.get(i);
            } else if (operators[i - 1].equals("*")) {
                result *= values.get(i);
            }
            else if (operators[i - 1].equals("|")) {
                result = Long.parseLong(String.valueOf(result) + String.valueOf(values.get(i)));
            }
        }

        return result;
    }
    private static List<String> generateCombinations(int i, int part) {
        List<String> combinations = new ArrayList<>();

        if (part == 1) {
            generateCombinationsHelperOne(i, "", combinations);
        } else {
            generateCombinationsHelperTwo(i, "", combinations);
        }

        return combinations;
    }

    private static void generateCombinationsHelperOne(int i, String s, List<String> combinations) {
        if (i == 0) {
            combinations.add(s);
            return;
        }

        generateCombinationsHelperOne(i - 1, s + "+", combinations);
        generateCombinationsHelperOne(i - 1, s + "*", combinations);
    }
    private static void generateCombinationsHelperTwo(int i, String s, List<String> combinations) {
        if (i == 0) {
            combinations.add(s);
            return;
        }

        generateCombinationsHelperTwo(i - 1, s + "+", combinations);
        generateCombinationsHelperTwo(i - 1, s + "*", combinations);
        generateCombinationsHelperTwo(i - 1, s + "|", combinations);
    }
}