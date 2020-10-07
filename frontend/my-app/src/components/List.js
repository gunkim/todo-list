import React from "react";
import Item from "../components/Item";
import { CircularProgress } from "@material-ui/core";
import { createStateUtils } from "../lib/asyncUtils";
import styled from "styled-components";

const AlarmMsg = styled.div`
  color: blue;
`;

const List = ({ list, loading, error }) => {
  if (loading) {
    return <CircularProgress />;
  }
  if (error) {
    return <div>오류입니다... 잠시 후 다시시도해주세요.</div>;
  }
  if(list.length === 0){
    return <AlarmMsg>할 일을 입력해보세요!</AlarmMsg>
  }
  return (
    <>
      {list.map((todo) => (
        <Item key={todo.id} id={todo.id} text={todo.text} check={todo.check} />
      ))}
    </>
  );
};

List.defaultProps = createStateUtils.error();

export default List;
