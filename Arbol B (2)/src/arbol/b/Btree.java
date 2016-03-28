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
public class Btree implements Serializable {

    private static final long serialVersionUID = 666L;
    // private int order;
    // private int height;
    // private Node root;

    /*
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
    
     */
    static int order; 
    Node root;  

    public Btree(int order) {
        this.order = order;
        root = new Node(order, null);
    }

    public void insert(Btree tree, int k) {
        Node r = tree.root;
        if (r.count == 2 * order - 1)
        {
            Node temp = new Node(order, null);
            tree.root = temp; 
            temp.leaf = false;
            temp.count = 0;	
            temp.child[0] = r;
            split(temp, 0, r);
            nonfullInsert(temp, k); 
        } else {
            nonfullInsert(r, k);
        }
    }

    public void split(Node nodo1, int i, Node nodo2) {
        Node newNodo = new Node(order, null);
        
        newNodo.leaf = nodo2.leaf;
        newNodo.count = order - 1;
        for (int j = 0; j < order - 1; j++) {
            newNodo.key[j] = nodo2.key[j + order]; 
        }
        if (!nodo2.leaf)
        {
            for (int k = 0; k < order; k++) {
                newNodo.child[k] = nodo2.child[k + order];
            }
        }
        nodo2.count = order - 1; 
        for (int j = nodo1.count; j > i; j--)
        {				 
            nodo1.child[j + 1] = nodo1.child[j]; 
        }
        nodo1.child[i + 1] = newNodo; 
        for (int j = nodo1.count; j > i; j--) {
            nodo1.key[j + 1] = nodo1.key[j]; 
        }
        nodo1.key[i] = nodo2.key[order - 1];
        nodo2.key[order - 1] = 0; 
        for (int j = 0; j < order - 1; j++) {
            nodo2.key[j + order] = 0;
        }
        nodo1.count++;  
    }

    public void nonfullInsert(Node temp, int k) {
        int i = temp.count; 
        if (temp.leaf) {
            while (i >= 1 && k < temp.key[i-1])
            {           
                temp.key[i] = temp.key[i - 1];
                i--;
            }
            temp.key[i] = k;
            temp.count++; 
        } else {
            int j = 0;
            while (j < temp.count && k > temp.key[j])
            {			             		
                j++;
            }
            
            if (temp.child[j].count == order * 2 - 1) {
                split(temp, j, temp.child[j]);
                if (k > temp.key[j]) {
                    j++;
                }
            }
            nonfullInsert(temp.child[j], k);
        }
    }

    @Override
    public String toString() {
        return "Btree{" + "\nroot=" + root + '}';
    }
    

}
