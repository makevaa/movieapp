package hh.sof3.movieapp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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

    // RESTful
    @RequestMapping(value="/movies", method = RequestMethod.GET)
    public @ResponseBody List<Movie> movieListRest() {	
        return (List<Movie>) movieRepository.findAll();
    }    

    // RESTful
    @RequestMapping(value="/movies/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Movie> findMovieRest(@PathVariable("id") Long movieId) {	
    	return movieRepository.findById(movieId);
    }    

    /* 
    // RESTful
    @RequestMapping(value="/movies/{id}/delete", method = RequestMethod.GET)
    public @ResponseBody Optional<Movie> findBookRest(@PathVariable("id") Long movieId) {	
        return movieRepository.findById(movieId);
    }    
    */
}
