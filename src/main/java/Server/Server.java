package Server;

import prevproject.RestaurantManager;
import util.SocketWrapper;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;


 public class Server {

    private static String INPUT_FILE_NAME = "restaurant.txt";
    private static final String OUTPUT_RESTAURANT_FILE_NAME = "restaurant.txt";
    private static final String OUTPUT_FOOD_FILE_NAME = "menu.txt";
    private ServerSocket serverSocket;
    public HashMap<String, SocketWrapper> clientMap;
    public RestaurantManager test;



    //public
    public Server() throws IOException, ClassNotFoundException {
        System.out.println(" Server Starting....");
        this.test = new RestaurantManager();
        ////////////////////// taking input from txt file///////////////////
        test.input_restaurant(INPUT_FILE_NAME);
        INPUT_FILE_NAME = "menu.txt";
        test.input_menu(INPUT_FILE_NAME);

        ////////////////////////HashMap Mapping ////////////////////////////////
        clientMap = new HashMap<>();

        serverSocket = new ServerSocket(12345);             // opening server socket

        new ServerWriteThread(this);

        Scanner sc = new Scanner(System.in);
        while (true)
        {
            Socket clientSocket = serverSocket.accept();
            SocketWrapper socketWrapper = new SocketWrapper(clientSocket);

            String clientStatus = (String) socketWrapper.read();


            if( clientStatus.equals("CLIENTRESTAURANT"))              // checks whether it is restaurant
            {
                serveRestaurant(socketWrapper, this);           // transferring client restaurant details to client
//                for (var entry : clientMap.entrySet()) {
//                    System.out.println(entry.getKey() + ": " + entry.getValue());
//                }
            }
            if( clientStatus.equals("CLIENTCUSTOMER"))                  // checks whether it is customer
            {
                serveCustomer(socketWrapper,this);
            }
        }
    }

    public void serveRestaurant(SocketWrapper socketWrapper, Server server) {
        new ServerReadThread(socketWrapper, server);
    }
    public void serveCustomer(SocketWrapper socketWrapper, Server server) {
        new ServerReadThreadCustomer(socketWrapper, server);
    }

    public void exitFromServer() throws IOException {
        test.output_restaurant("NewRestaurant.txt");
        test.output_menu("NewMenu.txt");
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Server();
    }
}
