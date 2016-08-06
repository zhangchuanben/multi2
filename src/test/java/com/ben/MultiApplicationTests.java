package com.ben;

import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ben.domain.mysql.Product;
import com.ben.domain.mysql.ProductDao;
import com.ben.domain.mysql2.MysqlUser;
import com.ben.domain.mysql2.MysqlUserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MultiApplication.class)
@Rollback
@WebAppConfiguration
public class MultiApplicationTests {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MysqlUserDao userDao;

	@Test
	@Transactional
	@Commit
	public void testSave() {
		Product p = new Product();
		p.setName("aa");
		p.setPrice(1.00);
		p.setId(UUID.randomUUID().toString());
		productDao.save(p);
		
		MysqlUser user = new MysqlUser();
		user.setAge(10);
		user.setEmail("dddds");
		userDao.save(user);
	}
}
