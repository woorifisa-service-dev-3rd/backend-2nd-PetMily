package woori.petmily_card.service;

import woori.petmily_card.dto.board.BoardDetailResponse;
import woori.petmily_card.dto.board.BoardPageResponse;
import woori.petmily_card.dto.board.BoardRequest;

public interface BoardService {
    BoardPageResponse showAll(int page);

    BoardDetailResponse show(int boardNo);

    void save(BoardRequest boardRequest);

    void modify(int boardNo, BoardRequest boardRequest);

    void remove(int boardNo);
}
