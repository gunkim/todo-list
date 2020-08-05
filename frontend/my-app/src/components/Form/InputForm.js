import React, { useState } from "react";
import styles from "./InputForm.module.scss";
import { createTodo } from "../../modules/todos";
import { useDispatch } from "react-redux";

const Form = React.memo(() => {
  const [value, setValue] = useState("");
  const dispatch = useDispatch();
  const onChange = (e) => {
    setValue(e.target.value);
  };
  const onSubmit = (e) => {
    e.preventDefault();

    if (value.trim() === "") {
      return;
    }
    dispatch(createTodo(value));
    setValue("");
  };

  return (
    <div className={styles.main}>
      <form onSubmit={onSubmit}>
        <input
          type="text"
          value={value}
          onChange={onChange}
          placeholder="할 일을 입력하세요."
        />
      </form>
    </div>
  );
});

export default Form;
