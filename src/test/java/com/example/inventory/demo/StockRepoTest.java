package com.example.inventory.demo;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class StockRepoTest {
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private StockRepository repository;

	private Long id;
	@Test
	public void testExample() throws Exception {
		StockGroup grp = new StockGroup.Builder().groupName("Bit").build();
		entityManager.persist(grp);

		Stock stk = new Stock.Builder().name("Drill").group(grp).isActive(true).build();
		entityManager.persist(stk);
		entityManager.flush();
		id = stk.getId();

		List<Stock> stocks = this.repository.findByName("Drill");
		assertThat(stocks.get(0).getName(), equalTo("Drill"));

		Stock stk1 = entityManager.find(Stock.class, id);
		
		stk1.setName("Stk2");
		entityManager.persist(stk);
		entityManager.flush();

		List<Stock> stocks1 = this.repository.findByName("Stk2");
		assertThat(stocks1.get(0).getName(), equalTo("Stk2"));
		
		AuditReader reader = AuditReaderFactory.get(entityManager);
		System.out.println(reader.find(Stock.class, id, 1));
		System.out.println(reader.find(Stock.class, id, 2));
	}

}
