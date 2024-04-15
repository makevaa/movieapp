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


import hh.sof3.movieapp.domain.Genre;
import hh.sof3.movieapp.domain.GenreRepository;
import jakarta.validation.Valid;

@CrossOrigin
@Controller
public class GenreController {

    @Autowired
	private GenreRepository genreRepository; 



    // Show all genres
    @RequestMapping(value="/genrelist", method=RequestMethod.GET)
    public String showGenres(Model model) {
        model.addAttribute( "genres", genreRepository.findAll() ); 
        return "genrelist";
    }

    // Show "Add genre" page
    @RequestMapping(value="/addgenre", method=RequestMethod.GET)
    public String showAddGenrePage(Model model) {
        model.addAttribute("genre", new Genre());
        return "addgenre";
    }

    // Show "Edit genre" page
    @RequestMapping(value="/editgenre/{id}", method=RequestMethod.GET)
    public String showEditPage(@PathVariable("id") Long genreId, Model model) {
        Genre genre = genreRepository.findById(genreId).get();
        model.addAttribute("genre", genre);
        return "editgenre";
    }

    // Save new genre (or update if Genre has id)
    @RequestMapping(value="/savegenre", method=RequestMethod.POST)
    public String save( @Valid  @ModelAttribute("genre") Genre genre, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) { // validation errors 
			return "addgenre";  // return back to form
		} else { // no validation errors
            genreRepository.save(genre);
            return "redirect:genrelist";
		}
    }

    // Delete genre
    @RequestMapping(value="/deletegenre/{id}", method=RequestMethod.GET)
    public String deleteGenre(@PathVariable("id") Long genreId, Model model) {
        genreRepository.deleteById(genreId);
        return "redirect:../genrelist";
    }

}
