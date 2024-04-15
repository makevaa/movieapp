package hh.sof3.movieapp;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.sof3.movieapp.domain.Movie;
import hh.sof3.movieapp.domain.MovieRepository;
import hh.sof3.movieapp.domain.User;
import hh.sof3.movieapp.domain.UserRepository;
import hh.sof3.movieapp.domain.Watch;
import hh.sof3.movieapp.domain.WatchRepository;
import hh.sof3.movieapp.domain.Genre;
import hh.sof3.movieapp.domain.GenreRepository;

@SpringBootApplication
public class MovieApplication {

	private static final Logger log = LoggerFactory.getLogger(MovieApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MovieApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(MovieRepository movieRepository, UserRepository userRepository, WatchRepository watchRepository, GenreRepository genreRepository) {
		return (args) -> {

			// Create test movies
			Movie mov1 = new Movie("A Nightmare on Elm Street", 1984);
			Movie mov2 = new Movie("The Truman Show", 1998);
			Movie mov3 = new Movie("Apocalypse Now", 1979);

			movieRepository.save(mov1);
			movieRepository.save(mov2);
			movieRepository.save(mov3);

			movieRepository.save(new Movie("Aliens", 1986));
			movieRepository.save(new Movie("Hellraiser", 1987));
			movieRepository.save(new Movie("The Last Man on Earth", 1964));
			movieRepository.save(new Movie("The Village", 2004));
			movieRepository.save(new Movie("Inside Out", 2015)); 
			movieRepository.save(new Movie("Freaks", 2018)); 
			movieRepository.save(new Movie("Rosemary's Baby", 1968)); 
			movieRepository.save(new Movie("The Killing", 1956)); 
			movieRepository.save(new Movie("Spider-Man: No Way Home", 2021)); 
			movieRepository.save(new Movie("Legend of Hell House", 1973)); 
			movieRepository.save(new Movie("Halloween", 1978)); 
			movieRepository.save(new Movie("Dune", 2021)); 
			movieRepository.save(new Movie("Men", 2022)); 
			movieRepository.save(new Movie("The Wicker Man", 1973)); 

			SimpleDateFormat fdate = new SimpleDateFormat("dd.MM.yyyy");

			// Create test watches
			Watch w1 = new Watch( fdate.parse("04.04.2024" ), mov1, "ihan hyvä leffa");
			Watch w2 = new Watch( fdate.parse("11.02.2024" ), mov3, "");

			watchRepository.save(w1);
			watchRepository.save(w2);

			// Create test genres
			Genre g1 = new Genre("Comedy");
			Genre g2 = new Genre("Drama");
			Genre g3 = new Genre("Mystery");
			Genre g4 = new Genre("War");

			genreRepository.save(g1);
			genreRepository.save(g2);
			genreRepository.save(g3);
			genreRepository.save(g4);

			genreRepository.save(new Genre("Horror"));
			genreRepository.save(new Genre("Adventure"));
			genreRepository.save(new Genre("Scifi"));
			genreRepository.save(new Genre("Fantasy"));
			

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$hvSsp.0rKbIDyDawzQS0Ae6xmgWAQQp44rGdHiiI76SvQCzhHMzTO", "USER");
			User user2 = new User("admin","$2a$10$C1.SktkimBQccPPj0QOnm.XN63vX156Qjrmeo.af8.LAem3d.88Va", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			

			for (Movie movie : movieRepository.findAll() ) {
				log.info(movie.toString());
			}

			/* 
			for (Category category : categoryRepository.findAll() ) {
				log.info(category.toString());
			}
			*/
		};
	}



}
