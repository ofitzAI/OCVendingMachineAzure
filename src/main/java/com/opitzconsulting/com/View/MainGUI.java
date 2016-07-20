package com.opitzconsulting.com.View;


import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


/**
 * Created by OFI on 24.06.2016.
 */
public class MainGUI extends JFrame {

    public static final String BUYPHOTO = "BUYPHOTO";
    public static final String BUYPLUSHIE = "BUYPLUSHIE";
    public static final String BUYTOY = "BUYTOY";
    public static final String BUYMILK = "BUYMILK";
    public static final String RESETBUTTON = "RESETBUTTON";

    private JButton btn_pr0 = new JButton();
    private JButton btn_pr1= new JButton();
    private JButton btn_pr2 = new JButton();
    private JButton btn_pr3 = new JButton();
    private JButton btn_reset =new JButton();

    private JTextField txt_pr0=new JTextField();
    private JTextField txt_pr1= new JTextField();
    private JTextField txt_pr2=new JTextField();
    private JTextField txt_pr3=new JTextField();
    private JTextField txt_reset=new JTextField();

    private JLabel label0 = new JLabel();
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel label3 = new JLabel();
    private JLabel label4=new JLabel();

    private ImageIcon icon0 = new ImageIcon();
    private ImageIcon icon1 = new ImageIcon();
    private ImageIcon icon2 = new ImageIcon();
    private ImageIcon icon3 = new ImageIcon();
    private ImageIcon background=new ImageIcon();


    public MainGUI(){

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);

        btn_pr0.setActionCommand(MainGUI.BUYPHOTO);
        btn_pr1.setActionCommand(MainGUI.BUYPLUSHIE);
        btn_pr2.setActionCommand(MainGUI.BUYTOY);
        btn_pr3.setActionCommand(MainGUI.BUYMILK);
        btn_reset.setActionCommand(MainGUI.RESETBUTTON);
        btn_pr0.setBorder(null);
        btn_pr1.setBorder(null);
        btn_pr2.setBorder(null);
        btn_pr3.setBorder(null);

        txt_pr0.setEditable(false);
        txt_pr1.setEditable(false);
        txt_pr2.setEditable(false);
        txt_pr3.setEditable(false);
        txt_reset.setEditable(false);

        this.add(txt_pr0);
        this.add(txt_pr1);
        this.add(txt_pr2);
        this.add(txt_pr3);
        this.add(txt_reset);

        this.add(btn_pr0);
        this.add(btn_pr1);
        this.add(btn_pr2);
        this.add(btn_pr3);
        this.add(btn_reset);


        this.pack();
    }


    public JButton getBtn_pr0(){
        return btn_pr0;
    }
    public JButton getBtn_pr1(){
        return btn_pr1;
    }
    public JButton getBtn_pr2(){
        return btn_pr2;
    }
    public JButton getBtn_pr3(){
        return btn_pr3;
    }
    public JButton getBtn_reset(){
        return btn_reset;
    }

    public JTextField getTxt_pr0(){
        return txt_pr0;
    }
    public JTextField getTxt_pr1(){
        return txt_pr1;
    }
    public JTextField getTxt_pr2(){
        return txt_pr2;
    }
    public JTextField getTxt_pr3(){
        return txt_pr3;
    }
    public JTextField getTxt_reset(){
        return txt_reset;
    }


    public JLabel getLabel0(){return label0;}
    public JLabel getLabel1(){return label1;}
    public JLabel getLabel2(){return label2;}
    public JLabel getLabel3(){return label3;}
    public JLabel getLabel4(){return label4;}

    public ImageIcon getIcon0(){return icon0;}
    public ImageIcon getIcon1(){return icon1;}
    public ImageIcon getIcon2(){return icon2;}
    public ImageIcon getIcon3(){return icon3;}
    public ImageIcon getMyBackground(){return background;}
}
