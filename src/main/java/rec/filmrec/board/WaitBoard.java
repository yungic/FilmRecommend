package rec.filmrec.board;

import jakarta.persistence.*;
import lombok.*;
import rec.filmrec.domain.BaseEntity;
import rec.filmrec.domain.Movie;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "wait_board")
public class WaitBoard extends BaseEntity {

    @Id
    @JoinColumn(name = "wb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long wbId;

    @Column(name = "wb_title")
    private String wbTitle;

    @Column(name = "wb_content")
    private String wbContent;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name="mb_id")
    private MovieBoard movieBoard;

    WaitBoard(String wbTitle, String wbContent) {
        builder().wbTitle(wbTitle)
                .wbContent(wbContent)
                .build();
    }
}
