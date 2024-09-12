package woori.petmily_card.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import woori.petmily_card.dto.BoardDetailResponse;
import woori.petmily_card.dto.BoardPageResponse;
import woori.petmily_card.dto.BoardRequest;
import woori.petmily_card.entity.Board;
import woori.petmily_card.entity.Member;
import woori.petmily_card.repository.BoardRepository;
import woori.petmily_card.repository.MemberRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    private final int SIZE = 5;

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

    @Override
    public void save(BoardRequest boardRequest) {
        Board board = boardRequest.toBoard(getMember(boardRequest.getMemberNo()));
        boardRepository.save(board);
    }

    private Member getMember(int memberNo) {
        return memberRepository.findById(memberNo).orElseThrow(() ->
                new RuntimeException("memberNo 에 해당하는 member 가 존재하지 않습니다."));
    }

    @Override
    public void modify(int boardNo, BoardRequest boardRequest) {
        Board board = getBoard(boardNo);
        board.updateBoard(boardRequest);
    }

    @Override
    public void remove(int boardNo) {
        boardRepository.deleteById(boardNo);
    }
}
