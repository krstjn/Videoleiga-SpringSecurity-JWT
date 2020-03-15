package is.hi.hbv501.videoleiga.videoleiga.Entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private Double rating;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime lastModified;

    @ElementCollection(targetClass=Genre.class)
    @Column(name="genre", nullable=false)
    @CollectionTable(name="movie_genres", joinColumns= {@JoinColumn(name="movie_id")})
    public Set<Genre> genres;

    @OneToMany(mappedBy = "movie")
    private List<RentalLog> rentals = new ArrayList<>();

    public Movie() {
    }

    public Movie(String title, String description, Double rating, HashSet<Genre> genres) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.genres = genres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreated(){ return created; }
    public LocalDateTime getLastModified() { return lastModified; }

    @Override
    public String toString() {
        return this.title;
    }
}
