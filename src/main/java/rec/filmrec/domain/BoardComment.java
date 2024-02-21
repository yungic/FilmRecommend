package rec.filmrec.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment")

public class BoardComment extends Comment {
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private MovieBoard movieBoard;

    public BoardComment(Long id, int password, String content) {
        super(id, password, content);
    }
}
