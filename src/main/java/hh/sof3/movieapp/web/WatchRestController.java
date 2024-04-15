package hh.sof3.movieapp.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.sof3.movieapp.domain.Watch;
import hh.sof3.movieapp.domain.WatchRepository;

@CrossOrigin
@Controller
public class WatchRestController {

    @Autowired
	private WatchRepository watchRepository; 

    // RESTful get all watches
    @RequestMapping(value="/watches", method = RequestMethod.GET)
    public @ResponseBody List<Watch> watchListRest() {	
        return (List<Watch>) watchRepository.findAll();
    }    

    // RESTful get watch by id
    @RequestMapping(value="/watches/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Watch> findWatchRest(@PathVariable("id") Long watchId) {	
    	return watchRepository.findById(watchId);
    }    

    // RESTful delete watch by id
    @DeleteMapping("/watches/{id}")
    void deleteWatch(@PathVariable("id") Long watchId) {
        watchRepository.deleteById(watchId);
    }

    // update or create new watch
    @PutMapping("/watches/{id}")
    Watch replaceWatch(@RequestBody Watch newWatch, @PathVariable Long id) {
    return watchRepository.findById(id)
      .map(watch -> {
        watch.setDate(newWatch.getDate());
        watch.setComment(newWatch.getComment());
        watch.setMovie(newWatch.getMovie());
        return watchRepository.save(watch);
      })
      .orElseGet(() -> {
        return watchRepository.save(newWatch);
      });
  }
    
}
