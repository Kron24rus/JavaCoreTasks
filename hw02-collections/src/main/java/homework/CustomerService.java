package homework;


import java.util.*;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    private final NavigableMap<Customer, String> customersMap = new TreeMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return buildEntryClone(customersMap.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        return buildEntryClone(customersMap.higherEntry(customer));
    }

    public void add(Customer customer, String data) {
        customersMap.put(customer, data);
    }

    private Map.Entry<Customer, String> buildEntryClone(Map.Entry<Customer, String> customerEntry) {
        Map.Entry<Customer, String> entryCopy = null;
        if (customerEntry != null) {
            Customer customer = customerEntry.getKey();
            Customer customerCopy = new Customer(customer.getId(), customer.getName(), customer.getScores());
            entryCopy = new AbstractMap.SimpleEntry<>(customerCopy, customerEntry.getValue());
        }
        return entryCopy;
    }
}
