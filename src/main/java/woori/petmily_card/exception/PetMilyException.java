package woori.petmily_card.exception;

public class PetMilyException extends RuntimeException {
    private final ErrorCode errorCode;

    public PetMilyException(final ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
