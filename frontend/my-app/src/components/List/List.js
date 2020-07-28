import React from "react";
import Item from "../Item/Item";

function List({ todoList }) {
  return (
    <div>
      {todoList.map((todo) => (
        <Item key={todo.id} text={todo.text} check={todo.check} />
      ))}
    </div>
  );
}

export default List;
