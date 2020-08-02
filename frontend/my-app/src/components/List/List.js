import React from "react";
import Item from "../Item/Item";
import { useSelector } from "react-redux";

function List() {
  const todoList = useSelector((state) => state.todos);
  return (
    <div>
      {todoList.map((todo) => (
        <Item key={todo.id} id={todo.id} text={todo.text} check={todo.check} />
      ))}
    </div>
  );
}

export default List;
