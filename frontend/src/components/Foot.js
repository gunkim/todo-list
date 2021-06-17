import React from "react";
import styled from "styled-components";

const Main = styled.div`
  padding-top: 10px;
  font-size: 1.2rem;
`;
const NumMsg = styled.span`
  color: red;
`;

const Foot = ({ todosCnt }) => {
  return (
    <Main>
      남은 할 일 <NumMsg>{todosCnt}</NumMsg>
    </Main>
  );
};

Foot.defaultProps = {
  todosCnt: 0,
};

export default Foot;
