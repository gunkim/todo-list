import React, { useState, useRef, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { loginPro } from "../../modules/users";
import CircularProgress from "@material-ui/core/CircularProgress";
import "./LoginForm.scss";

function LoginForm() {
  const dispatch = useDispatch();
  const [login, setLogin] = useState({
    userId: "",
    password: "",
  });
  const { loading, error } = useSelector((state) => state.users);

  const pwdInputRef = useRef(null);

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
  useEffect(() => {
    if (error) {
      pwdInputRef.current.focus();
    }
  }, [error]);
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
          ref={pwdInputRef}
        />
      </div>
      {loading ? <CircularProgress /> : ""}
      {error ? (
        <div className="msg">아이디 및 비밀번호를 다시 한번 확인해주세요.</div>
      ) : (
        ""
      )}
    </div>
  );
}

export default LoginForm;
