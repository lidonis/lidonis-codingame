import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static final String UNKNOWN_MIME_TYPE = "UNKNOWN";

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        in.nextLine();
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        in.nextLine();
        Map<String, String> mimeTypes = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next(); // file extension
            String MT = in.next(); // MIME type.
            in.nextLine();
            mimeTypes.put(EXT.toLowerCase(), MT);
        }

        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            if (!FNAME.contains(".")) {
                System.out.println(UNKNOWN_MIME_TYPE);
            }  else {
                String fileExtension = FNAME.substring(FNAME.lastIndexOf(".") + 1).toLowerCase();
                if (mimeTypes.containsKey(fileExtension)) {
                    System.out.println(mimeTypes.get(fileExtension));
                } else {
                    System.out.println(UNKNOWN_MIME_TYPE);
                }
            }
        }
    }
}