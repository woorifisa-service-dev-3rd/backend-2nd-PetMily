package woori.petmily_card.dto;

import lombok.Getter;
import woori.petmily_card.entity.Board;

@Getter
public class BoardDetailResponse {
    private BoardResponse board;
    private String content;
    // comment 추후 추가

    private BoardDetailResponse(BoardResponse board, String content) {
        this.board = board;
        this.content = content;
    }

    public static BoardDetailResponse from(Board board) {
        return new BoardDetailResponse(BoardResponse.from(board), board.getContent());
    }
}
