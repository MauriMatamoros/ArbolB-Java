/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.b;

import java.io.Serializable;

/**
 *
 * @author mauricio
 */
public class Btree implements Serializable{
        private static final long serialVersionUID = 666L;
        private int order;
        private int height;
        private Node root;

    public Btree(int order) {
        this.order = order;
        this.root = new Node();
        this.height = 1;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getHeight() {
        return height;
    }
    
    public void add(int k){
        this.root.addValue(k);
    }
    
    public void delete(int k){
        this.root.deleteValue(k);
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    
    
    @Override
    public String toString() {
        return "Btree{" + "order=" + order + '}' + "\n root: " + this.root.toString();
    }
    
        
        
    
}
