/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.b;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author mauricio
 */
public class Node {

    private ArrayList<Integer> keys = new ArrayList();
    private Node father = null;
    private ArrayList<Node> children = new ArrayList();
    private int level;

    public Node() {
    }

    public Node(int level) {
        this.level = level;
    }

    public Node(Node father) {
        this.father = father;
        this.level = this.father.getLevel() + 1;
    }

    public void addValue(int k) {
        if (children.isEmpty()) {
            this.keys.add(k);
            Collections.sort(this.keys);
            if (this.keys.size() == 3) {
                this.promover(k);
            }
        } else if (this.keys.size() < 3) {
            if (k < this.keys.get(0)) {
                this.children.get(0).addValue(k);
            } else if (k > this.keys.get(this.keys.size() - 1)) {
                this.children.get(1).addValue(k);
            }
        }
    }

    public void promover(int k) {
        if (this.father == null) {
            this.father = new Node(1);
            this.keys.add(k);
        }
        int middle = this.keys.get(1);
        this.keys.remove(1);
        this.Split(middle);
    }

    public void deleteValue(int k) {

    }

    public void Split(int k) {
        Node leftNode = new Node(this);
        Node RightNode = new Node(this);
        if (!this.keys.isEmpty()) {
            /*leftNode.keys.add(this.keys.get(0));
            this.keys.remove(0);
            this.father.setChild(leftNode);
            RightNode.keys.add(this.keys.get(1));
            this.father.setChild(RightNode);
            this.keys.clear();
            this.keys.add(k);  
             */
            System.out.println("quien es mi padre");
            System.out.println(this.father.toString());
            System.out.println("Donde estoy carajo");
            System.out.println(this.toString());
            System.out.println("Quien putas agrego");
            System.out.println(k);
            leftNode.keys.add(this.keys.get(0));
            this.keys.remove(0);
            this.setChild(leftNode);
            RightNode.keys.add(this.keys.get(1));
            this.setChild(RightNode);
            this.keys.clear();
            this.keys.add(k);
        }
    }

    public void setChild(Node n) {
        this.children.add(n);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public ArrayList<Integer> getKeys() {
        return keys;
    }

    public void setValues(ArrayList<Integer> values) {
        this.keys = values;
    }

    //debugging el available Leaf Node (mejor recursivo)
    /* public Node availableLeaf(Node temp, int k){
        Node retval;
            while(temp.childrenfull || temp.getChildren().size() > 0){
                if (k <= temp.getKeys().get(0)) {
                    temp = temp.getChildren().get(0);
                }else if (k <= temp.getKeys().get(1)) {
                    temp = temp.getChildren().get(1);
                }else if (k > temp.getKeys().get(1)) {
                    temp = temp.getChildren().get(2);
                }
            }
            retval = temp;
        return retval;
    }
     */
    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Node{" + "\nlevel" + this.level + "\nvalues=" + this.keys + "\nChildren= " + this.children + '}';
    }

}
