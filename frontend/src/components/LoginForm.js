import React from "react";
import CircularProgress from "@material-ui/core/CircularProgress";
import DefaultInput from "../components/DefaultInput";
import styled from "styled-components";

const FailMsg = styled.div`
  color: red;
`;

function LoginForm({ userId, password, loading, error, onChange, onKeyPress }) {
  return (
    <>
      <h1>로그인</h1>
      <div>
        <DefaultInput
          name="userId"
          type="text"
          placeholder="아이디를 입력하세요"
          value={userId}
          onChange={onChange}
        />
      </div>
      <div>
        <DefaultInput
          name="password"
          type="password"
          placeholder="비밀번호를 입력하세요"
          value={password}
          onChange={onChange}
          onKeyPress={onKeyPress}
        />
      </div>
      {loading && <CircularProgress />}
      {error && <FailMsg>아이디 및 비밀번호를 다시 한번 확인해주세요.</FailMsg>}
    </>
  );
}

export default LoginForm;
