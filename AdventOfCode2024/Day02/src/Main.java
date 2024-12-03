import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<List<Integer>> reports = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            List<Integer> report = new ArrayList<>();

            for (String s : line.split("\\s+")) {
                report.add(Integer.parseInt(s));
            }
            reports.add(report);
        }

        DayTwoPartOne(reports);
        DayTwoPartTwo(reports);
    }

    public static void DayTwoPartOne(List<List<Integer>> reports){
        int count = 0;

        for (List<Integer> report : reports){
            boolean isIncreasing = report.get(0) < report.get(1);

            if (Math.abs(report.get(0) - report.get(1)) > 3 || Math.abs(report.get(0) - report.get(1)) == 0){
                continue;
            }

            boolean isSafe = true;

            for(int i = 1; i < report.size() - 1; i++){
                if(isIncreasing){
                    if (report.get(i) >= report.get(i + 1) ||
                            report.get(i + 1) - report.get(i) > 3) {
                        isSafe = false;
                        break;
                    }
                }else{
                    if (report.get(i) <= report.get(i + 1) ||
                            report.get(i) - report.get(i + 1) > 3) {
                        isSafe = false;
                        break;
                    }
                }
            }
            if (isSafe){
                count++;
            }
        }
        System.out.println(count);
    }

    public static void DayTwoPartTwo(List<List<Integer>> reports){
        int count = 0;

        for (List<Integer> report : reports){

            if (isSafe(report)){
                count++;
            }
            else {
                count+= dampener(report);
            }
        }
        System.out.println(count);
    }

    private static int dampener(List<Integer> report) {
        for (int i = 0; i < report.size(); i++){
            List<Integer> newReport = new ArrayList<>(report);
            newReport.remove(i);
            if (isSafe(newReport)){
                return 1;
            }
        }
        return 0;
    }

    private static boolean isSafe(List<Integer> report) {
        boolean isIncreasing = report.get(0) < report.get(1);

        if (Math.abs(report.get(0) - report.get(1)) > 3 || Math.abs(report.get(0) - report.get(1)) == 0){
            return false;
        }

        boolean isSafe = true;

        for(int i = 1; i < report.size() - 1; i++){
            if(isIncreasing){
                if (report.get(i) >= report.get(i + 1) ||
                        report.get(i + 1) - report.get(i) > 3) {
                    isSafe = false;
                    break;
                }
            }else{
                if (report.get(i) <= report.get(i + 1) ||
                        report.get(i) - report.get(i + 1) > 3) {
                    isSafe = false;
                    break;
                }
            }
        }
        return isSafe;
    }
}