package Server;

import prevproject.RestaurantManager;
import util.DataToCustomerDTO;
import util.OrderedFoodDTO;
import util.SocketWrapper;
import util.addFoodInResDTO;

import java.io.IOException;

////////////////////////////////// Sends data(DTO) to customer /////////////////////////////////
public class ServerReadThreadCustomer implements Runnable{

    public RestaurantManager restaurantManager;
    private Thread thr;
    private SocketWrapper socketWrapper;

    public Server server;
    public ServerReadThreadCustomer(SocketWrapper socketWrapper, Server server) {
        this.socketWrapper = socketWrapper;
        this.thr = new Thread(this,"ServerReadThreadCustomer");
        this.server = server;
        this.restaurantManager = server.test;
        System.out.println("printitng");
        for (var entry : server.clientMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        thr.start();
    }

    @Override
    public void run() {
        DataToCustomerDTO dataToCustomerDTO = new DataToCustomerDTO( restaurantManager );
        try {
            socketWrapper.write(dataToCustomerDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while( true )
        {
            try {
                Object o = socketWrapper.read();

                if(o instanceof OrderedFoodDTO orderedFoodDTO) {
                    System.out.println(server.clientMap.get( orderedFoodDTO.getResName().toUpperCase() ));
                    for (var entry : server.clientMap.entrySet()) {
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    SocketWrapper restaurantSocket = server.clientMap.get( orderedFoodDTO.getResName().toUpperCase() );
                    System.out.println("paisi" + orderedFoodDTO.getResName().toUpperCase() );
                    restaurantSocket.write( orderedFoodDTO );
                }

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
