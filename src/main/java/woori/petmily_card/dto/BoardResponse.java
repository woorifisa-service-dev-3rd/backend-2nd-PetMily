package woori.petmily_card.dto;

import lombok.Getter;
import woori.petmily_card.entity.Board;
import woori.petmily_card.entity.Comment;
import woori.petmily_card.entity.Member;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponse {

    private int boardNo;
    private Member member;
    private String title;
    private String content;
    private List<Comment> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BoardResponse(int boardNo, Member member, String title,
                         String content,List<Comment> comments,
                         LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.boardNo = boardNo;
        this.member = member;
        this.title = title;
        this.content = content;
        this.comments = comments;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static BoardResponse from(Board board){
        return new BoardResponse(board.getBoardNo(),board.getMember(),
                board.getTitle(), board.getContent(),board.getComments(),
                board.getCreatedAt(),board.getUpdatedAt());
    }
}
