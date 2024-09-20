// src/components/BoardListItem.jsx

import React from "react";
import { Link } from "react-router-dom";
import PropTypes from "prop-types";

/**
 * BoardListItem 컴포넌트는 게시글 목록에서 개별 게시글을 렌더링합니다.
 * @param {Object} props
 * @param {Object} props.board - 게시글 데이터
 * @param {string} props.board.id - 게시글 ID
 * @param {string} props.board.title - 게시글 제목
 * @param {string} props.board.content - 게시글 내용 (짧게 요약)
 */
const BoardListItem = ({ board }) => {
  return (
    <div className="board-list-item">
      <h3>
        <Link to={`/boards/${board.id}`}>{board.title}</Link>
      </h3>
      <p>{board.content.substring(0, 10)}...</p>
    </div>
  );
};

BoardListItem.propTypes = {
  board: PropTypes.shape({
    id: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
    content: PropTypes.string.isRequired,
  }).isRequired,
};

export default BoardListItem;
