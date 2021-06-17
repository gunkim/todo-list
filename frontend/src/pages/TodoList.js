import React from "react";
import FootContainer from "../container/FootContainer";
import Template from "../components/Template";
import ListContainer from "../container/ListContainer";
import InputFormContainer from "../container/InputFormContainer";

const TodoList = () => {
  return (
    <>
      <Template form={<InputFormContainer />} foot={<FootContainer />}>
        <ListContainer />
      </Template>
    </>
  );
};

export default TodoList;
