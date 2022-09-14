import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class BankClientThread extends Thread{
    private Socket socket;
    private List<String> listOfBranches;
    private List<Customer> listOfCustomer;

    public BankClientThread(Socket socket, List<String> listOfBranches, List<Customer> listOfCustomer) {
        this.socket = socket;
        this.listOfBranches = listOfBranches;
        this.listOfCustomer = listOfCustomer;
    }

    @Override
    public void run(){
            try(PrintStream writer = new PrintStream(socket.getOutputStream(), true); Scanner reader = new Scanner(socket.getInputStream())){
                writer.print("ID of bank office:");
                String idOfBankOffice = reader.nextLine();
                List<String> bankOffices = BankServer.getListOfBranches();
                String authorizedBankOffice = null;
                for (int i = 0; i < listOfBranches.size(); i++){
                    if(idOfBankOffice.equals(bankOffices.get(i))){
                        authorizedBankOffice = idOfBankOffice;
                        break;
                    }
                }

                if(authorizedBankOffice != null){
                    writer.println("OK");
                    writer.print("ClientId:");
                    int clientId = reader.nextInt();
                    List<Customer> clients = BankServer.getListOfCustomer();
                    int searchClient = 125986325;
                    for (Customer client: clients){
                        if(clientId == client.getCustomerId()){
                            searchClient = clientId;
                            break;
                        }
                    }

                    if(searchClient != 125986325){
                        for (Customer client: clients){
                            if(clientId == client.getCustomerId()){
                                writer.print(client.getCustomerId() + "*"+ client.getCustomerName()+ "*" + client.getAmount());
                                break;
                            }
                        }
                    }else{
                        writer.print("No such customer");
                    }

                }else{
                    writer.println("Invalid code");
                }

            }catch (IOException e){
                System.err.println("IOException");
            }
    }
}
