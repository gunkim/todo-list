import React, { memo } from "react";
import { useSelector } from "react-redux";
import Foot from "../components/Foot";

const FootContainer = memo(() => {
  const todoList = useSelector((state) => state.todos.data);
  const beforeTodos = todoList.filter((todo) => !todo.check);

  return <Foot todosCnt={beforeTodos.length} />;
});
export default FootContainer;
