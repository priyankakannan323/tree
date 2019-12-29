/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tree;
import java.io.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class tree {
    public static void main(String args[]){
        node root = new node("root");
        node node1 = root.addChild(new node("node1"));
        node node2 = root.addChild(new node("node2"));
        node node11 = node1.addChild(new node("node11"));
        node node12 = node1.addChild(new node("node12"));
        node node13 = node1.addChild(new node("node13"));
        node node21 = node2.addChild(new node("node21"));
        node node22 = node2.addChild(new node("node22"));
        node node23 = node2.addChild(new node("node23"));
        node node111 = node11.addChild(new node("node111"));
        node node121 = node12.addChild(new node("node121"));
        node node131 = node13.addChild(new node("node131"));
        node node211 = node21.addChild(new node("node211"));
        node node221 = node22.addChild(new node("node221"));
        node node231 = node23.addChild(new node("node231"));
        //node21.deletion(root);
        //select(node22);
        select(node231);
        dfs(root," ");
        //loadIntoDB(root);
    }
    public static void dfs(node root, String padding){
       System.out.println(padding+root.data+"->"+root.select);
       root.getChildren().forEach(child -> dfs(child,padding+padding));
    }
    public static void select(node root){
        if(root.select==false){
        root.select = true;
        }
        root.getChildren().forEach(child -> select(child));
    }
    public static void unselect(node root){
        if(root.select = true){
            root.select = false;
        }
        root.getChildren().forEach(child -> unselect(child));
    }
    public static void loadIntoDB(node root){
        List<node> children = root.getChildren();
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tree?zeroDateTimeBehavior=convertToNull", "root", "root");
            for(node child: children){
                if(child.children.isEmpty()){
                    String grandChild = child.data;
                    String chld = child.getParent().data;
                    String parent = child.getParent().getParent().data;
                    PreparedStatement ps = con.prepareStatement("insert into tree values(?,?,?)");
                    ps.setString(1, parent);
                    ps.setString(2, chld);
                    ps.setString(3, grandChild);
                    ps.executeUpdate();
                    //System.out.println("child");
                }
                loadIntoDB(child);
            }
        }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        
    }
   
}
