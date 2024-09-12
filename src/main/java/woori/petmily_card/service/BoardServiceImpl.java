package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.BoardDetailResponse;
import woori.petmily_card.dto.BoardPageResponse;
import woori.petmily_card.dto.BoardResponse;
import woori.petmily_card.entity.Board;
import woori.petmily_card.repository.BoardRepository;
import woori.petmily_card.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // 의존성 자동주입 때문에(private final)인것들 자동 주입 가능
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final int SIZE = 5; // 페이지당 5개 만 나오게

    @Override
    public BoardPageResponse showAll(int page) {
        // if(page <= 0) 예외처리
        Page<Board> boards = getBoards(page - 1);
        // if(page > boards.getTotalPages()) 예외처리
        return BoardPageResponse.from(boards);
    }

    private Page<Board> getBoards(int page) {
        Pageable pageable = PageRequest.of(page, SIZE);
        return boardRepository.findAll(pageable);
    }

    @Override
    public BoardDetailResponse show(int boardNo) {
        return BoardDetailResponse.from(getBoard(boardNo));
    }

    private Board getBoard(int boardNo) {
        return boardRepository.findById(boardNo).orElseThrow(() ->
                new RuntimeException("boardNo 에 해당하는 board 가 존재하지 않습니다."));
    }

//    @Override
//    public Board save(Board board) {
//        return boardRepository.save(board);
//    }


//    private Optional<Board> findById(int id) {
//        return boardRepository.findById(id);
//    }

//    @Override
//    public void deleteById(int id) {
//        boardRepository.deleteById(id);
//    }


//

//



}
