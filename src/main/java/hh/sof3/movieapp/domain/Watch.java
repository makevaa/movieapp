package hh.sof3.movieapp.domain;



import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "watch") 
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "watchid")
    private Long watchid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Select date")
    private Date date;
    
    private String comment;

    // One Watch relates to one Movie
    // Movie can have many Watches (watched multiple times)

    @ManyToOne
    @JsonIgnoreProperties ("watches") 
    @JoinColumn(name = "movieid")
    private Movie movie;
    

    public Watch() {
        super();
        this.date = null;
        this.comment = null;
    }

    public Watch(Date date, Movie movie, String comment) {
        super();
        this.date = date;
        this.movie = movie;
        this.comment = comment;
    }

    public Long getWatchid() {
        return watchid;
    }

    public void setWatchid(Long watchid) {
        this.watchid = watchid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "Watch [watchid=" + watchid + ", date=" + date + ", comment=" + comment + "]";
    }






}
