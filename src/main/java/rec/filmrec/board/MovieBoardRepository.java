package rec.filmrec.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieBoardRepository extends JpaRepository<MovieBoard, Long> {

}
