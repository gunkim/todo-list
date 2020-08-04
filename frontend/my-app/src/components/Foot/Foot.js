import React from "react";
import styles from "./Foot.module.scss";
import { useSelector } from "react-redux";

function Foot() {
  const todoList = useSelector((state) => state.todos.data);
  const beforeTodo = todoList.filter((todo) => !todo.check);
  return (
    <div className={styles.main}>
      남은 할 일<span className={styles.todoLength}>{beforeTodo.length}</span>
    </div>
  );
}
export default Foot;
