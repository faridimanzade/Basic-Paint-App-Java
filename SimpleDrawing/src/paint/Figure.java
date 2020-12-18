package paint;

import java.awt.*;
import java.io.Serializable;

// Figure is the class for all type of figures
public class Figure implements Serializable {
    public String name;
    // sx - start x  sy - start y  ex - end x  ey - end y
    public int sx, sy, ex, ey;
    public Color color;

    public Figure(String name, int sx, int sy, int ex, int ey, Color color) {
        this.name = name;
        this.sx = sx;
        this.sy = sy;
        this.ex = ex;
        this.ey = ey;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Figure{" +
                "name='" + name + '\'' +
                ", sx=" + sx +
                ", sy=" + sy +
                ", ex=" + ex +
                ", ey=" + ey +
                ", color=" + color +
                '}';
    }
}
