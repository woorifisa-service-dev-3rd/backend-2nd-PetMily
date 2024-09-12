package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.BoardResponse;
import woori.petmily_card.entity.Board;
import woori.petmily_card.repository.BoardRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 의존성 자동주입 때문에(private final)인것들 자동 주입 가능
@Transactional
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;
    private final int SIZE = 5; // 페이지당 5개 만 나오게

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

    @Override
    public Page<BoardResponse> showAll(int page) {
        Page<Board> boards = getBoards(page);
        return boards.map(BoardResponse::from);
    }

    private Page<Board> getBoards(int page) {
        Pageable pageable = PageRequest.of(page,SIZE);
        return boardRepository.findAll(pageable);
    }

    @Override
    public BoardResponse show(int boardNo) {
        return BoardResponse.from(getBoard(boardNo));
    }

    private Board getBoard(int boardNo) {
        return boardRepository.findById(boardNo).orElseThrow();
    }


}
