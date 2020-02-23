package is.hi.hbv501.videoleiga.videoleiga.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@Entity
public class RentalLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("rentals")
    private User user;

    public Date fromdate;
    public Date todate;

    public RentalLog() {
    }

    public Date getFromdate() {
        return fromdate;
    }

    public void setFromdate(Date fromdate) {
        this.fromdate = fromdate;
    }

    public Date getToDate() {
        return todate;
    }

    public void setToDate(Date todate) {
        this.todate = todate;
    }

    public RentalLog(Movie movie, User user, Date fromdate, Date todate) {
        this.movie = movie;
        this.user = user;
        this.fromdate = fromdate;
        this.todate = todate;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
