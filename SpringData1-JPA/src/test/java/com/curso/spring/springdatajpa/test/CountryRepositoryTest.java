package com.curso.spring.springdatajpa.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.curso.spring.springdatajpa.AuthenticationMockup;
import com.curso.spring.springdatajpa.model.Country;
import com.curso.spring.springdatajpa.repository.CountryRepository;
import com.curso.spring.springdatajpa.repository.specifications.CountrySpecifications;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("file:src/main/resources/applicationContext.xml")
public class CountryRepositoryTest
{

	@Autowired
	private CountryRepository countryRepository;

	@Before
	@Transactional
	public void setUp() throws Exception
	{
		AuthenticationMockup.UserName = "joseluis";

		// empty repository
		countryRepository.deleteAllInBatch();

		// insert
		countryRepository.save(new Country("Spain", 47265321));
		countryRepository.save(new Country("Mexico", 115296767));
		countryRepository.save(new Country("Germany", 81799600));
		countryRepository.save(new Country("Finland", 5470820));
		countryRepository.save(new Country("Colombia", 47846160));
		countryRepository.save(new Country("Costa Rica", 4586353));
		countryRepository.save(new Country("Norway", 5136700));

	}

	@Test
	public void testAudit() throws Exception
	{
		Country country = new Country();
		country.setName("Bolivia");
		country.setPopulation(10556105);

		country = countryRepository.save(country);
		assertTrue(country.getCreateBy().equals(country.getLastModifiedBy()));
		assertTrue(country.getCreatedDate().equals(country.getLastModifiedDate()));

		Thread.sleep(2000);

		AuthenticationMockup.UserName = "update";
		country.setName("Estado de Bolivia");
		country = countryRepository.save(country);
		assertTrue(country.getLastModifiedBy().equals(AuthenticationMockup.UserName));
		assertTrue(country.getCreateBy().equals("joseluis"));
		assertFalse(country.getCreatedDate().equals(country.getLastModifiedDate()));

		AuthenticationMockup.UserName = "joseluis";
		countryRepository.delete(country);

	}

	@Test
	public void testSimpleQuerys()
	{
		assertTrue(countryRepository.findByName("Germany").getName().equals("Germany"));
		assertNull(countryRepository.findByName("France"));
		assertTrue(countryRepository.countByPopulationGreaterThan(45000000) == 4);
		assertTrue(countryRepository.findByPopulationGreaterThan(100000000).get(0).getName().equals("Mexico"));
		assertTrue(countryRepository.exists("Spain"));
		assertFalse(countryRepository.exists("Italy"));

	}

	@Test
	public void testQuerysSortingAndPaging()
	{
		List<Country> countries = countryRepository.findByPopulationGreaterThanOrderByPopulationAsc(45000000);
		assertTrue(countries.size() == 4);
		assertTrue(countries.get(0).getName().equals("Spain"));
		assertTrue(countries.get(3).getName().equals("Mexico"));

		Page<Country> page0 = countryRepository.getByNameWithQuery("%i%", new PageRequest(0, 4, new Sort(
				new Sort.Order(Sort.Direction.ASC, "name"))));
		assertTrue(page0.getTotalElements() == 5);
		assertTrue(page0.getTotalPages() == 2);
		assertTrue(page0.isFirst());
		assertTrue(page0.getContent().get(0).getName().equals("Colombia"));

	}

	@Test
	public void testModifyingQuerys() throws Exception
	{
		Calendar creation = countryRepository.findByName("Norway").getCreation();
		Thread.sleep(2000);
		assertTrue(countryRepository.updateCreation(Calendar.getInstance()) == 7);
		assertTrue(countryRepository.findByName("Norway").getCreation().after(creation));
		assertTrue(countryRepository.deleteByName("%") == 0);
		assertTrue(countryRepository.deleteByName("Norway") == 1);
	}

	@Test
	public void testJpaCriteria()
	{
		assertTrue(countryRepository.findOne(CountrySpecifications.searchByName("Mexico")).getName().equals("Mexico"));
	}

}
