import React, { memo } from "react";
import { useSelector } from "react-redux";
import styled from "styled-components";

const Main = styled.div`
  padding-top: 10px;
  font-size: 1.2rem;
`;
const NumMsg = styled.span`
  color: red;
`;

const Foot = memo(() => {
  const todoList = useSelector((state) => state.todos.data);
  const beforeTodos = todoList.filter((todo) => !todo.check);

  return (
    <Main>
      남은 할 일 <NumMsg>{beforeTodos.length}</NumMsg>
    </Main>
  );
});
export default Foot;
