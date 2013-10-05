import javax.swing.*;

import java.awt.*;
import java.awt.Event.*;
import java.util.Vector;
class MyList{
 private JFrame frame = new JFrame("hello world");
 private Container cont = frame.getContentPane();
 private Container cont2 = new Container();
 private JList list1 = null;
 private JList list2 = null;
 private JList list3 = null;
 private JTabbedPane tabPane = new JTabbedPane();
 public MyList(){
  this.frame.setLayout(new GridLayout(1,3));
  String nation[] ={"china","usa","japan","corea","dlsj","ldskj","ldsk","lsfjls","lsdfk"};
  Vector<String> v = new Vector<String>();//可实现自动增长对象数组
  this.frame.setContentPane(tabPane);
  tabPane.addTab("tab1", cont);
  tabPane.addTab("tab2", cont2);
  list3 = new JList(v);
  cont2.add(list3);
  v.add("hi");
  v.add("you");
  v.add("who");
  v.add("are");
  
  this.list1 = new JList(nation);
  this.list2 = new JList(v);
  this.list3.setBorder(BorderFactory.createTitledBorder("this is title 3"));
  this.list1.setBorder(BorderFactory.createTitledBorder("which country do you want to"));
  this.list2.setBorder(BorderFactory.createTitledBorder("do you love me"));
  
  this.cont.add(list1);
  this.cont.add(list2);
  
  this.cont.add(new JScrollPane(this.list1)); //对list1添加滚动条
  this.cont.add(new JScrollPane(this.list2)); //对list1添加滚动条
  //this.cont.add(new JScrollPane(this.list3));
  
  this.frame.setSize(400,200);
  this.frame.setVisible(true);
  this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 }
}