package src;

import java.awt.Color;

public class Mandelbrot {
    
    double highestDimension = (double) Math.max(Main.width, Main.height);

    Canvas canvas;

    Mandelbrot(Canvas canvas) {
        this.canvas = canvas;
    }

    private void setCheck(Complex c, int x, int y) {
        Complex z = new Complex(0, 0);
        int max_i = 100;
        int i = max_i;
        
        while (Complex.absolute(z) < 2 && i > 0) {
            z = Complex.add(Complex.square(z), c);
            i--;
        }

        int greyscale = (int) Math.round(Math.pow(-((double) i / 99.0) + 1, 0.9) * 255);
        canvas.drawPixel(x, y, new Color(greyscale, greyscale, greyscale));
        canvas.screenData[x][y] = greyscale;
    }

    public void render(Position position) {
        // double startTime = ((double) System.currentTimeMillis()) / 1000.0;
        
        int y = 0;
        int x;
        for (y = 0; y < Main.height; y++) {
            for (x = 0; x < Main.width; x++) {
                setCheck(new Complex(((((double) (x + position.x) / highestDimension) * 4 * (1 / position.zoom)) - ((Main.width / highestDimension) * 2 * (1 / position.zoom))), ((((double) (y + position.y) / highestDimension) * 4 * (1 / position.zoom)) - ((Main.height / highestDimension) * 2 * (1 / position.zoom)))), x, y);
            }
        }

        // double endTime = ((double) System.currentTimeMillis()) / 1000.0;
        // System.out.println(Double.toString(endTime - startTime));
    }
}
