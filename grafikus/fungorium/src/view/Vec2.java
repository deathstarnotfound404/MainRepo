package view;

public class Vec2 {
    private int x, y;

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public Vec2 add(Vec2 other) {
        return new Vec2(this.x + other.x, this.y + other.y);
    }

    public void add(double dx, double dy) {
        this.x += (int)dx;
        this.y += (int)dy;
    }
}

