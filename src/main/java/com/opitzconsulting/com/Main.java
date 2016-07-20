package com.opitzconsulting.com;
import com.opitzconsulting.com.Controller.ControllerMainGUI;
import com.opitzconsulting.com.Controller.*;
import com.opitzconsulting.com.Model.*;
import com.opitzconsulting.com.View.*;

import java.io.IOException;

public class Main {
    static ReceiveDataFromCloud receiveDataFromCloud;
    //static GpioController gpioController;
    static Thread t1=new Thread(receiveDataFromCloud=new ReceiveDataFromCloud());
    //static Thread t2=new Thread((Runnable) (gpioController=new GpioController()));


    public static void main(String[] args) throws IOException, InterruptedException
    {
        //t2.start();
        t1.start();
        MainGUI mainGUI=new MainGUI();
        SendData2Cloud sendData2Cloud=new SendData2Cloud(receiveDataFromCloud, mainGUI);
        ControllerMainGUI cmg=new ControllerMainGUI(mainGUI, sendData2Cloud, receiveDataFromCloud);
        //GpioController gp=new GpioController();
    }
}
