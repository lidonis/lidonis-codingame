import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int l = in.nextInt(); in.nextLine();
        int h = in.nextInt(); in.nextLine();

        Map<Character, List<String>> alphabet = new HashMap<>();

        String text = in.nextLine();

        for (int row = 0; row < h; row++) {
            String line = in.nextLine();
            char currentChar = 'A';
            for (int index = 0; index < 27*l; index += l) {
                if (!alphabet.containsKey(currentChar)) {
                    alphabet.put(currentChar, new ArrayList<>());
                }
                alphabet.get(currentChar).add(line.substring(index, index +l));
                if (currentChar == 'Z') {
                    currentChar = '?';
                } else {
                    currentChar = (char) (currentChar + 1);
                }
            }

            for (char letter : text.toCharArray()) {
                System.out.print(
                        alphabet.containsKey(Character.toUpperCase(letter))
                                ? alphabet.get(Character.toUpperCase(letter)).get(row)
                                : alphabet.get('?').get(row)

                );
            }
            System.out.println();
        }
    }
}