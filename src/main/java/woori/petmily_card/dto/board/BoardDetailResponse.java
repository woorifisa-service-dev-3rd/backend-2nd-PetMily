package woori.petmily_card.dto.board;

import lombok.Getter;
import woori.petmily_card.dto.comment.CommentResponse;
import woori.petmily_card.entity.Board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardDetailResponse {
    private BoardResponse board;
    private String content;
    private List<CommentResponse> comments;
    private LocalDateTime updatedAt;

    private BoardDetailResponse(BoardResponse board, String content, List<CommentResponse> comments, LocalDateTime updatedAt) {
        this.board = board;
        this.content = content;
        this.comments = comments;
        this.updatedAt = updatedAt;
    }

    public static BoardDetailResponse from(Board board) {
        return new BoardDetailResponse(BoardResponse.from(board), board.getContent(),
                board.getComments().stream().map(CommentResponse::from).collect(Collectors.toList()), board.getUpdatedAt());
    }
}
