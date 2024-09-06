package woori.petmily_card.service;

import org.springframework.beans.factory.annotation.Autowired;
import woori.petmily_card.repository.BoardRepository;
import woori.petmily_card.repository.CommentRepository;

public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;





}
