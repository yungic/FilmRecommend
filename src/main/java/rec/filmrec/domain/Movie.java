package rec.filmrec.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rec.filmrec.board.WaitBoard;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="movie")
public class Movie {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long movieId;

    @Column(name = "movie_title")
    private String movieTitle;

    @Column(name = "movie_info")
    private String movieInfo;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<WaitBoard> mWaitBoards = new ArrayList<>();

}
