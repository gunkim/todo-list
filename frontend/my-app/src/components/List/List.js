import React from "react";
import Item from "../Item/Item";
import { useListState } from "../../ListContext";

function List() {
  const todoList = useListState();
  return (
    <div>
      {todoList.map((todo) => (
        <Item key={todo.id} id={todo.id} text={todo.text} check={todo.check} />
      ))}
    </div>
  );
}

export default List;
