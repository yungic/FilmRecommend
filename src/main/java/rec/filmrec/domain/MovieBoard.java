package rec.filmrec.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "movie_board")
public class MovieBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wait_board_id")
    private Long waitBoardId;

    @OneToMany(mappedBy = "movieBoard", cascade = CascadeType.ALL)
    private List<BoardComment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "movieBoard", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

}
