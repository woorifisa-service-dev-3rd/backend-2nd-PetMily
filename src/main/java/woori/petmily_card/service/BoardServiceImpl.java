package woori.petmily_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woori.petmily_card.entity.Board;
import woori.petmily_card.repository.BoardRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Board save(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public Optional<Board> findById(int id) {
        return boardRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        boardRepository.deleteById(id);
    }
}
