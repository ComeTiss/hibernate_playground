package com.example.database_struct;

import com.example.database_struct.model.Customer;
import com.example.database_struct.model.Order;
import com.example.database_struct.model.Property;
import com.example.database_struct.model.User;
import com.example.database_struct.repository.CustomerRepository;
import com.example.database_struct.repository.OrderRepository;
import com.example.database_struct.repository.PropertyRepository;
import com.example.database_struct.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootApplication
public class DatabaseStructApplication implements CommandLineRunner {

	@Autowired private CustomerRepository customerRepository;
	@Autowired private OrderRepository orderRepository;
	@Autowired private UserRepository userRepository;
	@Autowired private PropertyRepository propertyRepository;

	public static void main(String[] args) {
		SpringApplication.run(DatabaseStructApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testCustomerOrder();
		testUserLikeProperty();
	}

	private void testCustomerOrder() {
		// RESET
		orderRepository.deleteAll();
		customerRepository.deleteAll();

		// CREATE Customer
		Assert.isTrue(customerRepository.findAll().size() == 0);
		customerRepository.save(new Customer());
		List<Customer> customers = customerRepository.findAll();
		Assert.isTrue(customers.size() == 1);

		// CREATE ORDER
		Assert.isTrue(orderRepository.findAll().size() == 0);
		orderRepository.save(new Order(customers.get(0)));
		Assert.isTrue(orderRepository.findAll().size() == 1);
	}

	private void testUserLikeProperty() {
		// RESET db
		userRepository.deleteAll();
		propertyRepository.deleteAll();

		// CREATE user
		userRepository.save(new User("user A"));
		userRepository.save(new User("user B"));
		List<User> users = userRepository.findAllWithProperties();
		Assert.isTrue(users.size() == 2);

		// Create property
		propertyRepository.save(new Property("Paris, Rue de la Paix"));
		propertyRepository.save(new Property("London, Kengsinton street"));
		List<Property> properties = propertyRepository.findAll();
		Assert.isTrue(properties.size() == 2);

		// Create likes
		User userWithLikes = users.get(0);
		userWithLikes.getProperties().add(properties.get(0));
		userWithLikes.getProperties().add(properties.get(1));
		userRepository.save(userWithLikes);
	}
}
