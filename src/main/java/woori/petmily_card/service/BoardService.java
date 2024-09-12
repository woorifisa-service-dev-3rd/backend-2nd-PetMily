package woori.petmily_card.service;

import org.springframework.data.domain.Page;
import woori.petmily_card.dto.BoardDetailResponse;
import woori.petmily_card.dto.BoardPageResponse;
import woori.petmily_card.dto.BoardResponse;
import woori.petmily_card.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

//    Board save(Board board);

//    void deleteById(int boardId);

    BoardPageResponse showAll(int page);

    BoardDetailResponse show(int boardNo);










}
