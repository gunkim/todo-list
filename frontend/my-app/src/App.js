import React, { useState } from "react";
import ListTemplate from "./components/ListTemplate/ListTemplate";
import List from "./components/List/List";
import Form from "./components/Form/Form";

function App() {
  const [todoList, setTodoList] = useState([
    {
      id: 1,
      check: false,
      text: "리액트 공부하기",
    },
    {
      id: 2,
      check: true,
      text: "리액트 안녕",
    },
    {
      id: 3,
      check: true,
      text: "리액트 수고",
    },
    {
      id: 4,
      check: false,
      text: "리액트 잘 있어",
    },
  ]);
  return (
    <ListTemplate>
      <Form />
      <List todoList={todoList} />
    </ListTemplate>
  );
}

export default App;
