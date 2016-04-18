/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktltdh;

import java.awt.Color;

/**
 *
 * @author vanso_000
 */
public class ClippingLine {
    int dx,dy;
    int[] p = new int[4];
    int[] q = new int[4];
    float t[] = new float[4];
    
    public ClippingLine(){}
     
     public Line Clipping(Line l, Color c, int x_min, int y_min, int x_max,  int y_max){
        float t_min = 1;
        float t_max = 0;
        dx = l.getXb() - l.getXa();
        dy = l.getYb() - l.getYa();
        p[0] = -dx; q[0] = l.getXa() - x_min;
        p[1] = dx; q[1] = x_max - l.getXa();
        p[2] = -dy; q[2] = l.getYa() - y_min;
        p[3] = dy; q[3] = y_max - l.getYa();
        
        for(int i=0;i<4;i++){
            t[i] = (float)q[i]/p[i];
            if(p[i]==0){
                if(q[i]<0) System.out.println("outside & STOP");
                else {
                    System.out.println("inside & STOP");
                    return l;
                }
            }
            else if(p[i]<0)
                t_max = Math.max(t_max, t[i]);
            else
                t_min = Math.min(t_min, t[i]);
        }
        
        int x1=0,y1=0,x2=0,y2=0;
        if(t_max > t_min) 
            System.out.println("t_max > t_min outside");
        else{
            x1 = (int) Math.round(l.getXa() + dx*t_min);
            y1 = (int) Math.round(l.getYa() + dy*t_min);
            x2 = (int) Math.round(l.getXa() + dx*t_max);
            y2 = (int) Math.round(l.getYa()+ dy*t_max);
        }    
        Line seg = new Line(x1, y1, x2, y2, c);
        return seg;
     }
}
