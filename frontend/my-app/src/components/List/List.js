import React, { useEffect } from "react";
import Item from "../Item/Item";
import { useSelector, useDispatch } from "react-redux";
import { getTodos } from "../../modules/todos";

function List() {
  const { loading, error, data: todoList } = useSelector(
    (state) => state.todos
  );
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getTodos());
  }, [dispatch]);

  if (loading) {
    return <div>목록을 불러오는 중...</div>;
  }
  if (error) {
    return <div>오류입니다... 잠시 후 다시시도해주세요.</div>;
  }
  return (
    <div>
      {todoList.map((todo) => (
        <Item key={todo.id} id={todo.id} text={todo.text} check={todo.check} />
      ))}
    </div>
  );
}

export default List;
