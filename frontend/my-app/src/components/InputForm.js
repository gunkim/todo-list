import React from "react";
import DefaultInput from "../components/DefaultInput";

const InputForm = ({ value, onChange, onSubmit }) => {
  return (
    <form onSubmit={onSubmit}>
      <DefaultInput
        type="text"
        value={value}
        onChange={onChange}
        placeholder="할 일을 입력하세요."
      />
    </form>
  );
};

export default InputForm;
