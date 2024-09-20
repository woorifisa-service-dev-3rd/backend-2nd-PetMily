import React from "react";
import { Routes, Route } from "react-router-dom";
import BoardList from "./pages/BoardList";
import BoardDetail from "./pages/BoardDetail";
import BoardForm from "./pages/BoardForm";
import NotFound from "./pages/NotFound";
import Home from "./pages/Home";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/boards" element={<BoardList />} />
      <Route path="/boards/:boardId" element={<BoardDetail />} />
      <Route path="/boards/:boardId/edit" element={<BoardForm />} />
      <Route path="/boards/new" element={<BoardForm />} />
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
};

export default App;
