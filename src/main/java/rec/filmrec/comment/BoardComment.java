package rec.filmrec.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rec.filmrec.board.MovieBoard;
import rec.filmrec.domain.BaseEntity;

@Entity
@Getter
@Setter
@Table(name="board_comment")
public class BoardComment extends BaseEntity {

    @Id
    @Column(name="bc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardCommentId;

    @Column(name = "bc_pass")
    private int boardCommentPassword;

    @Column(name = "bc_title")
    private String boardCommentTitle;

    @Column(name = "bc_content")
    private String boardCommentContent;

    @ManyToOne
    @JoinColumn(name="movie_board_id")
    private MovieBoard bcMovieBoard;
}
