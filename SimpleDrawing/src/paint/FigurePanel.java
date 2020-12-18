package paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FigurePanel extends JPanel {
    //Coordinates of the figure
    int x = 0, y = 0, x2 = 0, y2 = 0;

    Boolean dragged = false;
    NewMouseListener listener = new NewMouseListener();


    public static Graphics2D gc;
    public static Color color;
    public static Figure shape;

    public FigurePanel() {
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }

    public void paintComponent(Graphics g) {

        gc = (Graphics2D) g;
        super.paintComponent(gc);

        //Add figure depending on the choice to the stack
        if (PaintApp.choice == 1) {
            Figure rect = new Figure("Rectangle", Math.min(x, x2), Math.min(y, y2), Math.max(x, x2), Math.max(y, y2), color);
            PaintApp.figures.push(rect);
        } else if (PaintApp.choice == 2) {
            Figure circle = new Figure("Circle", x, y, x2, y2, color);
            PaintApp.figures.push(circle);
        } else if (PaintApp.choice == 3) {
            Figure line = new Figure("Line", x, y, x2, y2, color);
            PaintApp.figures.push(line);
        }
        //Draw all figures in the stack
        draw();

        //Delete elements added while dragging
        if (dragged) {
            dragged = false;
            if (PaintApp.figures.size() > 0) PaintApp.figures.pop();
        }
    }

    //Loops inside stack and draw suitable figure
    public static void draw() {
        for (Figure figure : PaintApp.figures) {
            switch (figure.name) {
                case "Rectangle":
                    gc.setColor(figure.color);
                    gc.drawRect(Math.min(figure.sx, figure.ex), Math.min(figure.sy, figure.ey), Math.abs(figure.ex - figure.sx), Math.abs(figure.ey - figure.sy));
                    break;
                case "Circle":
                    gc.setColor(figure.color);
                    gc.drawOval(Math.min(figure.sx, figure.ex), Math.min(figure.sy, figure.ey), Math.abs(figure.ex - figure.sx), Math.abs(figure.ey - figure.sy));
                    break;
                case "Line":
                    gc.setColor(figure.color);
                    gc.drawLine(figure.sx, figure.sy, figure.ex, figure.ey);
                    break;
            }
        }
    }

    //Handle Mouse click, drag and release
    class NewMouseListener extends MouseAdapter {
        //Mouse click set the starting point to the event coordinates
        //Choice 1 2 3 stands for Rectangle Circle and Line
        public void mousePressed(MouseEvent e) {
            if (PaintApp.choice == 1 || PaintApp.choice == 2 || PaintApp.choice == 3) {
                setStartPoint(e.getX(), e.getY());
            }
        }

        //while dragging set last points to event coordinates and repaint it
        //Choice 1 2 3 stands for Rectangle Circle and Line
        public void mouseDragged(MouseEvent e) {
            if (PaintApp.choice == 1 || PaintApp.choice == 2 || PaintApp.choice == 3) {
                setEndPoint(e.getX(), e.getY());
                dragged = true;
                repaint();
            }
        }

        public void mouseReleased(MouseEvent e) {
            //Mouse release set end coordinates to the event coordinates and repaint
            //Choice 1 2 3 stands for Rectangle Circle and Line
            if (PaintApp.choice == 1 || PaintApp.choice == 2 || PaintApp.choice == 3) {
                setEndPoint(e.getX(), e.getY());
                repaint();
            }
        }
    }

    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        this.x2 = x;
        this.y2 = y;
    }

}

