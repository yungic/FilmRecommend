package rec.filmrec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rec.filmrec.board.WaitBoard;
import rec.filmrec.board.WaitBoardJdbcRepository;
import rec.filmrec.domain.Movie;
import rec.filmrec.domain.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

//@SpringBootTest
public class WaitBoardRepositoryTest {

//    @Autowired
//    private WaitBoardJdbcRepository waitBoardJdbcRepository;
//
//    @Mock
//    Movie movie = new Movie();
//    User user = new User();
//
//
//    @Test
//    @DisplayName("Repository saved")
//    void saveWaitBoardTest() {
//        //Given
//        movie.setMovieTitle("ABC");
//        movie.setMovieInfo("Test:ABC");
//
//        user.setUserNickname("nick");
//        user.setUserPassword(123);
//
//        WaitBoard waitBoard = new WaitBoard();
//        waitBoard.setMovie(movie);
//        waitBoard.setUser(user);
//        waitBoard.setWbTitle("Rec:ABC");
//        waitBoard.setWbContent("ABC:CON");
//
//        WaitBoard waitBoard1 = new WaitBoard();
//        waitBoard.setMovie(movie);
//        waitBoard.setUser(user);
//        waitBoard.setWbTitle("Rec:ABC1");
//        waitBoard.setWbContent("ABC:CON1");
//
//        WaitBoard resultBoard = waitBoardJdbcRepository.saveWaitBoard(waitBoard);
//        WaitBoard resultBoard1 = waitBoardJdbcRepository.saveWaitBoard(waitBoard1);
//
//        assertEquals(waitBoard.getWbTitle(), resultBoard.getWbTitle());
//        assertEquals(waitBoard1.getWbTitle(), resultBoard1.getWbTitle());
//
//    }
}
