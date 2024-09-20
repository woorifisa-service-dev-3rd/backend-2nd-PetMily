import React, { useState, useEffect } from "react";
import { Link, useLocation } from "react-router-dom";
import { getBoards } from "../services/boardService";
import styled from "styled-components";

// 스타일 정의
const PageWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f0f8ff;
`;

const Container = styled.div`
  background-color: #ffffff;
  padding: 20px;
  border-radius: 10px;
  max-width: 800px;
  width: 100%;
  margin: 0 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  color: #333;
`;

const Title = styled.h1`
  color: #1e90ff;
  text-align: center;
  margin-bottom: 20px;
`;

const BoardListContainer = styled.ul`
  list-style: none;
  padding: 0;
`;

const BoardItem = styled.li`
  background-color: #ffffff;
  padding: 10px;
  margin: 10px 0;
  border: 1px solid #1e90ff;
  border-radius: 8px;
  transition: transform 0.2s ease-in-out;

  &:hover {
    transform: translateY(-5px);
    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  }
`;

const BoardLink = styled(Link)`
  text-decoration: none;
  color: #1e90ff;
  font-weight: bold;

  &:hover {
    color: #104e8b;
  }
`;

const MemberName = styled.span`
  display: block;
  color: #555;
  font-size: 0.9rem;
  margin-top: 5px;
`;

const CreatedAt = styled.span`
  display: block;
  color: #888;
  font-size: 0.9rem;
  margin-top: 5px;
`;

const PaginationContainer = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 20px;
`;

const PaginationLink = styled(Link)`
  margin: 0 10px;
  padding: 5px 10px;
  background-color: ${(props) => (props.disabled ? "#d3d3d3" : "#1e90ff")};
  color: ${(props) => (props.disabled ? "#808080" : "#ffffff")};
  border-radius: 5px;
  text-decoration: none;
  pointer-events: ${(props) => (props.disabled ? "none" : "auto")};
`;

const NewBoardButton = styled(Link)`
  display: block;
  background-color: #1e90ff;
  color: white;
  text-align: center;
  padding: 10px;
  border-radius: 5px;
  text-decoration: none;
  margin-bottom: 20px;
  transition: background-color 0.2s;

  &:hover {
    background-color: #104e8b;
  }
`;

const BoardList = () => {
  const [boards, setBoards] = useState([]);
  const [page, setPage] = useState(1);
  const location = useLocation();

  useEffect(() => {
    const queryParams = new URLSearchParams(location.search);
    const pageNumber = parseInt(queryParams.get("page")) || 1;
    setPage(pageNumber);

    const fetchBoards = async () => {
      try {
        const response = await getBoards(pageNumber);
        setBoards(response.data.boards);
      } catch (error) {
        console.error("Failed to fetch boards:", error);
      }
    };
    fetchBoards();
  }, [location.search]);

  return (
    <PageWrapper>
      <Container>
        <Title>PetMily Board List</Title>
        <NewBoardButton to="/boards/new">Create New Board</NewBoardButton>
        <BoardListContainer>
          {boards.map((board) => (
            <BoardItem key={board.boardNo}>
              <BoardLink to={`/boards/${board.boardNo}`}>
                {board.title}
              </BoardLink>
              <MemberName>Created by: {board.memberName}</MemberName>
              <CreatedAt>
                Created on: {new Date(board.createdAt).toLocaleDateString()}
              </CreatedAt>
            </BoardItem>
          ))}
        </BoardListContainer>
        <PaginationContainer>
          <PaginationLink to={`?page=${page - 1}`} disabled={page <= 1}>
            Previous
          </PaginationLink>
          <PaginationLink to={`?page=${page + 1}`}>Next</PaginationLink>
        </PaginationContainer>
      </Container>
    </PageWrapper>
  );
};

export default BoardList;
