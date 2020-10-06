import React, { memo, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { getTodos } from "../modules/todos";
import List from "../components/List";

const ListContainer = memo(() => {
  const { loading, error, data: todoList } = useSelector(
    (state) => state.todos
  );
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getTodos());
  }, [dispatch]);

  return <List list={todoList} loading={loading} error={error} />;
});

export default ListContainer;
