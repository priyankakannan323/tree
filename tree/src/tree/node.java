/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;
import java.io.*;
import java.util.*;
/**
 *
 * @author Admin
 */
public class node {
    String data = null;
    node parent = null;
    boolean select = false;
    List<node> children = new ArrayList<node>();
    node(String data){
        this.data=data;
    }
    public node addChild(node child){
       child.setParent(this);
       this.children.add(child);
       return child;
    }
    public void addChildren(List<node> children){
        children.forEach(child -> child.setParent(this));
        this.children.addAll(children);
    }
    public void setParent(node parent){
        this.parent =  parent;
    }
    public String getData(){
        return this.data;
    }
    public List<node> getChildren(){
        return this.children;
    }
    public node getParent(){
        return this.parent;
    }
    public void deletion(node root){
        if(this.children.isEmpty()){
            this.parent.getChildren().remove(this);
        }
        else{
            this.parent.getChildren().addAll(this.getChildren());
            for(node each:this.getChildren()){
                each.setParent(this.parent);
            }
            this.parent.getChildren().remove(this);
        }
    }
}
