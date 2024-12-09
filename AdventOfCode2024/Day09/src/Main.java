import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        calculateChecksum(line);
    }

    public static void calculateChecksum(String input) {
        List<Integer> fileLengths = new ArrayList<>();
        List<Integer> freeSpaceLengths = new ArrayList<>();

        for (int i = 0; i < input.length(); i += 2) {
            fileLengths.add(Character.getNumericValue(input.charAt(i)));
            if (i + 1 < input.length()){
                freeSpaceLengths.add(Character.getNumericValue(input.charAt(i + 1)));
            }
        }

        List<String> diskMap = createDiskMap(fileLengths, freeSpaceLengths);
        List<String> map1 = compactDisk(diskMap);

        diskMap = createDiskMap(fileLengths, freeSpaceLengths);
        List<String> map2 = compactDiskPartTwo(diskMap);

        System.out.println("Checksum part 1: " + calculateChecksum(map1));
        System.out.println("Checksum part 2: " + calculateChecksum(map2));
    }

    private static List<String> compactDiskPartTwo(List<String> diskMap) {
        for (int i = diskMap.size() - 1; i >= 0;) {
            if (Objects.equals(diskMap.get(i), ".")) {
                i--;
                continue;
            }

            int fileCount = 0;
            String lastFile = diskMap.get(i);
            for (int j = i; j >= 0; j--) {
                if (Objects.equals(diskMap.get(j), lastFile)) {
                    fileCount++;
                } else {
                    break;
                }
            }

            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (Objects.equals(diskMap.get(j), ".")) {
                    int freeSpaceCount = 0;
                    for (int k = j; k < i; k++) {
                        if (Objects.equals(diskMap.get(k), ".")) {
                            freeSpaceCount++;
                        } else {
                            break;
                        }
                    }
                    if (freeSpaceCount >= fileCount) {
                        for (int k = 0; k < fileCount; k++) {
                            diskMap.set(j + k, lastFile);
                            diskMap.set(i, ".");
                            i--;
                            flag = true;
                        }
                        break;
                    }
                    else {
                        j += freeSpaceCount - 1;
                    }
                }
            }
            if (!flag){
                i -= fileCount;
            }
        }

        return diskMap;
    }

    private static List<String> createDiskMap(List<Integer> fileLengths, List<Integer> freeSpaceLengths) {
        List<String> diskMap = new ArrayList<>();
        int fileId = 0;
        for (int i = 0; i < fileLengths.size(); i++) {
            for (int j = 0; j < fileLengths.get(i); j++) {
                diskMap.add(String.valueOf(fileId));
            }
            if (i >= freeSpaceLengths.size()) {
                break;
            }
            for (int j = 0; j < freeSpaceLengths.get(i); j++) {
                diskMap.add(String.valueOf('.'));
            }
            fileId++;
        }
        return diskMap;
    }

    private static List<String> compactDisk(List<String> diskMap) {

        for (int i = 0; i < diskMap.size() - 1; i++) {
            if (Objects.equals(diskMap.getLast(), ".")) {
                diskMap.removeLast();
                i--;
                continue;
            }
            if (i >= diskMap.size() - 1) {
                break;
            }
            if (Objects.equals(diskMap.get(i), ".") && !Objects.equals(diskMap.getLast(), ".")) {
                diskMap.set(i, diskMap.getLast());
                diskMap.removeLast();
            }
        }
        if (Objects.equals(diskMap.getLast(), ".")) {
            diskMap.removeLast();
        }
        return diskMap;
    }

    private static long calculateChecksum(List<String> diskMap) {
        long checksum = 0;
        for (int i = 0; i < diskMap.size(); i++) {
            String block = diskMap.get(i);
            if (Objects.equals(block, ".")) {
                continue;
            }
            checksum += (long) i * Long.parseLong(block);

        }
        return checksum;
    }
}