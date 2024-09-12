package woori.petmily_card.dto;

import lombok.Getter;
import woori.petmily_card.entity.Board;
import woori.petmily_card.entity.Member;

@Getter
public class BoardRequest {
    private int memberNo;
    private String title;
    private String content;

    public BoardRequest(int memberNo, String title, String content) {
        this.memberNo = memberNo;
        this.title = title;
        this.content = content;
    }

    public Board toBoard(Member member) {
        return Board.builder().member(member).title(title).content(content).build();
    }
}
