package woori.petmily_card.dto.board;

import lombok.Getter;
import org.springframework.data.domain.Page;
import woori.petmily_card.entity.Board;

import java.util.List;

@Getter
public class BoardPageResponse {
    private List<BoardResponse> boards;
    private int currentPage;
    private int totalPage;

    private BoardPageResponse(List<BoardResponse> boards, int currentPage, int totalPage) {
        this.boards = boards;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
    }

    public static BoardPageResponse from(Page<Board> boards) {
        return new BoardPageResponse(boards.map(BoardResponse::from).getContent(),
                boards.getNumber() + 1, boards.getTotalPages());
    }
}
