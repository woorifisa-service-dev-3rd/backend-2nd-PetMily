// src/services/commentService.js

import axios from "axios";

// API 엔드포인트
const API_BASE_URL = "http://localhost:8080/comments";

/**
 * 댓글을 추가하는 함수
 * @param {Object} commentData - 댓글 데이터
 * @param {number} commentData.memberNo - 회원 번호
 * @param {number} commentData.boardNo - 게시글 번호
 * @param {string} commentData.content - 댓글 내용
 * @returns {Promise} - Axios Promise
 */
export const createComment = async (commentData) => {
  try {
    const response = await axios.post(API_BASE_URL, commentData);
    return response.data;
  } catch (error) {
    console.error("Error creating comment:", error);
    throw error;
  }
};

/**
 * 댓글을 삭제하는 함수
 * @param {number} commentNo - 댓글 번호
 * @returns {Promise} - Axios Promise
 */
export const deleteComment = async (commentNo) => {
  try {
    await axios.delete(`${API_BASE_URL}/${commentNo}`);
  } catch (error) {
    console.error("Error deleting comment:", error);
    throw error;
  }
};
