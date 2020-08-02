import React, { useState } from "react";
import styles from "./InputForm.module.scss";
import { useDispatch } from "react-redux";

function Form() {
  const dispatch = useDispatch();

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
      text: value,
    });
    setValue("");
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
