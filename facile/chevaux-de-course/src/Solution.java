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
        int n = in.nextInt();

        TreeSet<Integer> powers = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int power = in.nextInt();
            if (powers.contains(power)) {
                // best match
                System.out.println(0);
                return;
            }
            powers.add(power);
        }

        Integer lastPower = null;
        Integer bestPower = Integer.MAX_VALUE;

        for (int power : powers) {
            if (lastPower != null && power - lastPower < bestPower) {
                bestPower = power - lastPower;
            }
            lastPower = power;
        }
        System.out.println(bestPower);
    }
}