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
public class MovieController {

    @Autowired
	private MovieRepository movieRepository; 


    // Show login page
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public String showLogin() {
        return "login";
    }

    // Show index page
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String showIndex() {
        return "index";
    }

    // Show all movies
    @RequestMapping(value="/movielist", method=RequestMethod.GET)
    public String showBookList(Model model) {
        model.addAttribute( "movies", movieRepository.findAll() ); 
        return "movielist";
    }

    //https://www.w3schools.com/howto/howto_js_filter_dropdown.asp
    // Show "Add movie" page
    @RequestMapping(value="/addmovie", method=RequestMethod.GET)
    public String showAddMovie(Model model) {
        model.addAttribute("movie", new Movie());
        //model.addAttribute("categories", categoryRepository.findAll());
        return "addmovie";
    }

    // Show "Edit movie" page
    @RequestMapping(value="movies/{id}/edit", method=RequestMethod.GET)
    public String showEditPage(@PathVariable("id") Long movieId, Model model) {
        Movie movie = movieRepository.findById(movieId).get();
        model.addAttribute("movie", movie);
        return "editmovie";
    }

    // Save new movie (or update if Movie has id)
    @RequestMapping(value="/savemovie", method=RequestMethod.POST)
    public String save(Movie movie) {
        movieRepository.save(movie);
        return "redirect:movielist";
    }

    // Delete movie
    @RequestMapping(value="movies/{id}/delete", method=RequestMethod.GET)
    public String DeleteMovieById(@PathVariable("id") Long movieId, Model model) {
        movieRepository.deleteById(movieId);
        return "redirect:../../movielist";
    }

}
