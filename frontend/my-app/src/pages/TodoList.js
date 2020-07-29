import React from "react";
import ListTemplate from "../components/Template/Template";
import List from "../components/List/List";
import Form from "../components/Form/Form";
import { ListProvider } from "../ListContext";
import Foot from "../components/Foot/Foot";

function TodoList() {
  return (
    <div>
      <ListProvider>
        <ListTemplate>
          <Form />
          <List />
          <Foot />
        </ListTemplate>
      </ListProvider>
    </div>
  );
}

export default TodoList;
