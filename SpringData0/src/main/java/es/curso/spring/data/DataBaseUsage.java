package es.curso.spring.data;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseUsage implements CommandLineRunner {

	@Autowired
	private CustomerRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		 // save a couple of customers
        repository.save(new Customer("Customer1", "Apellido1",CustomerTypes.NORMAL.toString()));
        repository.save(new Customer("Customer2", "Apellido2",CustomerTypes.VIP.toString()));
        repository.save(new Customer("Nombre3", "Apellido3",CustomerTypes.NORMAL.toString()));
        repository.save(new Customer("Customer4", "LastName4",CustomerTypes.VIP.toString()));
        repository.save(new Customer("Nombre5", "Apellido5",CustomerTypes.DEFAULTER.toString()));
        repository.save(new Customer("Customer6", "LastName6",CustomerTypes.NORMAL.toString()));
        
        // fetch all customers
        List<Customer> customers = repository.findAll();
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer by ID
        Customer customer = repository.findOne(1L);
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        // fetch customers by last name
        List<Customer> customersLastName = repository.findByLastName("Apellido5");
        System.out.println("Customer found with findByLastName('Apellido5'):");
        System.out.println("--------------------------------------------");
        for (Customer cust : customersLastName) {
            System.out.println(cust);
        }
        
        
        Customer c0 = customersLastName.get(0);
        System.out.println("Borrando "+c0);
        repository.delete(c0);
        
        System.out.println("----------- CUSTOM QUERY -------------------");
        try (Stream<Customer> stream = repository.findAllByCustomQueryAndStream()) {
      	  stream.forEach(c -> System.out.println(c));
      	}
        
        
        System.out.println("----------- CUSTOM QUERY 1-------------------");
        List<Customer> cs = repository.findByFirstNameCustom("Nombre");
        pintaListado(cs);
        
        
        System.out.println("----------- NATIVE QUERY 1-------------------");
        cs = repository.findBySearchTermNative("llido1");
        pintaListado(cs);
        
        System.out.println("----------- NATIVE QUERY 2-------------------");
        cs = repository.findBySearchTermNative("Customer");
        pintaListado(cs);
        
        
        System.out.println("-------------VIP CUSTOMERS -----------------");
        List<Customer> vipCustomers = repository.findByVIPCustomer();
        pintaListado(vipCustomers);
        
	}
	
	private void pintaListado(List<Customer> customers) {
		for (Customer customer : customers) {
			System.out.println(customer);
		}
	}
}
