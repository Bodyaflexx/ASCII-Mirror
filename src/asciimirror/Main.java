package asciimirror;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the file path:");
        String path = scanner.nextLine();
        File file = new File(path);
        List<String> strFromFile = new ArrayList<>();
        try (Scanner scannerFromFile = new Scanner(file)) {
            while (scannerFromFile.hasNext()) {
                strFromFile.add(scannerFromFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        int maxLength = findMaxLength(strFromFile);
        List<String> result = addSpaces(maxLength, strFromFile);
        print(result);
    }

    private static int findMaxLength(List<String> list) {
        int maxLength = 0;
        for (String s : list) {
            if (s.length() > maxLength) {
                maxLength = s.length();
            }
        }
        return maxLength;
    }

    private static List<String> addSpaces(int length, List<String> list) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            StringBuilder tmp = new StringBuilder(s);
            while (tmp.length() < length) {
                tmp.append(" ");
            }
            result.add(tmp.toString());
        }
        return result;
    }

    private static void print(List<String> list) {
        for (String s : list) {
            System.out.println(s + " | " + changeSymbols(s));
        }
    }

    private static String changeSymbols(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '<' -> result.append('>');
                case '>' -> result.append('<');
                case ']' -> result.append('[');
                case '[' -> result.append(']');
                case '{' -> result.append('}');
                case '}' -> result.append('{');
                case '(' -> result.append(')');
                case ')' -> result.append('(');
                case '/' -> result.append('\\');
                case '\\' -> result.append('/');
                default -> result.append(s.charAt(i));
            }
        }
        return result.reverse().toString();
    }
}