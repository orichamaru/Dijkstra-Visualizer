/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagraph;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author javed
*/




public class JavaGraph {

    public static void main(String[] args)
    {
               
        ArrayList<Graph> vet = new ArrayList<Graph>();
        ArrayList<Edge> edt =new ArrayList<Edge>();
        new Menu(vet,edt).setVisible(true); 
    }
    
}
