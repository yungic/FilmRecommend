package rec.filmrec.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import rec.filmrec.domain.Movie;
import rec.filmrec.domain.User;

@Entity
@Getter
@Setter
@Table(name = "wait_board")
public class WaitBoard {

    @Id
    @Column(name = "wb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wbId;

    @Column(name = "wb_pass")
    private int wbPassword;

    @Column(name = "wb_title")
    private String wbTitle;

    @Column(name = "wb_content")
    private String wbContent;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="mb_id")
    private MovieBoard movieBoard;
}
