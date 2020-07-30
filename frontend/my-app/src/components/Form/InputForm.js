import React, { useState } from "react";
import styles from "./InputForm.module.scss";
import { useListDispatch, useListNextId } from "../../ListContext";

function Form() {
  const dispatch = useListDispatch();
  const nextId = useListNextId();

  const [value, setValue] = useState("");
  const onChange = (e) => {
    setValue(e.target.value);
  };
  const onSubmit = (e) => {
    e.preventDefault();

    if (value.trim() === "") {
      return;
    }

    dispatch({
      type: "CREATE_TODO",
      id: nextId.current,
      text: value,
    });
    setValue("");
    nextId.current += 1;
  };

  return (
    <div className={styles.main}>
      <form onSubmit={onSubmit}>
        <input type="text" value={value} onChange={onChange} />
      </form>
    </div>
  );
}

export default Form;
