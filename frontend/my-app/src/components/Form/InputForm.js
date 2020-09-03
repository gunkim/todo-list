import React, { useState } from "react";
import { createTodo } from "../../modules/todos";
import { useDispatch } from "react-redux";
import Input from "../Input/DefaultInput";

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
    <form onSubmit={onSubmit}>
      <Input
        type="text"
        value={value}
        onChange={onChange}
        placeholder="할 일을 입력하세요."
      />
    </form>
  );
});

export default Form;
