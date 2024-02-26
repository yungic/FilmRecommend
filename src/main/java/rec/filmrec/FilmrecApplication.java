package rec.filmrec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FilmrecApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmrecApplication.class, args);
	}

}