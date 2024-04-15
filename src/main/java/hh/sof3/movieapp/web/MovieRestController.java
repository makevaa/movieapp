package hh.sof3.movieapp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof3.movieapp.domain.Movie;
import hh.sof3.movieapp.domain.MovieRepository;


@CrossOrigin
@Controller
public class MovieRestController {

    @Autowired
	private MovieRepository movieRepository; 

    // get all movies
    @RequestMapping(value="/movies", method = RequestMethod.GET)
    public @ResponseBody List<Movie> movieListRest() {	
        return (List<Movie>) movieRepository.findAll();
    }    

    // get movie by id
    @RequestMapping(value="/movies/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Movie> findMovieRest(@PathVariable("id") Long movieId) {	
    	return movieRepository.findById(movieId);
    }    
    
    // delete movie by id
    @DeleteMapping("/movies/{id}")
    void deleteMovie(@PathVariable("id") Long id) {
        movieRepository.deleteById(id);
    }


    // update or create new movie
    @PutMapping("/movies/{id}")
    Movie replaceMovie(@RequestBody Movie newMovie, @PathVariable Long id) {
    return movieRepository.findById(id)
      .map(movie -> {
        movie.setTitle(newMovie.getTitle());
        movie.setReleaseYear(newMovie.getReleaseYear());
        return movieRepository.save(movie);
      })
      .orElseGet(() -> {
        return movieRepository.save(newMovie);
      });
  }
    
}
