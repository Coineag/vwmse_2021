
import java.util.Scanner;

import binsuchbaum.AllWords;

// wc.java
public class wc {
    public static void main(String[] s) {
        AllWords words = new AllWords();
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("[\\s,.:;()!?\"_-]+");
        // scanner.useDelimiter("[\\p{Punct}\\s]+");
        while (scanner.hasNext()) {
            String t = scanner.next();
            words.register(t);
        }
        scanner.close();
        words.printWords();
        // und gib alle Wörter aus
    }
}