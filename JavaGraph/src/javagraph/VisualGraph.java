/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagraph;
import javagraph.Edgev;

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.*;
import javax.swing.event.*;
/**
 *
 * @author javed
 */
public class VisualGraph extends javax.swing.JFrame {

   int x,y;  
   Point selected=null;
   Point moved = null;
   Point dijs = null;
   Point dijd = null;
   Point starting = null;
   Point ending = null;
   static int radius = 10;
   double costi = 10.0;
   Edgev ed = null ;
   static ArrayList<Point> points;
   static ArrayList<Edgev> edgev;
   static ArrayList<Point> dikp;
   static ArrayList<Edgev> tempedge;
   static ArrayList<Edgev> distedge;
   static ArrayList<Point> temppoint;
   HashMap<Point,Double> dist;    
   HashMap<Point,Integer> visited;
   HashMap<Point,Point> parent; 
   int ch1 = 0, ch2 = 0, ch3 = 0,ch4 = 0, ch5 = 0,countyy = 0;
   
    /**
     * Creates new form VisualGraph
     */
    public VisualGraph() {
        initComponents();
        points = new ArrayList<Point>();
        edgev = new ArrayList<Edgev>();
        dikp = new ArrayList<Point>();
        temppoint = new ArrayList<Point>(); 
        tempedge = new ArrayList<Edgev>();
        distedge = new ArrayList<Edgev>();
        dist = new HashMap<Point, Double>(points.size());
        visited = new HashMap<Point,Integer>(points.size());
        parent = new HashMap<Point, Point>(points.size());
        if(countyy == 0)
        { 
            JOptionPane.showMessageDialog(null , "By Default Weight of All Edges are 10");
            countyy++;
        }
    }

     public Point MiniVertex()
    {
        double min = Double.MAX_VALUE;
        Point min_vertex = null ;
        
        for(int j = 0; j < points.size(); j++)
        {
          if(visited.get(points.get(j)) == 0 && dist.get(points.get(j)) <= min)
          {
             min = dist.get(points.get(j));
             min_vertex = points.get(j);
          }
        }
       
       return min_vertex;
    }   
    
//For Animation
//    public void run()
//    {
//        System.out.println("Hello");
//       // if(dijs!=null && dijd!=null)
//        {         // System.out.println(starting);
//
//         // System.out.println(starting);
//          //while(starting != ending)
//          {
//              
//              for(int i = 0; i < distedge.size(); i++)
//              {
//                  System.out.println(distedge.get(i).getA());
//               if(distedge.get(i).getA()!=null)   
//               {
//                 
//                System.out.println(distedge.get(i).getA());
//                
//                Point temi = distedge.get(i).getA();
//                Point temp = distedge.get(i).getB();
//               
//               
//                for(int j = 10; j>=0; j--)
//                {
//                    Point aa = new Point(((temi.x)*j + (temp.x))/(j+1),((temi.y)*j + temp.y)/(j+1)) ;
//                    temppoint.add(aa);
//                    
//                    repaint();
//                    try
//                    {
//                        //Thread.sleep(200);
//                    }
//                    catch(Exception e)
//                    {
//                        System.out.println(e);
//                    }
//                    
//                    Iterator toll = temppoint.iterator();
//                    while(toll.hasNext())
//                    {
//                        Point ru = (Point)toll.next();
//                        toll.remove();
//                    }
//                } 
//                //starting = distedge.get(i).getB();
//                //break;
//            }
//          }
//          }
//        } //System.out.println("Hello");
////         if(distedge.size()>0)
////               { 
////                 Iterator tu = distedge.iterator();
////               while(tu.hasNext())
////               {
////                   Edgev toi = (Edgev)tu.next();
////                   tu.remove();
////               }
////               }
////                if(dikp.size() != 0)
////                {
////                    Iterator pr = dikp.iterator();
////                    while(pr.hasNext())
////                   {
////                     Point ty = (Point)pr.next();
////                     pr.remove();
////                   }
////                   repaint();
////                }
////                dijs = null;
////                dijd = null;
//                
//    }
     
    public void SolveDijikstra(Point a, Point b)
    {
          
        for(int i = 0; i < points.size(); i++)
        {
            dist.put(points.get(i),Double.MAX_VALUE);
            visited.put(points.get(i), 0);
            parent.put(points.get(i),null);
        }
        
        dist.put(a,0.0);
        
        for(int i = 0; i < points.size()-1; i++)
        {
            Point temp = MiniVertex();
            System.out.println(temp);
            visited.put(temp,1);
            
            for(int j = 0; j < edgev.size(); j++)
            {
               if(edgev.get(j).getA().equals(temp))
               {
                   Point temp1 = edgev.get(j).getB();
                   double apart = edgev.get(j).getCost();
                   if((visited.get(temp1) == 0) && (dist.get(temp)!= Integer.MAX_VALUE) && (dist.get(temp1) > dist.get(temp) + apart) )
                   {
                       dist.put(temp1, dist.get(temp) + apart);
                       parent.put(temp1, temp);
                   }
               }
               if(edgev.get(j).getB().equals(temp))
               {
                   Point temp1 = edgev.get(j).getA();
                   double apart = edgev.get(j).getCost();
                   if((visited.get(temp1) == 0) && (dist.get(temp)!= Integer.MAX_VALUE) && (dist.get(temp1) > dist.get(temp) + apart) )
                   {
                       dist.put(temp1, dist.get(temp) + apart);
                       parent.put(temp1, temp);
                   }
               }
            }
        }
        dikp.add(b);
        String word = "";
        int count1 = 0;
        int meas = 0;
        if(parent.get(b) == null)
        {
            JOptionPane.showMessageDialog(null ,"No path is available");
        }
        else
        {
            Point dest = b;
            while(parent.get(b) != a)
           {
              word += b.x + " -< ";
              Point tempo = parent.get(b);
              for(Edgev tru: edgev)
              {
                  if((tru.getB().equals(b) && tru.getA().equals(tempo))  )
                  {
                      distedge.add(tru);
                  }   
              }
              b = parent.get(b);
              if(b == null)
              { 
                  count1++;
                  Iterator pot = distedge.iterator();
                  while(pot.hasNext())
                  {
                    Edgev tru = (Edgev)pot.next();
                    pot.remove(); 
                  }
                  break;
              }
              dikp.add(b);
              meas++;System.out.println(dijd);
           }
           word += b.x + " -<" + a.x;
           if((count1 == 0 || meas == 0) )
           {
               dikp.add(b);
               dikp.add(a);
               for(Edgev tru: edgev)
              {
                  if((tru.getB().equals(b) && tru.getA().equals(a)) )
                  {
                      distedge.add(tru);
                  }   
              }
      
                 System.out.println(word);
//                 System.out.println(dijd);
  //              Thread obj = new Thread(new VisualGraph());
  //              System.out.println(dijd);
      //          obj.start();
//                System.out.println(dijd);
               repaint();
           }
           else if(count1 > 0)
           {
             JOptionPane.showMessageDialog(null ,"No path is available");
             Iterator it = dikp.iterator();
             while(it.hasNext())
             {
                 Point pu = (Point)it.next();
                 it.remove();
             }
             if(distedge.size()>0)
             { 
                 Iterator tu = distedge.iterator();
               while(tu.hasNext())
               {
                   Edgev toi = (Edgev)tu.next();
                   tu.remove();
               }
             }
             dijs = null;
             dijd = null;
            repaint();
           }
        }
        
    }
    
    public static boolean isWithinBounds(MouseEvent e, Point p) {
        int a = e.getX();
        int b = e.getY();

        int boundX = (int) p.getX();
        int boundY = (int) p.getY();

        return (a <= boundX + radius && a >= boundX - radius) && (b <= boundY + radius && b >= boundY - radius);
    }
   
    public static boolean isWithinBounds1(Point s, Point p) {
        int a = (int)s.getX();
        int b = (int)s.getY();

        int boundX = (int) p.getX();
        int boundY = (int) p.getY();

        return (a <= boundX + radius && a >= boundX - radius) && (b <= boundY + radius && b >= boundY - radius);
    } 
     
    public static boolean isOverlapping(MouseEvent e, Point p) {
        int x = e.getX();
        int y = e.getY();

        int boundX = (int) p.getX();
        int boundY = (int) p.getY();

        return (x <= boundX + 2.5*radius && x >= boundX - 2.5*radius) && (y <= boundY + 2.5*radius && y >= boundY - 2.5*radius);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        addv = new javax.swing.JRadioButton();
        delv = new javax.swing.JRadioButton();
        mov = new javax.swing.JRadioButton();
        adde = new javax.swing.JRadioButton();
        dele = new javax.swing.JRadioButton();
        dij = new javax.swing.JRadioButton();
        addw = new javax.swing.JRadioButton();
        jPanel1 = new AddVertexV();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(88, 200, 87));

        addv.setBackground(new java.awt.Color(155, 146, 137));
        buttonGroup1.add(addv);
        addv.setText("Add Vertex");
        addv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addvActionPerformed(evt);
            }
        });

        buttonGroup1.add(delv);
        delv.setText("Delete Vertex");
        delv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delvActionPerformed(evt);
            }
        });

        buttonGroup1.add(mov);
        mov.setText("Move vertex");
        mov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movActionPerformed(evt);
            }
        });

        buttonGroup1.add(adde);
        adde.setText("Add Edge");
        adde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addeActionPerformed(evt);
            }
        });

        buttonGroup1.add(dele);
        dele.setText("Delete Edge");
        dele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleActionPerformed(evt);
            }
        });

        buttonGroup1.add(dij);
        dij.setText("Dijakstra");
        dij.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dijActionPerformed(evt);
            }
        });

        buttonGroup1.add(addw);
        addw.setText("Add Weight");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addw)
                    .addComponent(dij)
                    .addComponent(dele)
                    .addComponent(mov)
                    .addComponent(delv)
                    .addComponent(addv, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adde))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(addv, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(delv)
                .addGap(18, 18, 18)
                .addComponent(mov)
                .addGap(33, 33, 33)
                .addComponent(adde)
                .addGap(29, 29, 29)
                .addComponent(dele)
                .addGap(32, 32, 32)
                .addComponent(addw)
                .addGap(29, 29, 29)
                .addComponent(dij)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addvActionPerformed
                   
    }//GEN-LAST:event_addvActionPerformed

    public static int sqr(int x) {
        return x * x;
    }
    
    public static int dist2(Point v, Point w) {
        return sqr(v.x - w.x) + sqr(v.y - w.y);
    }
    
    public static int distToSegmentSquared(Point p, Point v, Point w) 
    {
        double l2 = dist2(v, w);
        if (l2 == 0) return dist2(p, v);
        double t = ((p.x - v.x) * (w.x - v.x) + (p.y - v.y) * (w.y - v.y)) / l2;
        if (t < 0) return dist2(p, v);
        if (t > 1) return dist2(p, w);
        return dist2(p, new Point(
                (int)(v.x + t * (w.x - v.x)),
                (int)(v.y + t * (w.y - v.y))
        ));
    }
    
    public static int distToSegment(Point p, Point v, Point w) {
        return (int) Math.sqrt(distToSegmentSquared(p, v, w));
    }

     public static boolean isOnEdge(MouseEvent e, Edgev edgev)
    {
        int dist = distToSegment( e.getPoint(), edgev.getA(), edgev.getB() );
        if (dist<4)
            return true;
        return false;
    }
    
    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        
        //Add Vertex
        
        if(addv.isSelected())
        {
           
            x = evt.getX();
            y = evt.getY();      
           
            if(ch1 == 0)
            {
               JOptionPane.showMessageDialog(null, "Click on Pane to Add Node");
               ch1++;
            }
            
            for(Point point : points)
           {
              if(isWithinBounds(evt,point))
             {
              JOptionPane.showMessageDialog(null, "Overlapping Node can't be created");
              return;
             }
            }
            points.add(new Point(x-(radius)/2, y - (radius)/2));
            repaint();
         }
        
         //Delete Vertex
         if(delv.isSelected()) 
         {
             if(ch2 == 0)
             {
               JOptionPane.showMessageDialog(null, "Press Control and Select Vertex");
               ch2++;
             }
             if(evt.isControlDown())
              {
               
               Iterator temp = points.iterator();
               while(temp.hasNext())
              {
                Point point = (Point)temp.next();
                if(isOverlapping(evt, point))
               {
                  System.out.println(point.x + " Hello");
                Iterator temp1 = edgev.iterator();
                while(temp1.hasNext())
               {
                 Edgev ab = (Edgev)temp1.next();
                 if(isOverlapping(evt,ab.getA()) || isOverlapping(evt,ab.getB()))
                 {
                  temp1.remove();
                 }
               }
               temp.remove();
               }
             } 
               repaint();

              }
         }
         //Delete Edge
         if(dele.isSelected())
         {
             x = evt.getX();
             y = evt.getY();
          
             if(ch3 == 0)
             {
                 JOptionPane.showMessageDialog(null , "Use Shift + Mouse Click ");
                ch3++;
             }
             
            if(evt.isShiftDown()) 
            { Iterator temp = edgev.iterator();
             while(temp.hasNext())
             {
                Edgev obj1 = (Edgev)temp.next();
                if(isOnEdge(evt , obj1))
                {
                    temp.remove();
                }
             }
             repaint();
           }
         }
         
         //Add Weight
         if(addw.isSelected())
         {
             x = evt.getX();
             y = evt.getY();

             
              if(ch4 == 0) 
              {
                  JOptionPane.showMessageDialog(null , "Use Control + Mouse Click ");
                  ch4++;
              }
             
            if(evt.isControlDown()) 
            { 
                Iterator temp = edgev.iterator();
                while(temp.hasNext())
                {
                   Edgev obj1 = (Edgev)temp.next();
                   if(isOnEdge(evt , obj1))
                   {
                      new EnterWeight(edgev,obj1).setVisible(true);
                   }
                }
                repaint();
           }
         }
         
         //Dijakstra
         if(dij.isSelected())
         {
            if(ch5 == 0)
            {
                JOptionPane.showMessageDialog(null , "Select 2 Node by Clicking On it ");
                ch5++; 
            }
            
            if(dijs == null) 
            {
                for(Point ptr : points)
                {
                    if(isWithinBounds(evt,ptr))
                        dijs = ptr;
                }
            }
            else if(dijs != null)
            {
                for(Point ptr : points)
                {
                    if(isWithinBounds(evt,ptr))
                        dijd = ptr;
                }
                if(distedge.size()>0)
               { 
                 Iterator tu = distedge.iterator();
               while(tu.hasNext())
               {
                   Edgev toi = (Edgev)tu.next();
                   tu.remove();
               }
               }
                if(dikp.size() != 0)
                {
                    Iterator pr = dikp.iterator();
                    while(pr.hasNext())
                   {
                     Point ty = (Point)pr.next();
                     pr.remove();
                   }
                   repaint();
                }
//                starting = dijs;
//                ending = dijd;
                SolveDijikstra(dijs , dijd);
               // System.out.println(starting);
              dijs = null;
               dijd = null;
            }
         }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
     //Add vertex is selected
        if(adde.isSelected())
      {   
         x = evt.getX();
         y = evt.getY();
         
         for (Point point : points)
        {
           if(isWithinBounds(evt,point))
           { 
             selected = point;
           }
        }
      }
        
      //Move Vertex is Selected   
      if(mov.isSelected())
      {
         x = evt.getX();
         y = evt.getY();
         
         for (Point point : points)
        {
           if(isWithinBounds(evt,point))
           { 
             moved = point;
           }
        }
      }
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseReleased
       
           x = evt.getX();
           y = evt.getY();
          
         //Move Vertex
         if(mov.isSelected())
         {
             Point tr = evt.getPoint();  //Current Mouse Pointer
             Point tempo = null;
             if(moved != null )
             {
                 Iterator temp = points.iterator();
                 try
                 {
                     while(temp.hasNext())
                   {
                     Point ptr = (Point)temp.next();
     
                     if(isWithinBounds1(moved ,ptr ))
                     {
                           tempo = moved;
                         //System.out.println(ptr.x + " " + ptr.y);  
                           if(edgev.size()!=0)
                           {
                              
                             Iterator temp1 = edgev.iterator();
                             while(temp1.hasNext())
                            {
                             Edgev obj1 = (Edgev)temp1.next();
                             
                             if(obj1.getA().equals(ptr) && !obj1.getB().equals(ptr)) 
                             {
                                 System.out.println(tr.x + " " + tr.y);
                                 System.out.println(obj1.getA().x + " " + obj1.getB().y);
                                 Edgev obj2 = new Edgev( tr,obj1.getB(),obj1.getCost());
                                 Edgev obj3 = new Edgev( obj1.getB(),tr,obj1.getCost());
                                 
                                 tempedge.add(obj2);
                                 tempedge.add(obj3);
                                 temp1.remove();
                             }
                        
                            
                              if(obj1.getB().equals(ptr) && !obj1.getA().equals(ptr)) 
                             {
                                 System.out.println(tr.x + " " + tr.y);
                                 System.out.println(obj1.getA().x + " " + obj1.getB().y);
                                 Edgev obj4 = new Edgev( tr,obj1.getA(),obj1.getCost());
                                 Edgev obj6 = new Edgev( obj1.getA(),tr,obj1.getCost());
                                 
                                 tempedge.add(obj4);
                                 tempedge.add(obj6);
                                 temp1.remove();
                             }
                         }
                        }
                         if(tempedge.size() != 0)
                         {
                             Iterator ty = tempedge.iterator();
                             while(ty.hasNext())
                             {
                               Edgev obj5 = (Edgev)ty.next();
                               edgev.add(obj5);   
                             }
                             Iterator tm = tempedge.iterator();
                             while(tm.hasNext())
                             {
                                 Edgev obj7 = (Edgev)tm.next();
                                 tm.remove();
                             }
                         }
                       
                         temp.remove();      
                  }
                    
                 }
                 }
                 catch(Exception e)
                         {
                             System.out.println(e);
                        }
                if(tempo != null)
                { 
                    points.add(new Point(tr.x, tr.y ));
                   repaint();
                   tempo=null;
                }
                 moved=null;
             }
         }
        
         //Add Edge
         if(adde.isSelected()) 
         {
            for (Point point : points)
           {
           if(isWithinBounds(evt,point))
           { 
             if(selected != null && selected!=point)
             {
                  Edgev obj1 = new Edgev(selected,point,costi);
                  Edgev obj2 = new Edgev(point,selected,costi);
                  edgev.add(obj1);
                  edgev.add(obj2);
                  repaint();
                 // System.out.println(selected.x + " " + selected.y + " " + point.x + " " + point.y );
                  selected=null;
             }
             if(selected != null && selected==point)
             {
                 JOptionPane.showMessageDialog(null, "Overlapping Edge can't be created");
             }
            }
           }
         }
    }//GEN-LAST:event_jPanel1MouseReleased

    private void delvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delvActionPerformed
       
    }//GEN-LAST:event_delvActionPerformed

    private void movActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_movActionPerformed

    private void addeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addeActionPerformed

    private void deleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleActionPerformed

    private void dijActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dijActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dijActionPerformed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged

   
    }//GEN-LAST:event_jPanel1MouseDragged
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VisualGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisualGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisualGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisualGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisualGraph().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton adde;
    private javax.swing.JRadioButton addv;
    private javax.swing.JRadioButton addw;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton dele;
    private javax.swing.JRadioButton delv;
    private javax.swing.JRadioButton dij;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton mov;
    // End of variables declaration//GEN-END:variables
}
