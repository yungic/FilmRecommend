package rec.filmrec.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaitBoardService {

    private final WaitBoardJdbcRepository waitBoardJdbcRepository;

    @Autowired
    public WaitBoardService(WaitBoardJdbcRepository waitBoardJdbcRepository) {
        this.waitBoardJdbcRepository = waitBoardJdbcRepository;
    }

    public List<WaitBoard> findAllWaitBoard() {
        return waitBoardJdbcRepository.findAllWaitBoard();
    }

    public Optional<WaitBoard> findWbById(long wbId) {
        return waitBoardJdbcRepository.findWaitBoardByWbId(wbId);
    }

    public List<WaitBoard> findWbWithMovie(long movieId) {
        return waitBoardJdbcRepository.findWaitBoardWithMovie(movieId);
    }

    public List<WaitBoard> findWbWithUser(long userId) {
        return waitBoardJdbcRepository.findWaitBoardWithUser(userId);
    }

    public WaitBoard saveWb(WaitBoard savedWaitBoard) {
        return waitBoardJdbcRepository.saveWaitBoard(savedWaitBoard);
    }

    public void updateWaitBoard(WaitBoard updatedWaitBoard) {
        waitBoardJdbcRepository.updateWaitBoard(updatedWaitBoard);
    }

    public void deleteWaitBoard(long wbId) {
        waitBoardJdbcRepository.deleteWaitBoard(wbId);
    }
}
