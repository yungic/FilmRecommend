package rec.filmrec.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rec.filmrec.domain.Movie;
import rec.filmrec.exception.ExceptionCode;
import rec.filmrec.exception.ServiceLogicException;

@Service
public class MovieBoardService {

    private final MovieBoardRepository movieBoardRepository;

    @Autowired
    MovieBoardService(MovieBoardRepository movieBoardRepository) {
        this.movieBoardRepository = movieBoardRepository;
    }

    public List<MovieBoard> getAllMb() {
        return movieBoardRepository.findAll();
    }

    public MovieBoard getByMbId(long mbId) {
        return movieBoardRepository.findById(mbId)
                .orElseThrow(() -> new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    public void editMbById(MovieBoard editedMb) {
        MovieBoard movieBoard =
        movieBoardRepository.findById(editedMb.getMbId())
                .orElseThrow(()->new ServiceLogicException(ExceptionCode.BOARD_NOT_FOUND));

        Optional.ofNullable(editedMb.getMbTitle())
                .ifPresent(mbTitle -> movieBoard.setMbTitle(editedMb.getMbTitle()));

        movieBoard.setMbContent(editedMb.getMbContent());

        movieBoardRepository.save(movieBoard);
    }
}
