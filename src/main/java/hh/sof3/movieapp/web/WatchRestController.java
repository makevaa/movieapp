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

import hh.sof3.movieapp.domain.Watch;
import hh.sof3.movieapp.domain.WatchRepository;

@CrossOrigin
@Controller
public class WatchRestController {

    @Autowired
	private WatchRepository watchRepository; 

    // RESTful
    @RequestMapping(value="/watches", method = RequestMethod.GET)
    public @ResponseBody List<Watch> watchListRest() {	
        return (List<Watch>) watchRepository.findAll();
    }    

    // RESTful
    @RequestMapping(value="/watches/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Watch> findWatchRest(@PathVariable("id") Long watchId) {	
    	return watchRepository.findById(watchId);
    }    
}
