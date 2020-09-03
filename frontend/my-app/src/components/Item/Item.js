import React from "react";
import { GrCheckbox, GrCheckboxSelected } from "react-icons/gr";
import { AiFillDelete } from "react-icons/ai";
import { useDispatch } from "react-redux";
import { deleteTodo } from "../../modules/todos";
import { setReverseCheckTodo } from "../../modules/todos";
import styled from "styled-components";
import { makeStyles } from "@material-ui/core/styles"; // styles 기능 추가

const Main = styled.div`
  padding-left: 20px;
  margin-top: 10px;
  font-size: 1.5rem;
  text-align: left;
  border-bottom: 1px solid black;
  padding-right: 10px;
  cursor: pointer;
`;
const Text = styled.span`
  margin-left: 10px;
`;
const useStyles = makeStyles((theme) => ({
  root: {
    float: "right",
  },
}));

function Item({ id, text, check }) {
  const classes = useStyles();
  const dispatch = useDispatch();
  const onClick = () => {
    dispatch(setReverseCheckTodo(id));
  };
  const onDelete = () => {
    dispatch(deleteTodo(id));
  };
  return (
    <Main>
      {!check ? (
        <GrCheckbox onClick={onClick} />
      ) : (
        <GrCheckboxSelected onClick={onClick} />
      )}
      <Text>{text}</Text>
      <AiFillDelete className={classes.root} onClick={onDelete} />
    </Main>
  );
}

export default Item;
