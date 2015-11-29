import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // the number of temperatures to analyse
        int result;
        if(n == 0){
            result = 0;
        } else {
            in.nextLine();
            List<String> temps = Arrays.asList(in.nextLine().split(" "));
            System.err.println(temps);
            result = temps.stream().map(Integer::valueOf).reduce(
                    (i1, i2) -> Math.abs(i1) > Math.abs(i2)
                            || Math.abs(i1) == Math.abs(i2) && i2 > i1 ? i2 : i1
            ).get();
        }
        System.out.println(result);
    }
}