package woori.petmily_card.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ErrorCode {
    INVALID_INPUT(BAD_REQUEST, "잘못된 입력 값입니다."),
    CARD_NOT_FOUND(NOT_FOUND, "존재하지 않는 카드입니다."),
    HOSPITAL_NOT_FOUND(NOT_FOUND, "존재하지 않는 병원입니다."),
    ALREADY_EXIST_ID(CONFLICT, "이미 존재하는 아이디입니다."),
    MEMBER_NOT_FOUND(NOT_FOUND, "존재하지 않는 회원입니다."),
    INVALID_PASSWORD(UNAUTHORIZED, "비밀번호가 옳지 않습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
