import React from "react";
import styled from "styled-components";

const Main = styled.div`
  width: 50%;
  text-align: center;
  border-radius: 5px;
  margin: 0 auto;
  box-shadow: 1px 1px 35px -20px;
  background: white;
  padding-bottom: 30px;
  margin-top: 50px;
  margin-bottom: 50px;
  padding-top: 10px;
`;

const TodoListTemplate = ({ form, children, foot }) => {
  return (
    <Main>
      {form}
      {children}
      {foot}
    </Main>
  );
};

export default TodoListTemplate;
