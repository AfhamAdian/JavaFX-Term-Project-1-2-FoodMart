package Customer;

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
    SocketWrapper socketWrapper;

    public ClientCustomer(String serverAddress, int serverPort) throws IOException, ClassNotFoundException, InterruptedException {
        //try {
        socketWrapper = new SocketWrapper(serverAddress, serverPort); // connects with server
        System.out.println("ClientCustomer Connected with Server");

        socketWrapper.write((String)"CLIENTCUSTOMER");

        readThread = new ReadThreadCustomer(socketWrapper);
        //Thread thr = new Thread(this);
        Thread.sleep(200);
        restaurantManager = readThread.restaurantManager;

        restaurantManager.showAllRestaurantDetails();

        Scanner sc = new Scanner(System.in);
        while(true){
            String temp = sc.nextLine();
            String temp1;
            if( temp.equalsIgnoreCase("name")) {
                System.out.println("enter retaurant name");
                temp = sc.nextLine();
                System.out.println("enter food name");
                temp1= sc.nextLine();
                restaurantManager.printFoodList(restaurantManager.searchFoodByNameInRes(temp1,temp));
            }
            else if (temp.equalsIgnoreCase("order"))
            {
                System.out.println("Enter resname : ");
                temp = sc.nextLine();
                System.out.println("Enter foodname");
                temp1 = sc.nextLine();
                List<Food> food = restaurantManager.searchFoodByNameInRes(temp1,temp);
                restaurantManager.printFoodList(food);
                //if( food.isEmpty() ) System.out.println("bug paila....");
                try {
                    //socketWrapper.write(new OrderedFoodDTO(food.get(0), temp));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }




        // while(true){}
        //readThread = new ReadThreadCustomer(socketWrapper);  // thread for reading Restaurant object from socket

        //////////////////// client main thread where we can search all thing in restaurant ///////////////
//            while(true)
//            {
//                String cmd = sc.nextLine();
//                if( cmd.equalsIgnoreCase("count")) {
//                    System.out.println( readThread.restaurant.itemInRestaurant() );
//                }
//                else if(cmd.equalsIgnoreCase("search cost")) {
//                    System.out.println( readThread.restaurant.getCostliestFood() );
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        }

        public static void main (String[]args) throws IOException, ClassNotFoundException, InterruptedException {
            String serverAddress = "127.0.0.1";
            int serverPort = 12345;
            new ClientCustomer(serverAddress, serverPort);
        }
    }