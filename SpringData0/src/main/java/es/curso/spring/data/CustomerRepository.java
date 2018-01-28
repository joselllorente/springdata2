package es.curso.spring.data;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);

	List<Customer> findByFirstName(String firstName);

	@Query("select c from Customer c")
	Stream<Customer> findAllByCustomQueryAndStream();

	@Query("SELECT c FROM Customer c WHERE c.firstName like CONCAT('%', :searchTerm , '%')")
	public List<Customer> findByFirstNameCustom(@Param("searchTerm") String searchTerm);

	@Query(value = "SELECT * FROM TB_CUSTOMER cust WHERE "
			+ "LOWER(cust.FIRST_NAME) LIKE LOWER(CONCAT('%',:searchTerm, '%')) OR "
			+ "LOWER(cust.LAST_NAME) LIKE LOWER(CONCAT('%',:searchTerm, '%'))", nativeQuery = true)
	public List<Customer> findBySearchTermNative(@Param("searchTerm") String firstOrLastName);

	@Query(nativeQuery = true)
	public List<Customer> findByVIPCustomer();
	//public List<Customer> findByVIPCustomer(@Param("description") String description);

}