package hh.sof3.movieapp.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "genre") 
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "genreid")
    private Long genreid;

    @NotBlank(message = "Enter genre name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> movies;

    public Genre() {
        super();
        this.name = null;
    }

    public Genre(String name) {
        super();
        this.name = name;
    }

    public Long getGenreid() {
        return genreid;
    }

    public void setGenreid(Long genreid) {
        this.genreid = genreid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }



    @Override
    public String toString() {
        return "Genre [genreid=" + genreid + ", name=" + name + "]";
    }

}
