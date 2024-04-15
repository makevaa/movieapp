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

import hh.sof3.movieapp.domain.Genre;
import hh.sof3.movieapp.domain.GenreRepository;


@CrossOrigin
@Controller
public class GenreRestController {

    @Autowired
	private GenreRepository genreRepository; 

    // get all genres
    @RequestMapping(value="/genres", method = RequestMethod.GET)
    public @ResponseBody List<Genre> genreListRest() {	
        return (List<Genre>) genreRepository.findAll();
    }    

    // get genre by id
    @RequestMapping(value="/genres/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Genre> findGenreRest(@PathVariable("id") Long id) {	
    	return genreRepository.findById(id);
    }    
    
    // delete genre by id
    @DeleteMapping("/genres/{id}")
    void deleteGenre(@PathVariable("id") Long id) {
        genreRepository.deleteById(id);
    }


    // update or create new genre
    @PutMapping("/genres/{id}")
    Genre replaceGenre(@RequestBody Genre newGenre, @PathVariable Long id) {
    return genreRepository.findById(id)
      .map(genre -> {
        genre.setName(newGenre.getName());
        return genreRepository.save(genre);
      })
      .orElseGet(() -> {
        return genreRepository.save(newGenre);
      });
  }
    
}
