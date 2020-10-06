import React from "react";
import Item from "../components/Item";
import { CircularProgress } from "@material-ui/core";
import { createStateUtils } from "../lib/asyncUtils";

const List = ({ list, loading, error }) => {
  if (loading) {
    return <CircularProgress />;
  }
  if (error) {
    return <div>오류입니다... 잠시 후 다시시도해주세요.</div>;
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
