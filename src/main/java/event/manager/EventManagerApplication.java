package event.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "event.manager.dao")
@EntityScan(basePackages = "event.manager.entity")
public class EventManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventManagerApplication.class, args);
		
	}
}
