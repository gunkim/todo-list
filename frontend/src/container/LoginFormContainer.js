import React, { useState, memo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { loginPro } from "../modules/users";
import LoginForm from "../components/LoginForm";

const LoginFormContainer = memo(() => {
  const dispatch = useDispatch();
  const [login, setLogin] = useState({
    userId: "",
    password: "",
  });
  const { loading, error } = useSelector((state) => state.users);

  const onChange = (e) => {
    const { name, value } = e.target;

    setLogin({
      ...login,
      [name]: value,
    });
  };
  const onKeyPress = (e) => {
    if (e.key === "Enter") {
      const { userId: id, password: pw } = login;
      dispatch(loginPro(id, pw));
    }
  };
  return (
    <LoginForm
      userId={login.userId}
      password={login.password}
      loading={loading}
      error={error}
      onChange={onChange}
      onKeyPress={onKeyPress}
    />
  );
});

export default LoginFormContainer;
