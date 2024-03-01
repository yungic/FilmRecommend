package rec.filmrec.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rec.filmrec.exception.ExceptionCode;
import rec.filmrec.exception.ServiceLogicException;

import java.util.List;
@Service
public class WaitBoardService {

    private final WaitBoardJdbcRepository waitBoardJdbcRepository;

    @Autowired
    public WaitBoardService(WaitBoardJdbcRepository waitBoardJdbcRepository) {
        this.waitBoardJdbcRepository = waitBoardJdbcRepository;
    }

    public List<WaitBoard> findAllWb() {
        return waitBoardJdbcRepository.findAll();
    }

    public WaitBoard findWbById(long wbId) {
        return waitBoardJdbcRepository.findByWbId(wbId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }
    // 이게 문제네.
    public List<WaitBoard> findWbByMovie(long movieId) {
        return waitBoardJdbcRepository.findByMovieId(movieId);
    }

    public WaitBoard saveWb(long movieId, WaitBoard savedWaitBoard) {
         return waitBoardJdbcRepository.saveWaitBoard(movieId, savedWaitBoard);
    }

    public WaitBoard updateWaitBoard(WaitBoard updatedWaitBoard) {
        return waitBoardJdbcRepository.updateWaitBoard(updatedWaitBoard);
    }

    public void deleteWaitBoard(long wbId) {
        waitBoardJdbcRepository.deleteWaitBoard(wbId);
    }
}
