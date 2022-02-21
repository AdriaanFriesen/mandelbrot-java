package src;

public class Position {
    public int x = 0;
    public int y = 0;
    public double zoom = 1.0;

    Position(int x, int y, double zoom) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
    }

    public void set(int x, int y, double zoom) {
        this.x = x;
        this.y = y;
        this.zoom = zoom;
    }
}
