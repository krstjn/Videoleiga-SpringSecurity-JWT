package is.hi.hbv501.videoleiga.videoleiga.Controllers;

import is.hi.hbv501.videoleiga.videoleiga.Entities.Genre;
import is.hi.hbv501.videoleiga.videoleiga.Entities.Movie;
import is.hi.hbv501.videoleiga.videoleiga.Entities.RentalLog;
import is.hi.hbv501.videoleiga.videoleiga.Entities.User;
import is.hi.hbv501.videoleiga.videoleiga.Services.MovieService;
import is.hi.hbv501.videoleiga.videoleiga.Services.RentalLogService;
import is.hi.hbv501.videoleiga.videoleiga.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    private MovieService movieService;
    private RentalLogService rentalLogService;
    private UserService userService;

    @Autowired
    public HomeController(MovieService movieService, RentalLogService rentalLogService, UserService userService) {
        this.rentalLogService = rentalLogService;
        this.userService = userService;
        this.movieService = movieService;
    }

    @RequestMapping("/movies")
    public List<Movie> Home(@RequestParam(value = "search", required = false) String search) {
        if (search != null)
            return movieService.findByTitle(search);
        else
            return movieService.findAll();
    }

    @RequestMapping("/rentals")
    public List<RentalLog> allRentals() { return rentalLogService.findAll(); }

    @RequestMapping("/users")
    public List<User> usersGET() {
        return userService.findAll();
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public Movie addMovie(@Valid @RequestBody Movie movie, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid movie format");
        }
        return movieService.save(movie);
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMovie(@PathVariable("id") long id) {
        Movie movie = movieService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid movie ID"));
        movieService.delete(movie);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping("/makedata")
    public List<Movie> makeData() {
        HashSet<Genre> genres = new HashSet<>();
        genres.add(Genre.ADVENTURE);
        genres.add(Genre.ACTION);
        User tempUser = new User("Karl Jóhann", "pass123");
        this.userService.save(tempUser);

        for (int i = 0; i < 3; i++) {
            this.movieService
                    .save(new Movie("Great movie " + i, "fantastic movie in a trilogy", Double.valueOf(i), genres));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Movie> tempMovie = movieService.findAll();

        try {
            User exists = userService.findByUName("Karl Jóhann");
            this.rentalLogService
                    .save(new RentalLog(tempMovie.get(0), exists, sdf.parse("21/12/2012"), sdf.parse("31/12/2013")));
            this.rentalLogService
                    .save(new RentalLog(tempMovie.get(2), exists, sdf.parse("21/12/2015"), sdf.parse("31/12/2015")));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tempMovie;
    }

    @RequestMapping(value = "/loggedIn", method = RequestMethod.GET)
    public User loggedInGET(Authentication authentication) {
        return userService.findByUName(authentication.getName());
    }
}