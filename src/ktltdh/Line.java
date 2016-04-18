/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktltdh;

import java.awt.Color;

/**
 *
 * @author celhp
 */
public class Line {

    private int xa;
    private int ya;
    private int xb;
    private int yb;
    private Color c;

    public Line() {
    }

    public Line(int xa, int ya, int xb, int yb, Color c) {
        this.xa = xa;
        this.ya = ya;
        this.xb = xb;
        this.yb = yb;
        this.c = c;
    }

    Line(int i, int i0, int i1, int i2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getXa() {
        return xa;
    }

    public void setXa(int xa) {
        this.xa = xa;
    }

    public int getYa() {
        return ya;
    }

    public void setYa(int ya) {
        this.ya = ya;
    }

    public int getXb() {
        return xb;
    }

    public void setXb(int xb) {
        this.xb = xb;
    }

    public int getYb() {
        return yb;
    }

    public void setYb(int yb) {
        this.yb = yb;
    }

    public Color getC() {
        return c;
    }

    public void setC(Color c) {
        this.c = c;
    }
}
