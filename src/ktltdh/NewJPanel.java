/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktltdh;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author celhp
 */
public class NewJPanel extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private List<Line> Lines, Temp;
    private Drawer drawer;
    private int xa;
    private int ya;
    private int xb;
    private int yb;
    private int xc;
    private int yc;
    private int xd;
    private int yd;

    public NewJPanel() {
        initComponents();
        Lines = new ArrayList<>();
        drawer = new Drawer();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void veHinhThoi(Color c) {
        Lines.add(new Line(xa, ya, xb, yb, c));
        Lines.add(new Line(xb, yb, xc, yc, c));
        Lines.add(new Line(xc, yc, xd, yd, c));
        Lines.add(new Line(xd, yd, xa, ya, c));
        try {
            Temp.clear();
        } catch (Exception e) {
        }
    }

    public void catHinhThoi(int x_min, int y_min, int x_max, int y_max) {
        Temp = new ArrayList<>(Lines);
        try{
            Lines.clear();
            // Windows clipping
            Lines.add(new Line(x_min, y_min, x_max, y_min, Color.red));
            Lines.add(new Line(x_max, y_min, x_max, y_max, Color.red));
            Lines.add(new Line(x_max, y_max, x_min, y_max, Color.red));
            Lines.add(new Line(x_min, y_max, x_min, y_min, Color.red));
            
            ClippingLine cpl = new ClippingLine();
            for(int i=0;i<Temp.size();i++){
                //System.out.println("x1: "+Temp.get(i).getXa()+" y1:"+Temp.get(i).getYa()+"-"+" x2: "+Temp.get(i).getXb()+" y2:"+Temp.get(i).getYb());
               Lines.add(cpl.Clipping(Temp.get(i), Temp.get(i).getC(), x_min, y_min, x_max, y_max)); 
            }
        }catch(Exception e){System.out.println(e);}
    }

    public void TinhTien(int x, int y) {
        xa += x;
        xb += x;
        xc += x;
        xd += x;
        ya += y;
        yb += y;
        yc += y;
        yd += y;
    }

    public void BienDoiTiLe(float x, float y) {

        TinhTien(-330, -330);
        xa *= x;
        xb *= x;
        xc *= x;
        xd *= x;
        ya *= y;
        yb *= y;
        yc *= y;
        yd *= y;
        TinhTien(330, 330);

    }

    public void quay(int goc, int x, int y) {
        TinhTien(-330, -330);
        y = -y;
        int xat, yat, xbt, ybt, xct, yct, xdt, ydt;
        double g = Math.toRadians(-goc);
        double cos = Math.cos(g);
        double sin = Math.sin(g);
        TinhTien(-x, -y);
        xat = (int) (xa * cos - ya * sin);
        yat = (int) (xa * sin + ya * cos);
        xbt = (int) (xb * cos - yb * sin);
        ybt = (int) (xb * sin + yb * cos);
        xct = (int) (xc * cos - yc * sin);
        yct = (int) (xc * sin + yc * cos);
        xdt = (int) (xd * cos - yd * sin);
        ydt = (int) (xd * sin + yd * cos);
        xa = xat;
        ya = yat;
        xb = xbt;
        yb = ybt;
        xc = xct;
        yc = yct;
        xd = xdt;
        yd = ydt;
        TinhTien(x, y);
        TinhTien(330, 330);
    }

    public void bienDang(int x, int y) {
        TinhTien(-330, -330);
        int xat = xa, yat = ya, xbt = xb, ybt = yb, xct = xc, yct = yc, xdt = xd, ydt = yd;
        xa += yat * x;
        ya += xat * y;
        xb += ybt * x;
        yb += xbt * y;
        xc += yct * x;
        yc += xct * y;
        xd += ydt * x;
        yd += xdt * y;
        TinhTien(330, 330);
    }

    public void doiXung(int a, int b, int c) {
        TinhTien(-330, -330);
        switch (c) {
            case 1:
                ya = -ya;
                yb = -yb;
                yc = -yc;
                yd = -yd;
                break;
            case 2:
                xa = -xa;
                xb = -xb;
                xc = -xc;
                xd = -xd;
                break;
            case 3:
                int xat = xa,
                 yat = ya,
                 xbt = xb,
                 ybt = yb,
                 xct = xc,
                 yct = yc,
                 xdt = xd,
                 ydt = yd;
                Lines.add(new Line(1000 + 330, -(1000 * a + b) + 330, -1000 + 330, -((-1000) * a + b) + 330, Color.black));
                b = -b;
                double atan = Math.atan(-a);
                double sin = Math.sin(2 * atan);
                double cos = Math.cos(2 * atan);
                xa = (int) (cos * xat + sin * yat - b * sin);
                ya = (int) (sin * xat - cos * yat + b * (1 + cos));
                xb = (int) (cos * xbt + sin * ybt - b * sin);
                yb = (int) (sin * xbt - cos * ybt + b * (1 + cos));
                xc = (int) (cos * xct + sin * yct - b * sin);
                yc = (int) (sin * xct - cos * yct + b * (1 + cos));
                xd = (int) (cos * xdt + sin * ydt - b * sin);
                yd = (int) (sin * xdt - cos * ydt + b * (1 + cos));
                break;
        }
        TinhTien(330, 330);
    }

    public void TinhToaDoDinh(int x, int y, int width, int height) {
        this.xa = x + width / 2;
        this.ya = y;
        this.xb = x + width;
        this.yb = y + height / 2;
        this.xc = xa;
        this.yc = y + height;
        this.xd = x;
        this.yd = yb;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawer.MidpointLine(330, -100, 330, 700, (Graphics2D) g, Color.black);
        drawer.MidpointLine(-100, 330, 700, 330, (Graphics2D) g, Color.black);
        Lines.stream().forEach((get) -> {
            drawer.MidpointLine(get.getXa(), get.getYa(), get.getXb(), get.getYb(), (Graphics2D) g, get.getC());
        });
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

    public int getXc() {
        return xc;
    }

    public void setXc(int xc) {
        this.xc = xc;
    }

    public int getYc() {
        return yc;
    }

    public void setYc(int yc) {
        this.yc = yc;
    }

    public int getXd() {
        return xd;
    }

    public void setXd(int xd) {
        this.xd = xd;
    }

    public int getYd() {
        return yd;
    }

    public void setYd(int yd) {
        this.yd = yd;
    }

    public JButton getjButton1() {
        return jButton1;
    }

    public void setjButton1(JButton jButton1) {
        this.jButton1 = jButton1;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        jButton1.setText("Xóa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(584, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(460, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Lines.clear();
        repaint();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
