package rec.filmrec.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rec.filmrec.exception.ExceptionCode;
import rec.filmrec.exception.ServiceLogicException;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovie() {
        List<Movie> movies = movieRepository.findAll();
        return movies;
    }

    public Movie getMovieById(Long movieId) {
        Movie foundMovie = movieRepository.findById(movieId).orElseThrow(
                () -> new ServiceLogicException(ExceptionCode.MOVIE_NOT_FOUND));

        return foundMovie;
    }
}
