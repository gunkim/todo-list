import React from "react";
import ListTemplate from "./components/ListTemplate/ListTemplate";
import List from "./components/List/List";
import Form from "./components/Form/Form";
import { ListProvider } from "./ListContext";
import Foot from "./components/Foot/Foot";

function App() {
  return (
    <ListProvider>
      <ListTemplate>
        <Form />
        <List />
        <Foot />
      </ListTemplate>
    </ListProvider>
  );
}

export default App;
