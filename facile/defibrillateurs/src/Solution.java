import java.util.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String LON = in.next();
        in.nextLine();
        String LAT = in.next();
        in.nextLine();
        Coordinate userCoordinate = new Coordinate(LON, LAT);
        int N = in.nextInt();
        in.nextLine();
        List<Defibrillateur> defibrillateurs = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            String DEFIB = in.nextLine();
            defibrillateurs.add(new Defibrillateur(DEFIB));
        }

        Defibrillateur defibrillateur = defibrillateurs.stream().min(Comparator.comparing(d -> d.getCoordinate().distance(userCoordinate))).get();

        // Write an action using System.out.println()
        // To debug: System.err.println("Debug messages...");

        System.out.println(defibrillateur);
    }
}

class Defibrillateur {

    private String nom;
    private Coordinate coordinate;

    public Defibrillateur(String line) {
        String[] strings = line.split(";");
        nom = strings[1];
        coordinate = new Coordinate(strings[4], strings[5]);
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public String toString() {
        return nom;
    }
}




class Coordinate {
    private double x;
    private double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(String x, String y) {
        this(getDouble(x), getDouble(y));
    }

    private static Double getDouble(String s) {
        return Double.valueOf(s.replace(",","."));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Coordinate c) {
        double x1 = (c.x - x) * Math.cos((x+c.x) / 2);
        double y1 = c.y - y;
        return Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2)) * 6371;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}