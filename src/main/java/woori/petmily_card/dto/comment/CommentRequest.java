package woori.petmily_card.dto.comment;

import lombok.Getter;
import woori.petmily_card.entity.Board;
import woori.petmily_card.entity.Comment;
import woori.petmily_card.entity.Member;

@Getter
public class CommentRequest {
    private int memberNo;
    private int boardNo;
    private String content;

    public CommentRequest(int memberNo, int boardNo, String content) {
        this.memberNo =memberNo;
        this.boardNo = boardNo;
        this.content = content;
    }

    public Comment toComment(Member member, Board board) {
        return Comment.builder().member(member).board(board).content(content).build();
    }
}
