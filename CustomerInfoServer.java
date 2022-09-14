import java.util.List;

public class CustomerInfoServer {
    private List<String> listOfBranches;
    private List<Customer> listOfCustomer;

    public CustomerInfoServer(List<String> listOfBranches, List<Customer> listOfCustomer) {
        this.listOfBranches = listOfBranches;
        this.listOfCustomer = listOfCustomer;
    }

    public CustomerInfoServer(List<String> listOfBranches) {
        this.listOfBranches = listOfBranches;
    }
}
