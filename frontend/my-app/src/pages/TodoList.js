import React, { Fragment } from "react";
import ListTemplate from "../components/Template/Template";
import List from "../components/List/List";
import InputForm from "../components/Form/InputForm";
import Foot from "../components/Foot/Foot";

function TodoList() {
  return (
    <Fragment>
      <ListTemplate>
        <InputForm />
        <List />
        <Foot />
      </ListTemplate>
    </Fragment>
  );
}

export default TodoList;
