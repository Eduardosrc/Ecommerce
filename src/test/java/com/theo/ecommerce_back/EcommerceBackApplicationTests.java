package com.theo.ecommerce_back;

import com.theo.ecommerce_back.repository.LocalUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EcommerceBackApplicationTests {

	@Autowired
	LocalUserRepository repo;

	@Test
	void contextLoads() {


	}

}
