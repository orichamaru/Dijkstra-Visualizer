/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javagraph;
import javagraph.Graph;
/**
 *
 * @author javed
 */
public class Edge  {
    private Graph v1, v2;
    private double weight;

    public Edge(Graph v1, Graph v2, double weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public Graph getV1() {
        return v1;
    }

    public void setV1(Graph v1) {
        this.v1 = v1;
    }

    public Graph getV2() {
        return v2;
    }

    public void setV2(Graph v2) {
        this.v2 = v2;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
     
}
