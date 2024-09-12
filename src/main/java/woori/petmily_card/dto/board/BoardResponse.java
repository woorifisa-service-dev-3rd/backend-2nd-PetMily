package woori.petmily_card.dto.board;

import lombok.Getter;
import woori.petmily_card.entity.Board;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private int boardNo;
    private String memberName;
    private String title;
    private LocalDateTime createdAt;

    private BoardResponse(int boardNo, String memberName, String title, LocalDateTime createdAt) {
        this.boardNo = boardNo;
        this.memberName = memberName;
        this.title = title;
        this.createdAt = createdAt;
    }

    public static BoardResponse from(Board board){
        return new BoardResponse(board.getBoardNo(), board.getMember().getName(),
                board.getTitle(), board.getCreatedAt());
    }
}
