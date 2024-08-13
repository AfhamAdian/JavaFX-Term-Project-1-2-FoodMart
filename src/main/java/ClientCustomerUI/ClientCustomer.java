package ClientCustomerUI;

import Customer.ReadThreadCustomer;
import prevproject.Food;
import prevproject.RestaurantManager;
import util.OrderedFoodDTO;
import util.SocketWrapper;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ClientCustomer {

    public RestaurantManager restaurantManager;
    public ReadThreadCustomer readThread;
    private SocketWrapper socketWrapper;

    public SocketWrapper getSocketWrapper() {
        return socketWrapper;
    }


    public ClientCustomer(String serverAddress, int serverPort) throws IOException, InterruptedException
    {
        //try {
        socketWrapper = new SocketWrapper(serverAddress, serverPort); // connects with server
        System.out.println("ClientCustomer Connected with Server");

        socketWrapper.write((String) "CLIENTCUSTOMER");

        readThread = new ReadThreadCustomer(socketWrapper);  //opens a customer read thread to read data from server
        //Thread thr = new Thread(this);
        Thread.sleep(200);
        restaurantManager = readThread.restaurantManager;

        restaurantManager.showAllRestaurantDetails();
    }
}