import React from "react";
import ListTemplate from "../components/Template/Template";
import { ListProvider } from "../ListContext";

function Login() {
  return (
    <ListProvider>
      <ListTemplate>
        <div>로그인 페이지</div>
      </ListTemplate>
    </ListProvider>
  );
}

export default Login;
