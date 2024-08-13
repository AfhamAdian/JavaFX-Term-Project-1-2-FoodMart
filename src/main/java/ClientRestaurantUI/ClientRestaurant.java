package ClientRestaurantUI;

import prevproject.RestaurantManager;
import util.SocketWrapper;

public class ClientRestaurant {

    public SocketWrapper socketWrapper;
    public RestaurantReadThread readThread;

    public RestaurantManager restaurantManager;
    public ClientRestaurant(String serverAddress, int serverPort, String res_name) {
        try {
            socketWrapper = new SocketWrapper(serverAddress, serverPort); // connects with server
            System.out.println("Client Connected with Server");
            socketWrapper.write((String)"CLIENTRESTAURANT");

//            Scanner sc = new Scanner(System.in);        //////fx part start/////////
//            String res_name = sc.nextLine();            // takes res_name as input from client console
            socketWrapper.write( res_name );            // write name to client socket


            readThread = new RestaurantReadThread( socketWrapper,this );  // thread for reading Restaurant object from socket
            restaurantManager = readThread.restaurantManager;

            Thread.sleep(100);
            //restaurantManager.showAllRestaurantDetails();

            //////////////////// client main thread where we can search all thing in restaurant ///////////////
//            while(true)
//            {
////                String cmd = sc.nextLine();
////                if( cmd.equalsIgnoreCase("count")) {
////                    System.out.println( readThread.restaurant.itemInRestaurant() );
////                }
////                else if(cmd.equalsIgnoreCase("search cost")) {
////                    System.out.println( readThread.restaurant.getCostliestFood() );
////                }
//                System.out.println("sadasd");
//                if(readThread.orderedFoodList != null) {
//                    System.out.println(readThread.orderedFoodList.get(0));
//                }
//                else System.out.println("n");
//            }
         } catch (Exception e) {
            System.out.println(e);
        }
    }

//    public static void main(String[] args) {
//        String serverAddress = "127.0.0.1";
//        int serverPort = 12345;
//        new ClientRestaurant(serverAddress, serverPort,"kfc");
//    }
}
