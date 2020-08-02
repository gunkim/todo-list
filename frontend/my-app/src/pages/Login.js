import React from "react";
import ListTemplate from "../components/Template/Template";
import LoginForm from "../components/Form/LoginForm";

function Login() {
  return (
    <ListTemplate>
      <h1>로그인</h1>
      <LoginForm />
    </ListTemplate>
  );
}

export default Login;
