import java.util.List;

public class Coordinate {
    private int x;
    private int y;
    private int z;

    public Coordinate(int x, int z, int y) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public static String getResult(List<Coordinate> Points) {
        if (Points.isEmpty()) {
            return "None";
        } else {
            // do something with the list
        }return "Not empty";
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
