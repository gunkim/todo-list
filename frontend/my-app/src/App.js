import React from "react";
import { Route } from "react-router-dom";
import TodoList from "./pages/TodoList";
import Login from "./pages/Login";

function App() {
  return (
    <div>
      <Route path="/" exact component={Login} />
      <Route path="/list" component={TodoList} />
    </div>
  );
}

export default App;
