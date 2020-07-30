import React from "react";
import ListTemplate from "../components/Template/Template";
import { ListProvider } from "../ListContext";
import LoginForm from "../components/Form/LoginForm";

function Login() {
  return (
    <ListProvider>
      <ListTemplate>
        <h1>로그인</h1>
        <LoginForm />
      </ListTemplate>
    </ListProvider>
  );
}

export default Login;
