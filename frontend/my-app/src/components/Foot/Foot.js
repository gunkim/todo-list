import React from "react";
import styles from "./Foot.module.scss";
import { useListState } from "../../ListContext";

function Foot() {
  const todoList = useListState();
  const beforeTodo = todoList.filter((todo) => !todo.check);
  return (
    <div className={styles.main}>
      남은 할 일<span className={styles.todoLength}>{beforeTodo.length}</span>
    </div>
  );
}
export default Foot;
