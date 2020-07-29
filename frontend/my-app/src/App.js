import React from "react";
import ListTemplate from "./components/ListTemplate/ListTemplate";
import List from "./components/List/List";
import Form from "./components/Form/Form";
import { ListProvider } from "./ListContext";

function App() {
  return (
    <ListProvider>
      <ListTemplate>
        <Form />
        <List />
      </ListTemplate>
    </ListProvider>
  );
}

export default App;
