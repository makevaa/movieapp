package hh.sof3.movieapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.sof3.movieapp.domain.GenreRepository;
import hh.sof3.movieapp.domain.Movie;
import hh.sof3.movieapp.domain.MovieRepository;
import jakarta.validation.Valid;

@CrossOrigin
@Controller
public class MovieController {

    @Autowired
	private MovieRepository movieRepository; 

    @Autowired
	private GenreRepository genreRepository; 


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

    // Show API page
    @RequestMapping(value="/apipage", method=RequestMethod.GET)
    public String showApiPage() {
        return "apipage";
    }

    // Show all movies
    @RequestMapping(value="/movielist", method=RequestMethod.GET)
    public String showBookList(Model model) {
        model.addAttribute( "movies", movieRepository.findAll() ); 
        return "movielist";
    }

    // Show "Add movie" page
    @RequestMapping(value="/addmovie", method=RequestMethod.GET)
    public String showAddMovie(Model model) {
        model.addAttribute("movie", new Movie());
        model.addAttribute("allGenres", genreRepository.findAll());
        return "addmovie";
    }

    // Show "Edit movie" page
    @RequestMapping(value="/editmovie/{id}", method=RequestMethod.GET)
    public String showEditPage(@PathVariable("id") Long movieId, Model model) {
        Movie movie = movieRepository.findById(movieId).get();
        model.addAttribute("movie", movie);
        model.addAttribute("allGenres", genreRepository.findAll());
        return "editmovie";
    }

    // Save new movie (or update if Movie has id)
    @RequestMapping(value="/savemovie", method=RequestMethod.POST)
    public String save( @Valid  @ModelAttribute("movie") Movie movie, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) { // validation errors 
            //model.addAttribute("movie", new Movie());
            model.addAttribute("allGenres", genreRepository.findAll());
			return "addmovie";  // return back to form
		} else { // no validation errors
            movieRepository.save(movie);
            return "redirect:movielist";
		}
    }

    // Delete movie
    @RequestMapping(value="/deletemovie/{id}", method=RequestMethod.GET)
    public String DeleteMovieById(@PathVariable("id") Long movieId, Model model) {
        movieRepository.deleteById(movieId);
        return "redirect:../movielist";
    }

}
