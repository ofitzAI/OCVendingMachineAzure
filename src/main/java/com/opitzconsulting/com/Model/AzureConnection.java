package com.opitzconsulting.com.Model;

import com.microsoft.azure.iothub.DeviceClient;
import com.microsoft.azure.iothub.IotHubClientProtocol;

/**
 * Created by OFI on 27.06.2016.
 */
public class AzureConnection implements Runnable {
    //public static String connString = "HostName=OCVendingMachineNs.azure-devices.net;DeviceId=OCVendingMachine;SharedAccessKey=zuHqkHYpRBDgOjq9FK8Irg==";
    public static String connString = "HostName=OCVendingMachineNs.azure-devices.net;DeviceId=OCVendingMachine;SharedAccessKey=zuHqkHYpRBDgOjq9FK8Irg==";
    public static IotHubClientProtocol protocol = IotHubClientProtocol.AMQPS;
    private DeviceClient client;

    public AzureConnection(){
        try{
            client = new DeviceClient(connString, protocol);
            client.open();
            System.out.println("IoT Hub client open");
        }catch (Exception e){
            System.out.println("IoT Hub opening failed");
        }
    }

    public DeviceClient getDeviceClient(){
        return client;
    }

    @Override
    public void run() {


    }
}
