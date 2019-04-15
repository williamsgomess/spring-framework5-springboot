package br.com.projetoum.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.projetoum.entity.User;
import br.com.projetoum.repository.UserRepository;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		List<User> users = userRepository.findAll();

		if (users.isEmpty()) {

			createUser("Nataniel", "nataniel.paiva@gmail.com");
			createUser("João","joao@gmail.com");
			createUser("Maria", "maria@gmail.com");
			
		}
		
		User user = userRepository.findByEmailQualquerCoisa("nataniel.paiva@gmail.com");

		System.out.println(user.getName());

	}
	
	
	public void createUser(String name, String email) {
		
		User user = new User(name, email);
		
		userRepository.save(user);
		
		
	}

}
