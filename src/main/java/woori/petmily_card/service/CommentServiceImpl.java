package woori.petmily_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import woori.petmily_card.repository.BoardRepository;
import woori.petmily_card.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void addComment(int boardId, String content) {

    }

    @Override
    public void deleteCommentByBoardId(int boardId) {

    }
}
