import React from "react";
import { Link } from "react-router-dom";
import styled from "styled-components";

// 스타일 정의
const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
  background-color: #f0f8ff;
`;

const Title = styled.h1`
  color: #1e90ff;
  font-size: 3rem;
  margin-bottom: 20px;
`;

const Description = styled.p`
  font-size: 1.2rem;
  color: #333;
  margin-bottom: 30px;
`;

const StyledLink = styled(Link)`
  padding: 10px 20px;
  background-color: #1e90ff;
  color: #ffffff;
  text-decoration: none;
  border-radius: 5px;
  font-weight: bold;
  transition: background-color 0.3s ease;

  &:hover {
    background-color: #104e8b;
  }
`;

const Home = () => {
  return (
    <Container>
      <Title>PetMily</Title>
      <Description>Welcome to PetMily</Description>
      <StyledLink to="/boards">Board List</StyledLink>
    </Container>
  );
};

export default Home;
