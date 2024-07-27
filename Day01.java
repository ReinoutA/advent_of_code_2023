import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class day01 {

    private static HashMap<String, Integer> digitMapping = new HashMap<String, Integer>();

    private static String FILE_NAME = "src/inputs/day01.txt";
    public static void main (String[] args) throws IOException {

        digitMapping.put("zero", 0);
        digitMapping.put("one", 1);
        digitMapping.put("two", 2);
        digitMapping.put("three", 3);
        digitMapping.put("four", 4);
        digitMapping.put("five", 5);
        digitMapping.put("six", 6);
        digitMapping.put("seven", 7);
        digitMapping.put("eight", 8);
        digitMapping.put("nine", 9);

        List<String> lines = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        int totalSum = 0;

        for(String l : lines) {
            int twoDigit = 0;

            for (int i = 0; i < l.length(); i++) {
                char c = l.charAt(i);
                if (c >= '0' && c <= '9') {
                    twoDigit += Character.getNumericValue(c) * 10;
                    break;
                } else if (getDigitValue(l, i) != null) {
                    twoDigit += getDigitValue(l, i) * 10;
                    break;
                }
            }
        
            for(int i = l.length() - 1; i >= 0; i--) {
                char c = l.charAt(i);
                if(c >= '0' && c <= '9') {
                    twoDigit += Character.getNumericValue(c);
                    break;
                }else if(getDigitValue(l, i) != null) {
                    twoDigit += getDigitValue(l, i);
                    break;
                }
            }
            totalSum += twoDigit;
        }
        System.out.println("Solution: " + totalSum);
    }

    private static Integer getDigitValue(String str, int index) {
        for (int i = index; i < Math.min(index + "seven".length(), str.length()); i++) {
            String subStr = str.substring(index, i + 1).toLowerCase();
            if (digitMapping.containsKey(subStr)) {
                return digitMapping.get(subStr);
            }
        }
        return null;
    }
}