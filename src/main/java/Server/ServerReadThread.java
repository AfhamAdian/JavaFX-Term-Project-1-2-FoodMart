package Server;

import prevproject.Restaurant;
import util.SocketWrapper;
import util.addFoodInResDTO;

import java.io.IOException;
import java.util.List;

public class ServerReadThread implements Runnable {
    private Thread thr;
    private SocketWrapper socketWrapper;
    private List<Restaurant> restaurantlist;

    public Server server;
    public ServerReadThread(SocketWrapper socketWrapper, Server server)
    {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this);
        this.server = server;
        this.restaurantlist = server.test.getRestaurantlist();
        thr.start();
    }

    public void run() {
        try {

            String s = (String) socketWrapper.read();    //reading restaurant name from client socket

            System.out.println("Received client : " + s);

            for( Restaurant res_temp : restaurantlist) {
                synchronized(this){
                    if (res_temp.getName().equalsIgnoreCase(s)) {
                        socketWrapper.write(res_temp);
                        socketWrapper.write(server.test);
                        this.server.clientMap.put(res_temp.getName().toUpperCase(), socketWrapper);
                        System.out.println(res_temp.getName() + " is mapped to "+ socketWrapper);
                    }
                }
            }
            while (true) {
                Object o = socketWrapper.read();

                 if( o instanceof addFoodInResDTO addFoodDTO)
                 {
                    server.test.addFoodInRes(addFoodDTO.resName,addFoodDTO.food);

                    synchronized (this) {               /////// Writes in DataBase ////////
                        server.test.output_restaurant("NewRestaurant.txt");
                        server.test.output_menu("NewMenu.txt");
                    }
                    System.out.println(addFoodDTO.food.getFood_name() + " was added to" + addFoodDTO.resName + " and Database");
                 }
            //System.out.println("Restaurant "+s+" data is written to its socket by server...");
            }
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
