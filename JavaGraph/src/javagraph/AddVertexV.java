/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagraph;
import javagraph.Edgev;
import static javagraph.VisualGraph.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;

/**
 *
 * @author javed
 */
public class AddVertexV extends JPanel {
    
   int x,y; 
   static int radius = 10;
   HashMap<Point, Point> tr;
   
   public AddVertexV()
   {
       tr  = new HashMap<Point,Point>();
   }
    
   public void paintComponent(Graphics g)
   {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.BLACK);
    
    for (Point point : points) {
            g2.fillOval(point.x - radius, point.y - radius,2*radius ,2*radius);
        }
    for(Edgev ab : edgev)
    {
        g2.drawLine(ab.getA().x , ab.getA().y , ab.getB().x , ab.getB().y);
//        if(!tr.containsKey(ab.getA()) && !tr.containsValue(ab.getB()))
//        {
//         
//          String rt = ""+ab.getCost();
//          {
//              g2.drawString(rt,(ab.getA().x + ab.getB().x)/2 ,(ab.getA().y + ab.getA().y)/2);
//          }
//           tr.put(ab.getA() , ab.getB());
//        } 
   }   
    for(Edgev ab : tempedge)
    {
        g2.drawLine(ab.getA().x , ab.getA().y , ab.getB().x , ab.getB().y);
    }
    g2.setColor(Color.GREEN);
    for(Point point : temppoint)
    {
      g2.fillOval(point.x - radius, point.y - radius,2*radius ,2*radius);  
    }
    for(Point point : dikp)
    {
      g2.fillOval(point.x - radius, point.y - radius,2*radius ,2*radius);  
    }
    g2.setColor(Color.BLUE);
    for(Edgev ab : distedge)
    {
        g2.drawLine(ab.getA().x , ab.getA().y , ab.getB().x , ab.getB().y);
    }
    
    
//    for(Edgev t: tempv)
//    {
//        g2.drawLine(t.getA().x , t.getA().y , t.getB().x , t.getB().y);
//    }
   }
   
//   public void mouseClicked(MouseEvent e)
//   {  
//   }
//
//    @Override
//    public void mousePressed(MouseEvent e) 
//    {
//        
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e)
//    {
//       
////         if(count == 0)
////          JOptionPane.showMessageDialog(null, "Vertex is not there");
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent arg0) {
//        //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void mouseExited(MouseEvent arg0) {
//         //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent arg0) {
//         //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent arg0) {
//        //To change body of generated methods, choose Tools | Templates.
//    }
//   
}
