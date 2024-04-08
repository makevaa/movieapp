package hh.sof3.movieapp.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface WatchRepository extends CrudRepository<Watch, Long> {
    //List<Watch> findByTitle(String title);
}
