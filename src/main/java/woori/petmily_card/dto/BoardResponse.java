package woori.petmily_card.dto;

import lombok.Getter;
import woori.petmily_card.entity.Board;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private int boardNo;
    private String member;
    private String title;
    private LocalDateTime createdAt;

    private BoardResponse(int boardNo, String member, String title, LocalDateTime createdAt) {
        this.boardNo = boardNo;
        this.member = member;
        this.title = title;
        this.createdAt = createdAt;
    }

    public static BoardResponse from(Board board){
        return new BoardResponse(board.getBoardNo(), board.getMember().getName(),
                board.getTitle(), board.getCreatedAt());
    }
}
