package com.opitzconsulting.com.Model;

import com.google.gson.Gson;
import com.microsoft.azure.iothub.IotHubEventCallback;
import com.microsoft.azure.iothub.IotHubStatusCode;
import com.microsoft.azure.iothub.Message;
import com.opitzconsulting.com.View.MainGUI;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.Observable;


/**
 * Created by OFI on 27.06.2016.
 */
public class SendData2Cloud extends Observable implements Runnable {

    private AzureConnection azureConnection;
    private ReceiveDataFromCloud receiveDataFromCloud;
    private MainGUI mainGUI;

    private int photoAmount;
    private int plushieAmount;
    private int toyAmount;
    private int milkAmount;

    public SendData2Cloud(){ }

    public SendData2Cloud(ReceiveDataFromCloud receiveDataFromCloud, MainGUI mainGUI){
        this.azureConnection=new AzureConnection();
        this.receiveDataFromCloud=receiveDataFromCloud;
        //receiveDataFromCloud.addObserver(this);

        this.mainGUI=mainGUI;
        initMachine();
    }

    public void initMachine(){
        setPlushieAmount(10);
        setPhotoAmount(10);
        setToyAmount(10);
        setMilkAmount(10);

        sendNull2Cloud("RESET");
        setChanged();
        notifyObservers();

    }

    public void send2Cloud(String status, String brand, int amountLeft, boolean online, BigDecimal changeRemaining)  {

        try {
            TelemetryData telemetryData=new TelemetryData();
            telemetryData.data_status=status;
            telemetryData.data_brandid=brand;
            telemetryData.data_amount=amountLeft;
            //telemetryData.field_online=online;
            //telemetryData.field_change_remaining=changeRemaining.doubleValue();

            String msgStr = telemetryData.serialize();
            Message msg = new Message(msgStr);

            Object lockobj = new Object();
            EventCallback callback = new EventCallback();

            azureConnection.getDeviceClient().sendEventAsync(msg, callback, lockobj);

            synchronized (lockobj) {
                lockobj.wait();
            }
            Thread.sleep(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buyPhoto(){
        if(photoAmount==0){
            System.out.println("Photo not available");
            JOptionPane.showMessageDialog(null,"Photo sold out!");
        }else{
           setPhotoAmount(this.photoAmount-1);
           send2Cloud("sale","photo",getPhotoAmount(), true, BigDecimal.valueOf(1.1));

        }
    }

    public void buyPlushie(){
        if(plushieAmount==0){
            System.out.println("Plushie not available");
            JOptionPane.showMessageDialog(null, "Plushie sold out!");
        }else{
            setPlushieAmount(this.plushieAmount-1);
            send2Cloud("sale","plushie",getPlushieAmount(), true, BigDecimal.valueOf(1.2));

        }
    }
    public void buyToy(){
        if(toyAmount==0){
            System.out.println("Toy not available");
            JOptionPane.showMessageDialog(null, "Toy sold out!");
        }else{
            setToyAmount(this.toyAmount-1);
            send2Cloud("sale","toy",getToyAmount(), true, BigDecimal.valueOf(1.3));


        }
    }
    public void buyMilk(){
        if(milkAmount==0){
            System.out.println("Milk not available");
            JOptionPane.showMessageDialog(null, "Milk sold out!");
        }else{
            setMilkAmount(this.milkAmount-1);
            send2Cloud("sale","milk",getMilkAmount(), true, BigDecimal.valueOf(1.4));
        }
    }

    public void sendNull2Cloud(String status)  {


        try {

            TelemetryData telemetryData=new TelemetryData();
            telemetryData.data_status=status;
            telemetryData.data_brandid="";

            Gson gson=new Gson();

            Message msg = new Message(gson.toJson(telemetryData));

            Object lockobj = new Object();
            EventCallback callback = new EventCallback();
            azureConnection.getDeviceClient().sendEventAsync(msg, callback, lockobj);

            synchronized (lockobj) {
                lockobj.wait();
            }
            Thread.sleep(1000);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        initMachine();
    }

    /*
    @Override
    public void update(Observable o, Object arg) {
        String s=receiveDataFromCloud.getField_brand_id();
        switch (s) {
            case "photo":
                setPhotoAmount(receiveDataFromCloud.getField_amount());
                break;
            case "plushie":
                setPlushieAmount(receiveDataFromCloud.getField_amount());
                break;
            case "toy":
                setToyAmount(receiveDataFromCloud.getField_amount());
                break;
            case "milk":
                setMilkAmount(receiveDataFromCloud.getField_amount());
                break;
            default:
                System.out.println("select: "+s);
                break;
        }
    }
*/
    private static class EventCallback implements IotHubEventCallback
    {
        public void execute(IotHubStatusCode status, Object context) {
            //System.out.println("IoT Hub responded to message with status " + status.name());

            if (context != null) {
                synchronized (context) {
                    context.notify();
                }
            }
        }
    }
    public int getPhotoAmount(){
        return photoAmount;
    }
    public int getPlushieAmount(){
        return plushieAmount;
    }
    public int getToyAmount(){
        return toyAmount;
    }
    public int getMilkAmount(){
        return milkAmount;
    }

    public void setPhotoAmount(int photoAmount){
        this.photoAmount=photoAmount;
    }
    public void setPlushieAmount(int plushieAmount){
        this.plushieAmount=plushieAmount;
    }
    public void setToyAmount(int toyAmount){
        this.toyAmount=toyAmount;
    }
    public void setMilkAmount(int milkAmount){
        this.milkAmount=milkAmount;
    }

}
