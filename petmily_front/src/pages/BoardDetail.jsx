import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getBoardDetail, deleteBoard } from "../services/boardService";
import { Link } from "react-router-dom";
import styled from "styled-components";

// 스타일 정의
const Container = styled.div`
  background-color: #f0f8ff;
  padding: 20px;
  border-radius: 10px;
  max-width: 800px;
  margin: 20px auto;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  color: #333;
`;

const Title = styled.h1`
  color: #1e90ff;
  margin-bottom: 10px;
`;

const Content = styled.p`
  font-size: 1.2rem;
  line-height: 1.6;
  margin-bottom: 20px;
  color: #333;
`;

const InfoContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  color: #555;
  font-size: 0.9rem;
`;

const ButtonContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
`;

const StyledButton = styled.button`
  background-color: ${(props) => (props.danger ? "#ff4d4f" : "#1e90ff")};
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: ${(props) => (props.danger ? "#d9363e" : "#104e8b")};
  }
`;

const BackButton = styled(StyledButton)`
  background-color: #808080;
  margin-right: 10px;

  &:hover {
    background-color: #696969;
  }
`;

const EditButton = styled(Link)`
  display: inline-block;
  padding: 10px 20px;
  background-color: #1e90ff;
  color: white;
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #104e8b;
  }
`;

const BoardDetail = () => {
  const { boardId } = useParams();
  const navigate = useNavigate();
  const [board, setBoard] = useState({});

  useEffect(() => {
    const fetchBoardDetail = async () => {
      try {
        const response = await getBoardDetail(boardId);
        setBoard(response);
      } catch (error) {
        console.error("Failed to fetch board detail:", error);
      }
    };
    fetchBoardDetail();
  }, [boardId]);

  const handleDelete = async () => {
    try {
      await deleteBoard(boardId);
      navigate("/boards");
    } catch (error) {
      console.error("Failed to delete board:", error);
    }
  };

  const handleBack = () => {
    navigate(-1);
  };

  if (!board) return <p>Loading...</p>;

  return (
    <Container>
      <BackButton onClick={handleBack}>Back</BackButton>
      <Title>{board.title}</Title>
      <InfoContainer>
        <span>Updated at: {new Date(board.updatedAt).toLocaleString()}</span>
      </InfoContainer>
      <Content>{board.content}</Content>
      <ButtonContainer>
        <StyledButton danger onClick={handleDelete}>
          Delete
        </StyledButton>
        <EditButton to={`/boards/${boardId}/edit`}>Edit</EditButton>
      </ButtonContainer>
    </Container>
  );
};

export default BoardDetail;
