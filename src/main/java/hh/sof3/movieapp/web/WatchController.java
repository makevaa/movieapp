package hh.sof3.movieapp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import hh.sof3.movieapp.domain.Movie;
//import hh.sof3.movieapp.domain.Movie;
import hh.sof3.movieapp.domain.MovieRepository;
import hh.sof3.movieapp.domain.Watch;
import hh.sof3.movieapp.domain.WatchRepository;

@CrossOrigin
@Controller
public class WatchController {

    @Autowired
	private WatchRepository watchRepository; 

    @Autowired
    private MovieRepository movieRepository; 

    // Show all watches
    @RequestMapping(value="/watchlist", method=RequestMethod.GET)
    public String showWatchList(Model model) {
        model.addAttribute( "watches", watchRepository.findAll() ); 
        return "watchlist";
    }

    // Show "Add watch" page
    @RequestMapping(value="/addwatch", method=RequestMethod.GET)
    public String showAddWatch(Model model) {
        model.addAttribute("watch", new Watch());
        model.addAttribute("movies", movieRepository.findAll());
        return "addwatch";
    }

    // Show "Edit watch" page
    @RequestMapping(value="watches/{id}/edit", method=RequestMethod.GET)
    public String showEditPage(@PathVariable("id") Long watchId, Model model) {
        Watch watch = watchRepository.findById(watchId).get();
        model.addAttribute("watch", watch);
        model.addAttribute("movies", movieRepository.findAll());
        return "editwatch";
    }

    // Save new watch (or update if Watch has id)
    @RequestMapping(value="/savewatch", method=RequestMethod.POST)
    public String save(Watch watch) {
        watchRepository.save(watch);
        return "redirect:watchlist";
    }


    // Delete watch
    @RequestMapping(value="watches/{id}/delete", method=RequestMethod.GET)
    public String DeleteWatchById(@PathVariable("id") Long watchId, Model model) {
        watchRepository.deleteById(watchId);
        return "redirect:../../watchlist";
    }

}
