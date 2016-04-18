/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktltdh;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author celhp
 */
public class Drawer {

    void putpixel(int x, int y, Graphics2D g2d, Color c) {
        g2d.setColor(c);
        g2d.drawLine(x, y, x, y);
    }

    void MidpointLine(int x1, int y1, int x2, int y2, Graphics2D g2d, Color c) {

        if (x1 == x2) {
            putpixel(x1, y1, g2d, c);
            int y = y1;
            for (int i = 0; i <= Math.abs(y1 - y2); i++) {
                y += y1 > y2 ? -1 : 1;
                putpixel(x1, y, g2d, c);
            }
            return;
        }
        if (y1 == y2) {
            putpixel(x1, y1, g2d, c);
            int x = x1;
            for (int i = 0; i <= Math.abs(x1 - x2); i++) {
                x += x1 > x2 ? -1 : 1;
                putpixel(x, y1, g2d, c);
            }
            return;
        }
        int tg;
        if (x1 > x2) {
            tg = x1;
            x1 = x2;
            x2 = tg;
            tg = y1;
            y1 = y2;
            y2 = tg;
        }
        int Dx = x2 - x1;
        int Dy = y2 - y1;
        int x = x1;
        int y = y1;
        putpixel(x1, y1, g2d, c);
        if (Math.abs(Dy) < Dx) {

            float D = Math.abs(Dy) - (Dx >> 1);
            while (x < x2) {
                x++;
                if (D < 0) {
                    D = D + Math.abs(Dy);
                } else {
                    D = D + (Math.abs(Dy) - Dx);
                    y += Dy >= 0 ? 1 : -1;
                }
                putpixel(x, y, g2d, c);
            }
        } else {
            float D = Dx - (Math.abs(Dy) >> 1);
            for (int i = 0; i < Math.abs(Dy); i++) {
                y += Dy >= 0 ? 1 : -1;
                if (D < 0) {
                    D = D + Dx;
                } else {
                    D = D + (Dx - Math.abs(Dy));
                    x++;
                }
                putpixel(x, y, g2d, c);
            }
        }

    }
}
