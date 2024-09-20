import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import {
  createBoard,
  updateBoard,
  getBoardDetail,
} from "../services/boardService";
import styled from "styled-components";

// 스타일 정의
const Container = styled.div`
  background-color: #f0f8ff;
  padding: 20px;
  border-radius: 10px;
  max-width: 800px;
  margin: 20px auto;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
`;

const FormTitle = styled.h1`
  color: #1e90ff;
  text-align: center;
  margin-bottom: 20px;
`;

const Form = styled.form`
  display: flex;
  flex-direction: column;
  gap: 20px;
`;

const Label = styled.label`
  font-size: 1.1rem;
  color: #333;
`;

const Input = styled.input`
  padding: 10px;
  border: 1px solid #1e90ff;
  border-radius: 5px;
  font-size: 1rem;
`;

const Textarea = styled.textarea`
  padding: 10px;
  border: 1px solid #1e90ff;
  border-radius: 5px;
  font-size: 1rem;
  width: 100%; /* 좌우로 넓게 설정 */
  height: 300px; /* 고정된 높이 */
  resize: none; /* 크기 조절 금지 */
`;

const Button = styled.button`
  background-color: ${(props) => (props.danger ? "#ff4d4f" : "#1e90ff")};
  color: white;
  padding: 10px;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: ${(props) => (props.danger ? "#d9363e" : "#104e8b")};
  }
`;

const BackButton = styled(Button)`
  background-color: #808080;

  &:hover {
    background-color: #696969;
  }
`;

const BoardForm = () => {
  const { boardId } = useParams();
  const navigate = useNavigate();
  const { register, handleSubmit, setValue } = useForm();
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    if (boardId) {
      const fetchBoardDetail = async () => {
        try {
          const response = await getBoardDetail(boardId);
          setValue("title", response.title);
          setValue("content", response.content);
          setIsEditing(true);
        } catch (error) {
          console.error("Failed to fetch board detail:", error);
        }
      };
      fetchBoardDetail();
    }
  }, [boardId, setValue]);

  const onSubmit = async (data) => {
    try {
      if (isEditing) {
        await updateBoard(boardId, data);
      } else {
        await createBoard(data);
      }
      navigate("/boards");
    } catch (error) {
      console.error("Failed to save board:", error);
    }
  };

  const handleBack = () => {
    navigate(-1); // 이전 페이지로 이동
  };

  return (
    <Container>
      <BackButton onClick={handleBack}>Back</BackButton>
      <FormTitle>{isEditing ? "Edit Board" : "Create New Board"}</FormTitle>
      <Form onSubmit={handleSubmit(onSubmit)}>
        <Label>
          Title:
          <Input type="text" {...register("title", { required: true })} />
        </Label>
        <Label>
          Content:
          <Textarea {...register("content", { required: true })} />
        </Label>
        <Button type="submit">{isEditing ? "Update" : "Create"}</Button>
      </Form>
    </Container>
  );
};

export default BoardForm;
