import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();

        while(scanner.hasNextLine()){
            String []parts = scanner.nextLine().split("\\s+");

            first.add(Integer.valueOf(parts[0]));
            second.add(Integer.valueOf(parts[1]));

        }
        DayOnePartOne(first, second);
        DayOnePartTwo(first, second);
    }

    public static void DayOnePartOne(List<Integer> first, List<Integer> second){
        Collections.sort(first);
        Collections.sort(second);

        int sum = 0;
        for (int i = 0; i < first.size(); i++) {
            sum += Math.abs(first.get(i) - second.get(i));
        }

        System.out.println(sum);
    }

    public static void DayOnePartTwo(List<Integer> first, List<Integer> second){
        int sum = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < first.size(); i++) {
            map.putIfAbsent(second.get(i), 0);
            map.put(second.get(i), map.get(second.get(i)) + 1);
        }

        for (int i = 0; i < first.size(); i++) {
            if(map.containsKey(first.get(i))){
                sum += first.get(i) * map.get(first.get(i));
            }
        }
        System.out.println(sum);
    }
}