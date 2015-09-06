import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bersik on 16.03.2015.
 */
public class Figure {

    public static ArrayList<CPoint> lineDDA(int x1, int y1, int x2, int y2) {
        ArrayList<CPoint> list = new ArrayList<CPoint>();
        double dx, dy, steps, x, y, k;
        double xc, yc;

        dx = x2 - x1;
        dy = y2 - y1;

        if (Math.abs(dx) > Math.abs(dy))
            steps = Math.abs(dx);
        else
            steps = Math.abs(dy);
        steps++;
        xc = dx / steps;
        yc = dy / steps;
        x = x1;
        y = y1;
        list.add(new CPoint(new Point((int) Math.round(x), (int) Math.round(y))));

        for (k = 1; k <= steps; k++) {
            x = x + xc;
            y = y + yc;
            list.add(new CPoint(new Point((int) Math.round(x), (int) Math.round(y))));
        }
        return list;
    }

    public static ArrayList<CPoint> lineDDAnoSymmetric(int x1, int y1, int x2, int y2) {
        ArrayList<CPoint> list = new ArrayList<CPoint>();
        double dx, dy, steps, x, y, k;
        double xc, yc;

        dx = x2 - x1;
        dy = y2 - y1;

        if (Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx);
            xc = 1 * Math.signum(dx);
            yc = dy / steps;
        } else {
            steps = Math.abs(dy);
            yc = 1 * Math.signum(dy);
            xc = dx / steps;
        }

        x = x1;
        y = y1;
        list.add(new CPoint(new Point((int) Math.round(x), (int) Math.round(y))));

        for (k = 1; k <= steps; k++) {
            x = x + xc;
            y = y + yc;
            list.add(new CPoint(new Point((int) Math.round(x), (int) Math.round(y))));
        }
        return list;
    }

    public static ArrayList<CPoint> lineBresenham(int x1, int y1, int x2, int y2) {
        ArrayList<CPoint> list = new ArrayList<CPoint>();
        int d = 0;

        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);

        int dy2 = (dy << 1);
        int dx2 = (dx << 1);

        int ix = x1 < x2 ? 1 : -1;
        int iy = y1 < y2 ? 1 : -1;

        if (dy <= dx) {
            for (; ; ) {
                list.add(new CPoint(new Point(x1, y1)));
                if (x1 == x2)
                    break;
                x1 += ix;
                d += dy2;
                if (d > dx) {
                    y1 += iy;
                    d -= dx2;
                }
            }
        } else {
            for (; ; ) {
                list.add(new CPoint(new Point(x1, y1)));
                if (y1 == y2)
                    break;
                y1 += iy;
                d += dx2;
                if (d > dy) {
                    x1 += ix;
                    d -= dy2;
                }
            }
        }
        return list;
    }

    private static double fpart(double x) {
        if (x < 0)
            return 1 - (x - Math.floor(x));
        return x - Math.floor(x);
    }

    private static double rfpart(double x) {
        return 1 - fpart(x);
    }

    public static ArrayList<CPoint> lineBy(int x1, int y1, int x2, int y2) {
        ArrayList<CPoint> list = new ArrayList<CPoint>();
        boolean steep = Math.abs(y2 - y1) > Math.abs(x2 - x1);
        double dx, dy, gradient, xend, yend, xgap, xpxl1, xpxl2, intery;
        int ypxl1, ypxl2;

        if (steep == true) {
            int tmp = x1;
            x1 = y1;
            y1 = tmp;
            tmp = x2;
            x2 = y2;
            y2 = tmp;
        }
        if (x1 > x2) {
            int tmp = x2;
            x2 = x1;
            x1 = tmp;
            tmp = y2;
            y2 = y1;
            y1 = tmp;
        }
        dx = x2 - x1;
        dy = y2 - y1;
        gradient = dy / dx;

        xend = x1;
        yend = y1;
        xgap = rfpart(x1);
        xpxl1 = xend;
        ypxl1 = (int) yend;

        if (steep) {
            list.add(new CPoint(new Point(ypxl1, (int) xpxl1), getColorBrightness(rfpart(yend) * xgap)));
            list.add(new CPoint(new Point(ypxl1 + 1, (int) xpxl1), getColorBrightness(fpart(yend) * xgap)));
        } else {
            list.add(new CPoint(new Point((int) xpxl1, ypxl1), getColorBrightness(rfpart(yend) * xgap)));
            list.add(new CPoint(new Point((int) xpxl1, ypxl1 + 1), getColorBrightness(fpart(yend) * xgap)));
        }
        intery = yend + gradient;

        xend = x2;
        yend = y2;
        xgap = fpart(x2+0.5);
        xpxl2 = xend;
        ypxl2 = (int) yend;

        if (steep) {
            list.add(new CPoint(new Point(ypxl2, (int) xpxl2), getColorBrightness(rfpart(yend) * xgap)));
            list.add(new CPoint(new Point(ypxl2 + 1, (int) xpxl2), getColorBrightness(fpart(yend) * xgap)));
        } else {
            list.add(new CPoint(new Point((int) xpxl2, ypxl2), getColorBrightness(rfpart(yend) * xgap)));
            list.add(new CPoint(new Point((int) xpxl2, ypxl2 + 1), getColorBrightness(fpart(yend) * xgap)));
        }

        if (steep)
            for (double x = xpxl1 + 1; x < xpxl2; x = x + 1) {
                list.add(new CPoint(new Point((int) intery, (int) x), getColorBrightness(rfpart(intery))));
                list.add(new CPoint(new Point((int) intery + 1, (int) x), getColorBrightness(fpart(intery))));
                intery += gradient;
            }
        else
            for (double x = xpxl1 + 1; x < xpxl2; x = x + 1) {
                list.add(new CPoint(new Point((int) x, (int) intery), getColorBrightness(rfpart(intery))));
                list.add(new CPoint(new Point((int) x, (int) intery + 1), getColorBrightness(fpart(intery))));
                intery += gradient;
            }
        return list;
    }

    private static Color getColorBrightness(double brightness) {
        Color clr = Color.black;
        float[] hsv = Color.RGBtoHSB(clr.getRed(), clr.getGreen(), clr.getBlue(), null);
        hsv[2] = (1 - (float) brightness);
        return Color.getHSBColor(hsv[0], hsv[1], hsv[2]);
    }

    public static ArrayList<CPoint> circle(int x, int y, int r) {
        ArrayList<CPoint> list = new ArrayList<CPoint>();
        int x1, y1, yk = 0;
        int sigma, delta, f;

        x1 = 0;
        y1 = r;
        delta = 2 * (1 - r);
        do {
            list.add(new CPoint(new Point(x + x1, y + y1)));
            list.add(new CPoint(new Point(x - x1, y + y1)));
            list.add(new CPoint(new Point(x + x1, y - y1)));
            list.add(new CPoint(new Point(x - x1, y - y1)));
            f = 0;
            if (y1 < yk)
                break;
            if (delta < 0) {
                sigma = 2 * (delta + y1) - 1;
                if (sigma <= 0) {
                    x1++;
                    delta += 2 * x1 + 1;
                    f = 1;
                }
            } else if (delta > 0) {
                sigma = 2 * (delta - x1) - 1;
                if (sigma > 0) {
                    y1--;
                    delta += 1 - 2 * y1;
                    f = 1;
                }
            }
            if (f == 0) {
                x1++;
                y1--;
                delta += 2 * (x1 - y1 - 1);
            }
        }
        while (true);
        return list;
    }

    public static ArrayList<CPoint> ellipse(int xc, int yc, int width, int height) {
        ArrayList<CPoint> list = new ArrayList<CPoint>();
        int a2 = width * width;
        int b2 = height * height;
        int x, y, sigma;

        for (x = 0, y = height, sigma = 2 * b2 + a2 * (1 - 2 * height); b2 * x <= a2 * y; x++) {
            list.add(new CPoint(new Point(xc + x, yc + y)));
            list.add(new CPoint(new Point(xc - x, yc + y)));
            list.add(new CPoint(new Point(xc + x, yc - y)));
            list.add(new CPoint(new Point(xc - x, yc - y)));
            if (sigma >= 0) {
                sigma += 4 * a2 * (1 - y);
                y--;
            }
            sigma += b2 * ((4 * x) + 6);
        }

        for (x = width, y = 0, sigma = 2 * a2 + b2 * (1 - 2 * width); a2 * y <= b2 * x; y++) {
            list.add(new CPoint(new Point(xc + x, yc + y)));
            list.add(new CPoint(new Point(xc - x, yc + y)));
            list.add(new CPoint(new Point(xc + x, yc - y)));
            list.add(new CPoint(new Point(xc - x, yc - y)));
            if (sigma >= 0) {
                sigma += 4 * b2 * (1 - x);
                x--;
            }
            sigma += a2 * ((4 * y) + 6);
        }
        return list;
    }
}
