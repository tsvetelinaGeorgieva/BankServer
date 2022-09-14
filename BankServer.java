import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.lang.String;

public class BankServer {
    private static List<String> listOfBranches;
    private static List<Customer> listOfCustomer;

    public static List<String> getListOfBranches() {
        return listOfBranches;
    }

    public static List<Customer> getListOfCustomer() {
        return listOfCustomer;
    }

    static {
        listOfBranches = new LinkedList<>();
        listOfBranches.add("bankOffice1");
        listOfBranches.add("bankOffice2");
        listOfBranches.add("bankOffice3");

        Random gen = new Random();

        listOfCustomer = new LinkedList<>();
        double amount = gen.nextDouble();
       // int customerId = gen.nextInt()*100;
        for (int i = 1; i <=199; i++){
            listOfCustomer.add(new Customer(i, "ClientName" + i, amount));
        }
    }

    public static void main(String[] args) {
         try(ServerSocket server = new ServerSocket(1211)) {
             System.out.println("Server is waiting for connection ...");
             while (true){
                 Socket socket = server.accept();
                 BankClientThread bankOffice = new BankClientThread(socket, listOfBranches, listOfCustomer);
                 bankOffice.start();
             }
         }catch (IOException e){
             System.err.println("The port 1211 is in use.");
         }
    }
}
