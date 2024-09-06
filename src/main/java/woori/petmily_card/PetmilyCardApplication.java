package woori.petmily_card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PetmilyCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetmilyCardApplication.class, args);
	}

}
