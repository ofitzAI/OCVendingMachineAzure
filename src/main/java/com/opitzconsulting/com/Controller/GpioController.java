package com.opitzconsulting.com.Controller;

import com.microsoft.azure.iothub.Message;
import com.opitzconsulting.com.Model.AzureConnection;
import com.opitzconsulting.com.Model.SendData2Cloud;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.impl.GpioControllerImpl;

import javax.management.ListenerNotFoundException;
import java.io.IOException;

/**
 * Created by OFI on 30.05.2016.
 */



public class GpioController {

    final com.pi4j.io.gpio.GpioController gpio = GpioFactory.getInstance();
    private AzureConnection azureConnenction;

    private GpioPinDigitalInput photo;
    private GpioPinDigitalInput plushi;
    private GpioPinDigitalInput toy;
    private GpioPinDigitalInput milk;
    private GpioPinDigitalInput reset;

    private GpioPinDigitalOutput pinLEDGreen;
    private GpioPinDigitalOutput pinLEDBlue;
    private GpioPinDigitalOutput pinLEDRed;

    public GpioController(){

        this.azureConnenction=new AzureConnection();

        try {
            gpioInit();
        } catch (InterruptedException e) {
            System.out.println("initGPIO failed " +e.getMessage() );
        } catch (IOException e) {
            System.out.println("IOException " +e.getMessage() );
        }

        try {
            gpioListener();
        } catch (Exception e){
            System.out.println("IOException " +e.getMessage() );
        }

        try {
            runnig();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Program start failed");
        }

    }

    //set GPIOPIN  as Output
    private void gpioInit() throws InterruptedException, IOException {

        this.photo = gpio.provisionDigitalInputPin(RaspiPin.GPIO_11, PinPullResistance.PULL_DOWN);
        this.plushi = gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, PinPullResistance.PULL_DOWN);
        this.toy = gpio.provisionDigitalInputPin(RaspiPin.GPIO_29, PinPullResistance.PULL_DOWN);
        this.milk = gpio.provisionDigitalInputPin(RaspiPin.GPIO_28, PinPullResistance.PULL_DOWN);
        this.reset = gpio.provisionDigitalInputPin(RaspiPin.GPIO_26, PinPullResistance.PULL_DOWN);

        this.pinLEDGreen = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "GreenLED", PinState.LOW);
        this.pinLEDBlue = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "BlueLED", PinState.LOW);
        this.pinLEDRed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "RedLED", PinState.LOW);

        System.out.println("GPIOs are open");
    }

    private void gpioListener(){
        photo.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                if(event.getState().isHigh()) {
                    pinLEDGreen.setState(PinState.HIGH);
                    SendData2Cloud sd2c=new SendData2Cloud();
                    sd2c.buyPhoto();
                }                else {
                    pinLEDGreen.setState(PinState.LOW);
                }
            }
        });

        plushi.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                if(event.getState().isHigh()) {
                    pinLEDGreen.setState(PinState.HIGH);

                    SendData2Cloud sd2c=new SendData2Cloud();
                    sd2c.buyPlushie();
                }
                else {
                    pinLEDGreen.setState(PinState.LOW);
                }
            }

        });

        toy.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                if(event.getState().isHigh()){
                    pinLEDGreen.setState(PinState.HIGH);

                    SendData2Cloud sd2c=new SendData2Cloud();
                    sd2c.buyToy();
                }
                else{
                    pinLEDGreen.setState(PinState.LOW);
                }

            }

        });


        milk.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                if(event.getState().isHigh()){
                    pinLEDGreen.setState(PinState.HIGH);

                    SendData2Cloud sd2c=new SendData2Cloud();
                    sd2c.buyMilk();
                }
                else{
                    pinLEDGreen.setState(PinState.LOW);

                }
            }

        });



        reset.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                try {
                    azureConnenction.getDeviceClient().close();
                    System.out.println("Azure client closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(event.getState().isHigh()){
                    pinLEDGreen.setState(PinState.HIGH);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pinLEDGreen.setState(PinState.LOW);
                    pinLEDRed.setState(PinState.HIGH);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pinLEDRed.setState(PinState.LOW);
                    pinLEDBlue.setState(PinState.HIGH);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pinLEDBlue.setState(PinState.LOW);
                    gpioShutdown();
                    System.out.println("GPIOs are shutting down...");

                }
                else{


                }
            }

        });
    }
    public GpioPinDigitalOutput getPinLEDGreen(){ return pinLEDGreen;}
    public GpioPinDigitalOutput getPinLEDBlue(){ return pinLEDBlue;}
    public GpioPinDigitalOutput getPinLEDRed(){ return pinLEDRed;}

        // create and register gpio pin listener


    //Programmis running until user abort with Ctrl-C
    private void runnig() throws InterruptedException {
        for(;;){

            Thread.sleep(500);
        }
    }

    //terminate all Pi4J GPIO Controller
    private void gpioShutdown(){
        gpio.shutdown();
    }

}
