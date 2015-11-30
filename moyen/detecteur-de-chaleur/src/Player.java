import java.util.Objects;
import java.util.Scanner;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int W = in.nextInt(); // width of the building.
        int H = in.nextInt(); // height of the building.
        int N = in.nextInt(); // maximum number of turns before game over.
        int X0 = in.nextInt();
        int Y0 = in.nextInt();
        Building building = new Building(W, H, new Coordinate(X0, Y0));

        // game loop
        while (true) {
            Direction direction = Direction.valueOf(in.next()); // the direction of the bombs from batman's current location (U, UR, R, DR, D, DL, L or UL)

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.err.println(direction);

            Coordinate nextCoordinate = building.getWhereToJump(direction);
            System.out.println(nextCoordinate); // the location of the next window Batman should jump to.
            building.moveBatman(nextCoordinate);
        }
    }
}

class Building {
    private Coordinate bombMinCoordinate = new Coordinate(0, 0);
    private Coordinate bombMaxCoordinate;

    private Coordinate batman;

    public Building(int width, int height, Coordinate batman) {
        this.bombMaxCoordinate = new Coordinate(width - 1, height - 1);
        this.batman = batman;
        System.err.println("batman " + batman);
        System.err.println("min " + bombMinCoordinate);
        System.err.println("max " + bombMaxCoordinate);
    }


    public void moveBatman(Coordinate batman) {
        this.batman = batman;
    }

    public Coordinate getWhereToJump(Direction direction) {
        int xOpposite = batman.getX();
        switch (direction.getRight()){
            case 1: xOpposite = bombMaxCoordinate.getX();
                bombMinCoordinate = new Coordinate(batman.getX(), bombMinCoordinate.getY());
                break;
            case 0: xOpposite = batman.getX();
                bombMinCoordinate = new Coordinate(batman.getX(), bombMinCoordinate.getY());
                bombMaxCoordinate = new Coordinate(batman.getX(), bombMaxCoordinate.getY());
                break;
            case -1: xOpposite = bombMinCoordinate.getX();
                bombMaxCoordinate = new Coordinate(batman.getX(), bombMaxCoordinate.getY());
        }

        int yOpposite = batman.getY();
        switch (direction.getDown()){
            case 1: yOpposite = bombMaxCoordinate.getY();
                bombMinCoordinate = new Coordinate(bombMinCoordinate.getX(), batman.getY());
                break;
            case 0: yOpposite = batman.getY();
                bombMinCoordinate = new Coordinate(bombMinCoordinate.getX(), batman.getY());
                bombMaxCoordinate = new Coordinate(bombMaxCoordinate.getX(), batman.getY());
                break;
            case -1: yOpposite = bombMinCoordinate.getY();
                bombMaxCoordinate = new Coordinate(bombMaxCoordinate.getX(), batman.getY());
        }

        Coordinate center = batman.center(new Coordinate(xOpposite, yOpposite));
        if(center.equals(batman)){
            center = new Coordinate(center.getX() + 1, center.getY() + 1);
        }
        return center;
    }
}

enum Direction {
    U(-1,0),UR(-1,1),R(0,1),DR(1,1),D(1,0),DL(1,-1),L(0,-1),UL(-1,-1);

    private final int down;
    private final int right;

    Direction(int down, int right) {
        this.down = down;
        this.right = right;
    }

    public int getDown() {
        return down;
    }

    public int getRight() {
        return right;
    }
}

class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate center(Coordinate c) {
        return new Coordinate((x + c.x) / 2, (y + c.y) / 2);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}