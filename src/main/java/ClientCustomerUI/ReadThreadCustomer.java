package ClientCustomerUI;

import prevproject.RestaurantManager;
import util.DataToCustomerDTO;
import util.SocketWrapper;

///////////////////////// Reads Data DTO from server /////////////////////////
public class ReadThreadCustomer implements Runnable{
    private Thread thr;
    public RestaurantManager restaurantManager;

    public SocketWrapper socketWrapper;

    //public Server server;

    public ReadThreadCustomer(SocketWrapper socketWrapper)
    {
        this.socketWrapper = socketWrapper;
        //this.server = server;
        thr = new Thread(this,"Customer Read Thread");
        thr.start();
    }

    @Override
    public void run() {

//        while (true)
//        {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
                Object o = socketWrapper.read();
                if (o instanceof DataToCustomerDTO temp) {
                    this.restaurantManager = temp.restaurantManager;
                   // break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        //}
        //end of run
    }
}
