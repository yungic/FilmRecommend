package rec.filmrec.exception;

import lombok.Getter;

public class ServiceLogicException extends RuntimeException {

    @Getter
    private final ExceptionCode boardExceptionCode;

    public ServiceLogicException(ExceptionCode boardExceptionCode) {
        super(boardExceptionCode.getMessage());
        this.boardExceptionCode = boardExceptionCode;
    }

}