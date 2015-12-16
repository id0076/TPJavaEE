package ensai.ProjetJavaEE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class VoteApp {

	public static void main(String[] args) {
		SpringApplication.run(VoteApp.class, args);
		int i = 777;
		log.info("Hello World {}", i);
		
	}
}
