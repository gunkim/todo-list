import React, { useState, Fragment } from "react";

function LoginForm() {
  const [login, setLogin] = useState({
    userId: "",
    password: "",
  });

  const onChange = (e) => {
    const { name, value } = e.target;
    setLogin({
      ...login,
      [name]: value,
    });
  };
  return (
    <Fragment>
      <input
        name="userId"
        type="text"
        placeholder="아이디를 입력하세요"
        value={login.userId}
        onChange={onChange}
      />
      <br />
      <input
        name="password"
        type="password"
        placeholder="비밀번호를 입력하세요"
        value={login.password}
        onChange={onChange}
      />
    </Fragment>
  );
}

export default LoginForm;
