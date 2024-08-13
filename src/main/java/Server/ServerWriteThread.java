package Server;

import prevproject.Restaurant;
import util.SocketWrapper;

import java.io.IOException;
import java.util.Scanner;

public class ServerWriteThread implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;

    private Server server;
    String name;

    public ServerWriteThread(Server server) {
        //this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        this.server = server;
        thr.start();
    }

    public void run() {
        try {
            Scanner scanner = new Scanner(System.in);
            String tempString1,tempString2,cmd;
            Double temp_double1;
            Integer temp_int1,temp_int2;

            while (true) {
                //System.out.println("Enter a message to send: ");
                //String s = input.nextLine();
                //socketWrapper.write(name + ":" + s);
                cmd = scanner.nextLine();

                /////////////////// Adds restaurant if given add in server console /////////////////
                //            String cmd = sc.nextLine();                               // reads data from server console
//
//
                if(cmd.equalsIgnoreCase("exit")){
                    System.out.println(cmd);
                    server.exitFromServer();
                    System.out.println("Data Saved to Database");
                }
                if( cmd.equalsIgnoreCase("add")) {
                    System.out.printf("Enter Restaurant Name : ");
                    tempString1 = scanner.nextLine();
                    System.out.printf("Enter Restaurant ID : ");
                    temp_int1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.printf("Enter Restaurant Score : ");
                    temp_double1 = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.printf("Enter Restaurant Price : ");
                    tempString2 = scanner.nextLine();
                    System.out.printf("Enter Restaurant Zip-Code : ");
                    temp_int2 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.printf("Enter Restaurant Categories : ");
                    String catagories_fullline = scanner.nextLine();

                    if(catagories_fullline.isEmpty()){
                        System.out.println("Restaurant must have 1-3 categories.");
                        break;
                    }
                    String [] array = catagories_fullline.split(",", -1);   // splits the comma separated word

                    if(array.length < 1 || array.length > 3 ) {
                        System.out.println("Restaurant must have 1-3 categories.");
                        break;
                    }

                    //Restaurant(int id, String name, double score, String price, int zip, String[] categories)
                    Restaurant restaurant = new Restaurant(temp_int1,tempString1,temp_double1,tempString2,temp_int2,array);
                    int checker;
                    synchronized (this){
                        checker = server.test.addRestaurant( restaurant );
                    }
                    if( checker == 0){
                        System.out.println("Restaurant successfully added.");

                        server.test.output_menu("NewMenu.txt");
                        server.test.output_restaurant("NewRestaurant.txt");             //UPDATE

                    }
                    else if( checker == 1 ) System.out.println("Restaurant Name or Id already exists in database.");
                    else if( checker == -1 ) System.out.println("Restaurant must have 1-3 categories.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                socketWrapper.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//
//
//
//package Server;
//
//import prevproject.Restaurant;
//import util.SocketWrapper;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ServerWriteThread implements Runnable {
//    private Thread thr;
//    private SocketWrapper socketWrapper;
//    private List<Restaurant> restaurantlist;
//    public Server server;
//    public ServerWriteThread( Server server)
//    {
//        this.thr = new Thread(this);
//        this.server = server;
//        this.restaurantlist = server.test.getRestaurantlist();
//        thr.start();
//    }
//
//    public void run() {
//        try {
//            while (true) {
//                String s = (String) socketWrapper.read();    //reading restaurant name from client socket
//                System.out.println("Received client : " + s);
//
//                for( Restaurant res_temp : restaurantlist) {
//                    synchronized(this){
//                        if (res_temp.getName().equalsIgnoreCase(s)) {
//                            socketWrapper.write(res_temp);
//                            socketWrapper.write(server.test);
//                            this.server.clientMap.put(res_temp.getName(), socketWrapper);
//                        }
//                    }
//                }
//                //System.out.println("Restaurant "+s+" data is written to its socket by server...");
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            try {
//                socketWrapper.closeConnection();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
