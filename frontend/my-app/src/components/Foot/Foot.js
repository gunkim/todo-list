import React from "react";
import { useSelector } from "react-redux";
import styled from "styled-components";

const Main = styled.div`
  padding-top: 10px;
  font-size: 1.2rem;
`;
const NumMsg = styled.span`
  color: red;
`;

function Foot() {
  const todoList = useSelector((state) => state.todos.data);
  const beforeTodo = todoList.filter((todo) => !todo.check);
  return (
    <Main>
      남은 할 일<NumMsg>{beforeTodo.length}</NumMsg>
    </Main>
  );
}
export default Foot;
