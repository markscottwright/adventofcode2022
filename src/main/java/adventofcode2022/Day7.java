package adventofcode2022;

import java.util.Arrays;
import java.util.HashMap;

public class Day7 {
    static HashMap<String, Long> parseScript(String script) {
        HashMap<String, Long> directorySizes = new HashMap<>();
        String currentPath = "";
        for (String commandAndResults : script.split("\\$ ")) {

            if (commandAndResults.startsWith("cd ")) {
                String subDirectory = commandAndResults.trim().split(" ")[1];
                if (subDirectory.equals("..")) {
                    currentPath = currentPath.substring(0, currentPath.lastIndexOf("/"));
                    if (currentPath.equals(""))
                        currentPath = "/";
                } else if (subDirectory.equals("/")) {
                    currentPath = "/";
                } else {
                    if (currentPath.equals("/"))
                        currentPath += subDirectory;
                    else
                        currentPath += "/" + subDirectory;
                }
            } else if (commandAndResults.startsWith("ls")) {
                directorySizes.put(currentPath, Arrays.stream(commandAndResults.split("\n")).skip(1)
                        .filter(l -> !l.startsWith("dir ")).mapToLong(l -> Long.parseLong(l.split(" ")[0])).sum());
            }
        }

        return directorySizes;
    }

    static HashMap<String, Long> sizesWithChildren(HashMap<String, Long> directorySizes) {
        HashMap<String, Long> out = new HashMap<>();
        for (String directory : directorySizes.keySet()) {
            out.put(directory, sizeWithChildren(directory, directorySizes));
        }
        return out;
    }

    private static long sizeWithChildren(String directory, HashMap<String, Long> directorySizes) {
        //@formatter:off
        return directorySizes.keySet().stream()
                .filter(d -> d.startsWith(directory))
                .mapToLong(directorySizes::get)
                .sum();
        //@formatter:on
    }

    public static void main(String[] args) {
        HashMap<String, Long> directorySizes = parseScript(Util.inputAsString(Day7.class));
        HashMap<String, Long> sizesWithChildren = sizesWithChildren(directorySizes);
        long totalSizeOfMatchingDirectories = sizesWithChildren.values().stream().filter(v -> v < 100_000)
                .mapToLong(v -> v).sum();
        System.out.println("Day 7 part 1: " + totalSizeOfMatchingDirectories);

        long unusedSpace = 70_000_000 - sizesWithChildren.get("/");
        long needToDelete = 30_000_000 - unusedSpace;
        long sizeOfSmallestDeletion = sizesWithChildren.values().stream().filter(s -> s >= needToDelete)
                .mapToLong(v -> v).min().getAsLong();
        System.out.println("Day 7 part 2: " + sizeOfSmallestDeletion);
    }
}
