package com.opitzconsulting.com.Model;

import com.google.gson.Gson;
import com.microsoft.azure.eventhubs.EventData;
import com.microsoft.azure.eventhubs.EventHubClient;
import com.microsoft.azure.eventhubs.PartitionReceiver;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.ServiceBusException;
import com.opitzconsulting.com.Controller.ControllerMainGUI;
import com.opitzconsulting.com.View.MainGUI;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.Observable;
import java.util.function.Consumer;

/**
 * Created by OFI on 24.06.2016.
 */
public class ReceiveDataFromCloud extends Observable implements  Runnable{

    private static String namespaceName = "ihsuprodamres008dednamespace";
    private static String eventHubName = "iothub-ehub-ocvendingm-42876-db18135f57";
    private static String sasKeyName = "iothubowner";
    private static String sasKey = "83YsUlQPb5dl6iFM4huqiLGFPVIkUbz2AlAkf9NvQlU=";

    private ConnectionStringBuilder connStr;

    private Gson gson;
    private  Product product;

    private String data_status;
    private String data_brandid;
    private int data_amount;
    private boolean field_online;
    private double field_change_remaining;




    public ReceiveDataFromCloud() {
        connStr = new ConnectionStringBuilder(namespaceName, eventHubName, sasKeyName, sasKey);}

    private EventHubClient receiveMessages(final String partitionId)
                {
                    EventHubClient client = null;
                    try {

                        client = EventHubClient.createFromConnectionString(connStr.toString()).get();
                    }
                    catch(Exception e) {
                        System.out.println("Failed to create client: " + e.getMessage());
                        System.exit(1);
                    }
                    try {
                        client.createReceiver(
                                EventHubClient.DEFAULT_CONSUMER_GROUP_NAME,
                                partitionId,
                                Instant.now()).thenAccept(new Consumer<PartitionReceiver>()
                        {
                            public void accept(PartitionReceiver receiver)
                            {
                                System.out.println("** Created receiver on partition " + partitionId);
                                try {
                                    while (true) {
                                        Iterable<EventData> receivedEvents = receiver.receive(100).get();
                                        int batchSize = 0;
                                        if (receivedEvents != null)
                                        {
                                            for(EventData receivedEvent: receivedEvents)
                                            {
                                                System.out.println(String.format("\n| Device ID: %s", receivedEvent.getProperties().get("iothub-connection-device-id")));
                                                System.out.println(String.format("| Message Payload: %s", new String(receivedEvent.getBody(),
                                                        Charset.defaultCharset())));
                                                batchSize++;
                                                product=gson.fromJson(new String(receivedEvent.getBody()), Product.class);

                                                data_status=product.getData_status();
                                                data_brandid=product.getField_brand_id();
                                                data_amount=product.getField_amount();
                                                field_online=product.getField_online();
                                                field_change_remaining=product.getField_change_remaining();

                                                setChanged();
                                                notifyObservers();
                                            }
                                        }
                                    }
                                }
                                catch (Exception e)
                                {
                                    System.out.println("Failed to receive messages: " + e.getMessage());
                                }
                            }
                        });
                    }
                    catch (Exception e)
                    {
                        System.out.println("Failed to create receiver: " + e.getMessage());
                    }
                    return client;
                }


    //Programmis running until user abort with Ctrl-C
    private void runnig() throws InterruptedException {
        for (; ; ) {

            Thread.sleep(500);
        }

    }
    public String getData_status(){return data_status;}
    public String getField_brand_id(){return data_brandid;}
    public int getField_amount(){ return data_amount;}
    public boolean getField_online(){return field_online;}
    public double getField_change_remaining(){return field_change_remaining;}

    public Product getProduct(){return product;}

    @Override
    public void run() {
        gson=new Gson();
        product= new Product();

        EventHubClient client0 = receiveMessages("0");
        EventHubClient client1 = receiveMessages("1");
        System.out.println("Press ENTER to exit.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            //client1.closeSync();
            client1.closeSync();
            client0.closeSync();
            System.exit(0);
        } catch (ServiceBusException sbe) {
            System.exit(1);
        }
    }
}
