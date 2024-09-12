package woori.petmily_card.dto.board;

import lombok.Getter;
import woori.petmily_card.dto.comment.CommentResponse;
import woori.petmily_card.entity.Board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardDetailResponse {
    private String title;
    private BoardResponse board;
    private String content;
    private List<CommentResponse> comments;
    private LocalDateTime updatedAt;

    private BoardDetailResponse(String title, BoardResponse board, String content, List<CommentResponse> comments, LocalDateTime updatedAt) {
        this.title = title;
        this.board = board;
        this.content = content;
        this.comments = comments;
        this.updatedAt = updatedAt;
    }

    public static BoardDetailResponse from(Board board) {
        return new BoardDetailResponse(board.getTitle(), BoardResponse.from(board), board.getContent(),
                board.getComments().stream().map(CommentResponse::from).collect(Collectors.toList()), board.getUpdatedAt());
    }
}
