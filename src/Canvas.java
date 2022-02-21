package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import javax.swing.JPanel;

public class Canvas extends JPanel {

    private Graphics2D graphics2d;
    private Mandelbrot mandelbrot = new Mandelbrot(this);

    private Position startPosition = new Position(0, 0, 1);
    private Position position = new Position(0, 0, 1);
    private Position oldPosition = new Position(0, 0, 1);

    private boolean translate = false;

    public int[][] screenData = new int[Main.width][Main.height];

    Canvas() {
        this.setPreferredSize(new Dimension(Main.width, Main.height));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                startPosition.x = event.getX();
                startPosition.y = event.getY();
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                position.x -= event.getX() - startPosition.x;
                position.y -= event.getY() - startPosition.y;

                translate = false;
                repaint();
            }

            // @Override
            // public void mouseWheelMoved(MouseWheelEvent event) {
            // // System.out.println(Integer.toString(event.getWheelRotation()));
            // System.out.println("scroll");
            // }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent event) {
                // translation.x = event.getX() - startPosition.x;
                // translation.y = event.getY() - startPosition.y;

                // position.x += translation.x;
                // position.y += translation.y;
                
                // startPosition.x = event.getX();
                // startPosition.y = event.getY();
                
                // translate = true;
                // repaint();
            }
        });

        this.addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent event) {
                oldPosition.zoom = position.zoom;
                if (event.getPreciseWheelRotation() > 0) {
                    position.zoom *= -0.25 * event.getPreciseWheelRotation() + 1;
                }
                else {
                    position.zoom *= 1.0 / (0.25 * event.getPreciseWheelRotation() + 1);
                }

                // System.out.println(Double.toString((((position.zoom / oldPosition.zoom) - 1.0) * ((double) event.getX() - (double) position.x))));
                position.x -= (int) Math.round(((position.zoom / oldPosition.zoom) - 1.0) * ((double) ((Main.width - event.getX()) - (0.5 * Main.width)) - (double) position.x));
                position.y -= (int) Math.round(((position.zoom / oldPosition.zoom) - 1.0) * ((double) ((Main.height - event.getY()) - (0.5 * Main.height)) - (double) position.y));

                translate = false;
                repaint();
            }
        });
    }

    public void paint(Graphics graphics) {
        graphics2d = (Graphics2D) graphics;

        if (translate) {
            drawFromMemory(position);
        }
        else {
            mandelbrot.render(position);
        }
    }

    public void drawPixel(int x, int y, Color color) {
        graphics2d.setColor(color);
        graphics2d.drawRect(x, y, 0, 0);
    }

    private void drawFromMemory(Position position) {
        // System.out.println("drawFromMemory");
        clear();
        for (int x = 0; x < Main.width; x++) {
            for (int y = 0; y < Main.height; y++) {
                int greyscale = screenData[x][y];
                // System.out.println("pixelMem: " + Integer.toString(x) + ", " + Integer.toString(y));
                if (x + position.x >= 0 && x + position.x <= Main.width && y + position.y >= 0 && y + position.y <= Main.height) {
                    drawPixel(x + position.x, y + position.y, new Color(greyscale, greyscale, greyscale));
                }
            }
        }
        // System.out.println("done");
    }

    private void clear() {
        graphics2d.setColor(Color.BLACK);
        graphics2d.fillRect(0, 0, Main.width, Main.height);
    }
}
