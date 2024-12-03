import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> input = new ArrayList<>();

        while(scanner.hasNextLine()){
            input.add(scanner.nextLine());
        }

        DayThreePartOne(input);
    }
    public static void DayThreePartOne(List<String> input){
        String regex = "mul\\((\\d+),(\\d+)\\)|(do|don't)\\(\\)";

        Pattern pattern = Pattern.compile(regex);

        long sum = 0;
        long filtered = 0;
        boolean enabled = true;

        for (String s : input) {
            Matcher matcher = pattern.matcher(s);

            while (matcher.find()) {
                if (matcher.group(0).startsWith("mul")) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    sum += (long) x * y;

                    if (enabled) {
                        filtered += (long) x * y;
                    }
                }
                else {
                    enabled = !matcher.group(0).startsWith("don't");
                }

            }
        }

        System.out.println(sum);
        System.out.println(filtered);
    }
}