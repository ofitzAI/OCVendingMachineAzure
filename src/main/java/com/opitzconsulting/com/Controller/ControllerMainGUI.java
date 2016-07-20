package com.opitzconsulting.com.Controller;
import com.opitzconsulting.com.Model.*;
import com.opitzconsulting.com.View.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by OFI on 24.06.2016.
 */
public class ControllerMainGUI implements ActionListener, Observer {

    private MainGUI mainGUI;
    private SendData2Cloud sendData2Cloud;
    private CreateWindow createWindow;
    private ReceiveDataFromCloud receiveDataFromCloud;
    private BufferedImage[] bufferedImages;

    public ControllerMainGUI(MainGUI mainGUI, SendData2Cloud sendData2Cloud, ReceiveDataFromCloud receiveDataFromCloud) throws IOException, InterruptedException {

        this.mainGUI = mainGUI;
        this.createWindow = new CreateWindow();
        this.sendData2Cloud = sendData2Cloud;
        this.receiveDataFromCloud = receiveDataFromCloud;


        receiveDataFromCloud.addObserver(this);
        sendData2Cloud.addObserver(this);


        mainGUI.dispose();
        mainGUI.setUndecorated(true);
        mainGUI.setLayout(null);
        mainGUI.setTitle("OC Vending Machine");
        mainGUI.setSize(createWindow.getScreensize());

        mainGUI.getBtn_pr0().addActionListener(this);
        mainGUI.getBtn_pr1().addActionListener(this);
        mainGUI.getBtn_pr2().addActionListener(this);
        mainGUI.getBtn_pr3().addActionListener(this);
        mainGUI.getBtn_reset().addActionListener(this);


        mainGUI.getTxt_pr0().setText(String.valueOf(sendData2Cloud.getPhotoAmount()));
        mainGUI.getTxt_pr1().setText(String.valueOf(sendData2Cloud.getPlushieAmount()));
        mainGUI.getTxt_pr2().setText(String.valueOf(sendData2Cloud.getToyAmount()));
        mainGUI.getTxt_pr3().setText(String.valueOf(sendData2Cloud.getMilkAmount()));
        mainGUI.getTxt_reset().setText("RESET");
        paintPictures();
        mainGUI.setVisible(true);
    }

    private void paintPictures() {

        bufferedImages = createWindow.getBufferedImages();
        int x = createWindow.getScreensize().width;
        int y = createWindow.getScreensize().height;

        mainGUI.getIcon0().setImage(bufferedImages[0].getScaledInstance(x / 5, ((int) (y / 2.3)), Image.SCALE_DEFAULT));
        mainGUI.getIcon1().setImage(bufferedImages[1].getScaledInstance(x / 5, ((int) (y / 2.3)), Image.SCALE_DEFAULT));
        mainGUI.getIcon2().setImage(bufferedImages[2].getScaledInstance(x / 5, ((int) (y / 2.3)), Image.SCALE_DEFAULT));
        mainGUI.getIcon3().setImage(bufferedImages[3].getScaledInstance(x / 5, ((int) (y / 2.3)), Image.SCALE_DEFAULT));
        mainGUI.getMyBackground().setImage(bufferedImages[4].getScaledInstance(createWindow.getScreensize().width, createWindow.getScreensize().height, Image.SCALE_DEFAULT));


        mainGUI.getLabel0().setIcon(mainGUI.getIcon0());
        mainGUI.getLabel1().setIcon(mainGUI.getIcon1());
        mainGUI.getLabel2().setIcon(mainGUI.getIcon2());
        mainGUI.getLabel3().setIcon(mainGUI.getIcon3());
        mainGUI.getLabel4().setIcon(mainGUI.getMyBackground());


        mainGUI.getBtn_pr0().add(mainGUI.getLabel0());
        mainGUI.getBtn_pr1().add(mainGUI.getLabel1());
        mainGUI.getBtn_pr2().add(mainGUI.getLabel2());
        mainGUI.getBtn_pr3().add(mainGUI.getLabel3());
        //mainGUI.getBtn_reset().add(mainGUI.getTxt_reset());
        mainGUI.add(mainGUI.getLabel4());

        int imgX = mainGUI.getIcon0().getIconWidth();
        int imgY = mainGUI.getIcon0().getIconHeight();

        int spaceX = ((x - 4 * imgX) / 5);
        int spaceY = ((y - imgY) / 3);


        mainGUI.getBtn_pr0().setBounds(spaceX, spaceY, imgX, imgY);
        mainGUI.getBtn_pr1().setBounds(spaceX * 2 + imgX, spaceY, imgX, imgY);
        mainGUI.getBtn_pr2().setBounds(spaceX * 3 + imgX * 2, spaceY, imgX, imgY);
        mainGUI.getBtn_pr3().setBounds(spaceX * 4 + imgX * 3, spaceY, imgX, imgY);

        mainGUI.getBtn_reset().setBounds(spaceX * 4 + imgX * 3, spaceY + imgY + mainGUI.getBtn_pr3().getHeight() / 3, imgX, mainGUI.getBtn_pr3().getHeight() / 5);
        mainGUI.getBtn_reset().setText("RESET");

        mainGUI.getLabel4().setBounds(0, 0, x, y);

        mainGUI.getTxt_pr0().setBounds(spaceX, spaceY + imgY + mainGUI.getBtn_pr3().getHeight() / 10, mainGUI.getBtn_pr3().getWidth(), mainGUI.getBtn_pr3().getHeight() / 10);
        mainGUI.getTxt_pr1().setBounds(spaceX * 2 + imgX, spaceY + imgY + mainGUI.getBtn_pr3().getHeight() / 10, mainGUI.getBtn_pr3().getWidth(), mainGUI.getBtn_pr3().getHeight() / 10);
        mainGUI.getTxt_pr2().setBounds(spaceX * 3 + imgX * 2, spaceY + imgY + mainGUI.getBtn_pr3().getHeight() / 10, mainGUI.getBtn_pr3().getWidth(), mainGUI.getBtn_pr3().getHeight() / 10);
        mainGUI.getTxt_pr3().setBounds(spaceX * 4 + imgX * 3, spaceY + imgY + mainGUI.getBtn_pr3().getHeight() / 10, mainGUI.getBtn_pr3().getWidth(), mainGUI.getBtn_pr3().getHeight() / 10);

        Font font2 = new Font("SansSerif", Font.BOLD, 20);

        mainGUI.getTxt_pr0().setFont(font2);
        mainGUI.getTxt_pr0().setBackground(Color.gray);
        mainGUI.getTxt_pr0().setBorder(null);
        mainGUI.getTxt_pr0().setHorizontalAlignment(mainGUI.getTxt_pr0().CENTER);

        mainGUI.getTxt_pr1().setFont(font2);
        mainGUI.getTxt_pr1().setBackground(Color.gray);
        mainGUI.getTxt_pr1().setBorder(null);
        mainGUI.getTxt_pr1().setHorizontalAlignment(mainGUI.getTxt_pr1().CENTER);

        mainGUI.getTxt_pr2().setFont(font2);
        mainGUI.getTxt_pr2().setBackground(Color.gray);
        mainGUI.getTxt_pr2().setBorder(null);
        mainGUI.getTxt_pr2().setHorizontalAlignment(mainGUI.getTxt_pr2().CENTER);

        mainGUI.getTxt_pr3().setFont(font2);
        mainGUI.getTxt_pr3().setBackground(Color.gray);
        mainGUI.getTxt_pr3().setBorder(null);
        mainGUI.getTxt_pr3().setHorizontalAlignment(mainGUI.getTxt_pr3().CENTER);

        mainGUI.getBtn_reset().setFont(font2);
        mainGUI.getBtn_reset().setBackground(Color.red);
        mainGUI.getBtn_reset().setBorder(null);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        String s = ae.getActionCommand();
        if (s.equals(MainGUI.BUYPHOTO)) {
            sendData2Cloud.buyPhoto();
        } else if (s.equals(MainGUI.BUYPLUSHIE)) {
            sendData2Cloud.buyPlushie();
        } else if (s.equals(MainGUI.BUYTOY)) {
            sendData2Cloud.buyToy();
        } else if (s.equals(MainGUI.BUYMILK)) {
            sendData2Cloud.buyMilk();
        } else if (s.equals(MainGUI.RESETBUTTON)) {
            sendData2Cloud.initMachine();
        } else {
            System.out.println("Select ActionListener failed " + ae.getActionCommand());
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        String s;
        if (o == receiveDataFromCloud) {
            s = receiveDataFromCloud.getField_brand_id();
            System.out.println("CMG select: "+s);
            if (s.equals("photo")) {
                sendData2Cloud.setPhotoAmount(receiveDataFromCloud.getField_amount());
                mainGUI.getTxt_pr0().setText(String.valueOf(sendData2Cloud.getPhotoAmount()));
            } else if (s.equals("plushie")) {
                sendData2Cloud.setPlushieAmount(receiveDataFromCloud.getField_amount());
                mainGUI.getTxt_pr1().setText(String.valueOf(sendData2Cloud.getPlushieAmount()));
            } else if (s.equals("toy")) {
                sendData2Cloud.setToyAmount(receiveDataFromCloud.getField_amount());
                mainGUI.getTxt_pr2().setText(String.valueOf(sendData2Cloud.getToyAmount()));
            } else if (s.equals("milk")) {
                sendData2Cloud.setMilkAmount(receiveDataFromCloud.getField_amount());
                mainGUI.getTxt_pr3().setText(String.valueOf(sendData2Cloud.getMilkAmount()));
            } else if (s.equals("RESET")) {
                System.out.println("Reset all products");
            }
        }
        else if(o==sendData2Cloud){
            mainGUI.getTxt_pr0().setText(String.valueOf(sendData2Cloud.getPhotoAmount()));
            mainGUI.getTxt_pr1().setText(String.valueOf(sendData2Cloud.getPlushieAmount()));
            mainGUI.getTxt_pr2().setText(String.valueOf(sendData2Cloud.getToyAmount()));
            mainGUI.getTxt_pr3().setText(String.valueOf(sendData2Cloud.getMilkAmount()));
        }
        else{
            System.out.println("update failed");
        }
    }
}
