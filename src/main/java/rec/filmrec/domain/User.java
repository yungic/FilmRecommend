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
@Table(name="user")
public class User extends BaseEntity {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "user_pass")
    private int userPassword;

    @Column(name = "user_nick")
    private String userNickname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<WaitBoard> uWaitBoards = new ArrayList<>();

}
