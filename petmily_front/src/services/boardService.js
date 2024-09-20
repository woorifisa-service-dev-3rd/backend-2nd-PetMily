// src/services/boardService.js
import axios from "axios";

const BASE_URL = "http://localhost:8080/boards";

export const getBoards = async (page) => {
  try {
    const response = await axios.get(`${BASE_URL}?page=${page}`);
    //console.log("보드서비스에서 부른 response", response);
    return response;
  } catch (error) {
    console.error("Error fetching boards:", error);
    throw error;
  }
};

export const getBoardDetail = async (boardId) => {
  try {
    const response = await axios.get(`${BASE_URL}/${boardId}`);
    console.log("resp", response);
    return response.data;
  } catch (error) {
    console.error("Error fetching board detail:", error);
    throw error;
  }
};

export const createBoard = async (boardData) => {
  try {
    // memberNo를 1로 설정하여 데이터 준비
    const dataToSend = {
      title: boardData.title, // title
      content: boardData.content, // content
      memberNo: "1", // memberNo를 1로 설정
    };

    const response = await axios.post(`${BASE_URL}`, dataToSend);
    return response.data;
  } catch (error) {
    console.error("Error creating board:", error);
    throw error;
  }
};

export const updateBoard = async (boardNo, boardData) => {
  try {
    await axios.post(`${BASE_URL}/${boardNo}`, boardData);
  } catch (error) {
    console.error("Error updating board:", error);
    throw error;
  }
};

export const deleteBoard = async (boardNo) => {
  try {
    await axios.delete(`${BASE_URL}/${boardNo}`);
  } catch (error) {
    console.error("Error deleting board:", error);
    throw error;
  }
};
