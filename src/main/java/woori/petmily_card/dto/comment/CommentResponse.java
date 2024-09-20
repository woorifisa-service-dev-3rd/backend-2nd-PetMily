package woori.petmily_card.dto.comment;

import lombok.Getter;
import woori.petmily_card.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {
    private int commentNo;

    private String memberName;

    private String content;

    private LocalDateTime createdAt;

    private CommentResponse(int commentNo, String memberName, String content, LocalDateTime createdAt) {
        this.commentNo = commentNo;
        this.memberName = memberName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static CommentResponse from(Comment comment){
        return new CommentResponse(comment.getCommentNo(), comment.getMember().getName(),comment.getContent(),comment.getCreatedAt());
    }
}
