import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, Set<Integer>> map = new HashMap<>();
        List<String> updates = new ArrayList<>();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.isEmpty()){
                break;
            }
            String[] parts = line.split("\\|");

            int key = Integer.parseInt(parts[0]);

            if(!map.containsKey(key)){
                map.put(key, new HashSet<>());
                map.get(key).add(Integer.parseInt(parts[1]));
            }
            else{
                map.get(key).add(Integer.parseInt(parts[1]));
            }

        }

        while (scanner.hasNextLine()) {
            String line2 = scanner.nextLine();
            updates.add(line2);
        }

        DayFivePartOne(map, updates);
        DayFivePartTwo(map, updates);
    }
    public static void DayFivePartOne(Map<Integer, Set<Integer>> map, List<String> updates){
        int count = 0;

        for (String update : updates){
            String []parts = update.split(",");

            boolean flag = true;
            for (int i = 0; i < parts.length; i++){
                flag = isOrdered(map, i, parts, flag);
                if(!flag){
                   break;
               }
            }
            if(flag){
                count += Integer.parseInt(parts[parts.length / 2]);
            }
        }
        System.out.println(count);
    }

    private static boolean isOrdered(Map<Integer, Set<Integer>> map, int i, String[] parts, boolean flag) {
        for (int j = i + 1; j < parts.length; j++){
            if(!map.containsKey(Integer.parseInt(parts[i])) ||
                    !map.get(Integer.parseInt(parts[i])).contains(Integer.parseInt(parts[j]))){
                   flag = false;
                break;
            }
        }
        return flag;
    }

    public static void DayFivePartTwo(Map<Integer, Set<Integer>> map, List<String> updates){
        List<String> incorrectUpdates = new ArrayList<>();

        for (String update : updates){
            String []parts = update.split(",");

            boolean flag = true;
            for (int i = 0; i < parts.length; i++){
                flag = isOrdered(map, i, parts, flag);
                if(!flag){
                    incorrectUpdates.add(update);
                    break;
                }
            }
        }

        int sum = 0;
        for (String incorrectUpdate : incorrectUpdates){
            sum += makeCorrect(map, incorrectUpdate);
        }
        System.out.println(sum);
    }

    private static int makeCorrect(Map<Integer, Set<Integer>> map, String incorrectUpdate) {
        String []parts = incorrectUpdate.split(",");
        int []correctParts = new int[parts.length];
        for (int i = 0; i < parts.length; i++){
            correctParts[i] = Integer.parseInt(parts[i]);
        }

        for (int i = 0; i < correctParts.length; i++){
            for (int j = i + 1; j < correctParts.length; j++){
                if(!map.containsKey(correctParts[i]) ||
                        !map.get(correctParts[i]).contains(correctParts[j])){
                    int temp = correctParts[i];
                    correctParts[i] = correctParts[j];
                    correctParts[j] = temp;
                }
            }
        }

        return correctParts[correctParts.length / 2];
    }
}