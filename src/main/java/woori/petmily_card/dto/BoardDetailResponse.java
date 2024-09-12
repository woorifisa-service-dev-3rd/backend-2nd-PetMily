package woori.petmily_card.dto;

import lombok.Getter;
import woori.petmily_card.entity.Board;

import java.time.LocalDateTime;

@Getter
public class BoardDetailResponse {
    private BoardResponse board;
    private String content;
    private LocalDateTime updatedAt;
    // comment 추후 추가

    private BoardDetailResponse(BoardResponse board, String content, LocalDateTime updatedAt) {
        this.board = board;
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public static BoardDetailResponse from(Board board) {
        return new BoardDetailResponse(BoardResponse.from(board), board.getContent(), board.getUpdatedAt());
    }
}
