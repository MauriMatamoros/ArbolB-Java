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
    boolean promover = false;

    public Node() {
    }

    public Node(int level) {
        this.level = level;
    }

    public Node(Node father) {
        this.father = father;
        this.level = this.father.getLevel() + 1;
    }

    public void addValue(int k, boolean promover) {
        if (!promover) {
            if (this.children.isEmpty()) {
                keys.add(k);
                Collections.sort(keys);
                if (keys.size() > 2) {
                    promover();
                }
            } else {
                for (int i = 0; i < keys.size(); ++i) {
                    if (k < keys.get(0)) {
                        children.get(0).addValue(k, false);
                        break;
                    }
                    if (k > keys.get(keys.size() - 1)) {

                        children.get(children.size() - 1).addValue(k, false);
                        break;
                    }
                    if (k > keys.get(i) && k < keys.get(i + 1)) {

                        children.get(i + 1).addValue(k, false);
                        break;
                    }
                }
            }

        } else {

            keys.add(k);

            Collections.sort(keys);
            if (keys.size() > 2) {
                promover();
            }
        }
    }

    public void promover() {
        if (this.father == null) {
            this.father = new Node(1);
            this.father.setChild(this);

        }
        int promoted = keys.get(1);
        this.father.addValue(promoted, true);
        keys.remove(2);
        Split(promoted);
    }

    public void deleteValue(int k) {

    }

    public void Split(int k) {
        Node temp = new Node(father);
        for (int i = 2; i > 0; --i) {
            if (keys.get(i - 1) >= k) {
                temp.addValue(keys.get(i-1),false);
                keys.remove(keys.size()-1);
            }
        }
        if (!this.children.isEmpty()) {
            int tempchildsize = children.size() + 1;
            if (tempchildsize % 2 == 0) {
                for (int i = tempchildsize - 1; i > (tempchildsize) / 2; --i) {
                    temp.setChild(children.get(i - 1));
                    children.remove(children.size()-1);
                }
            } else {
                for (int i = tempchildsize - 1; i > (tempchildsize - 1) / 2; --i) {
                    temp.setChild(children.get(i - 1));
                    children.remove(children.size()-1);
                }
            }
        }
        father.setChild(temp);

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
    
    public void orderChildren(){
        if (this.children.get(0).getKeys().get(0) >  this.children.get(1).getKeys().get(0) &&
                this.children.get(0).getKeys().get(0) > this.children.get(2).getKeys().get(0)) {
            
            
        }
    }

    @Override
    public String toString() {
        return "Node{" + "\nlevel" + this.level + "\nvalues=" + this.keys + "\nChildren= " + this.children + '}';
    }

}
