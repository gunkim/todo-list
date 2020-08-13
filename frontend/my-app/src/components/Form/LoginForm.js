import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { loginPro } from "../../modules/users";

function LoginForm() {
  const dispatch = useDispatch();
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
  const onKeyPress = (e) => {
    if (e.key === "Enter") {
      const { userId: id, password: pw } = login;
      dispatch(loginPro(id, pw));
    }
  };
  return (
    <div>
      <div>
        <input
          name="userId"
          type="text"
          placeholder="아이디를 입력하세요"
          value={login.userId}
          onChange={onChange}
        />
      </div>
      <div>
        <input
          name="password"
          type="password"
          placeholder="비밀번호를 입력하세요"
          value={login.password}
          onChange={onChange}
          onKeyPress={onKeyPress}
        />
      </div>
    </div>
  );
}

export default LoginForm;
