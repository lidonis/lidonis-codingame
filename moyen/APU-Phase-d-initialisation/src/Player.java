import java.nio.channels.Pipe;
import java.util.*;

/**
 * Don't let the machines win. You are humanity's last hope...
 **/
class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int width = in.nextInt(); // the number of cells on the X axis
        in.nextLine();
        int height = in.nextInt(); // the number of cells on the Y axis
        in.nextLine();
        MicroChip microChip = new MicroChip(width, height);
        for (int i = 0; i < height; i++) {
            String line = in.nextLine(); // width characters, each either 0 or .
            char[] chars = line.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                if(chars[j] == '0'){
                    microChip.add(new Coordinate(j, i));
                }
            }
        }
        System.err.println(microChip);
        microChip.printNeighbours();
    }
}

class MicroChip {
    public static final Coordinate EMPTY = new Coordinate(-1, -1);
    private int width;
    private int height;
    private List<Coordinate> cells = new ArrayList<>();

    public MicroChip(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void add(Coordinate coordinate) {
        cells.add(coordinate);
    }

    public void printNeighbours() {
        for (Coordinate cell : cells) {
            System.out.println(cell + " " + getRightNeighbour(cell) + " " + getBottomNeighbour(cell));
        }
    }

    private Coordinate getRightNeighbour(Coordinate cell) {
        if(cell.getX() == width - 1){
            return EMPTY;
        }else {
            Coordinate rightCoordinate = new Coordinate(cell.getX() + 1, cell.getY());
            Coordinate rightCell = getCellAt(rightCoordinate);
            if(rightCell.equals(EMPTY)){
                return getRightNeighbour(rightCoordinate);
            }else {
                return rightCell;
            }
        }
    }

    private Coordinate getBottomNeighbour(Coordinate cell) {
        if(cell.getY() == height - 1){
            return EMPTY;
        }else {
            Coordinate bottomCoordinate = new Coordinate(cell.getX(), cell.getY() + 1);
            Coordinate bottomCell = getCellAt(bottomCoordinate);
            if(bottomCell.equals(EMPTY)){
                return getBottomNeighbour(bottomCoordinate);
            }else {
                return bottomCell;
            }
        }
    }

    private Coordinate getCellAt(Coordinate coordinate){
        return cells.stream().filter(c -> c.equals(coordinate)).findAny().orElse(EMPTY);
    }

    @Override
    public String toString() {
        return "MicroChip{" +
                "width=" + width +
                ", height=" + height +
                ", cells=" + cells +
                '}';
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