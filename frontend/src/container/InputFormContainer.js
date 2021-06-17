import React, { memo, useState } from "react";
import { createTodo } from "../modules/todos";
import { useDispatch } from "react-redux";
import InputForm from "../components/InputForm";

const InputFormContainer = memo(() => {
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

  return <InputForm value={value} onChange={onChange} onSubmit={onSubmit} />;
});

export default InputFormContainer;
