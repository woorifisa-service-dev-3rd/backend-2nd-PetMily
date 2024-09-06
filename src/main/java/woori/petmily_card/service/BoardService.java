package woori.petmily_card.service;

import woori.petmily_card.entity.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {

    Board save(Board board);

    List<Board> findAll();

    Optional<Board> findById(int id);

    void deleteById(int boardId);











}
