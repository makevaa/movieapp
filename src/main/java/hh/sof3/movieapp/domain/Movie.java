package hh.sof3.movieapp.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "movie") 
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "movieid")
    private Long movieid;

    @NotBlank(message = "Enter movie title")
    private String title;


    //@Min(value=4, message = "Enter year in YYYY format")
    //@Min(4)
    //@Max(4)
    @Column (name = "releaseYear")
    private Integer releaseYear;
    


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "movie")
    @JsonIgnoreProperties("movie") 
    private List<Watch> watches;
    

    public Movie() {
        super();
        this.title = null;
        this.releaseYear = null;
    }

    public Movie(String title, Integer releaseYear) {
        super();
        this.title = title;
        this.releaseYear = releaseYear;
    }


    public Long getMovieid() {
        return movieid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Watch> getWatches() {
        return watches;
    }

    public void setWatches(List<Watch> watches) {
        this.watches = watches;
    }

    @Override
    public String toString() {
        return "Movie [movieid=" + movieid + ", title=" + title + ", releaseYear=" + releaseYear + "]";
    }


}
