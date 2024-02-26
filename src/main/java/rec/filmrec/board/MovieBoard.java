package rec.filmrec.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rec.filmrec.comment.BoardComment;
import rec.filmrec.domain.BaseEntity;
import rec.filmrec.post.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "movie_board")
public class MovieBoard extends BaseEntity {

    @Id
    @Column(name = "mb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mbId;

    @OneToMany(mappedBy = "movieBoard")
    private List<WaitBoard> WaitBoards = new ArrayList<>();

    @OneToMany(mappedBy = "pMovieBoard")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "bcMovieBoard")
    private List<BoardComment> boardComments = new ArrayList<>();
}
