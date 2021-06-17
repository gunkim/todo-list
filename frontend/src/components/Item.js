import React from "react";
import { GrCheckbox, GrCheckboxSelected } from "react-icons/gr";
import { AiFillDelete } from "react-icons/ai";
import { useDispatch } from "react-redux";
import { deleteTodo } from "../modules/todos";
import { setReverseCheckTodo } from "../modules/todos";
import styled from "styled-components";
import { makeStyles } from "@material-ui/core/styles";
import {createStyles} from "@material-ui/core";

const useStyles = makeStyles({
    root: {
        float: 'right'
    }
})
const Text = styled.span`
  margin-left: 10px;
`;
const Main = styled.div`
  font-size: 1.5rem;
  text-align: left;
  border-bottom: 1px solid black;
  cursor: pointer;
  padding: 5px 5px 0 20px;
  &:hover{
    background-color: #f3f3f3;
  }
`;


const Item = ({ id, text, check }) => {
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
      <AiFillDelete className={classes.root} onClick={onDelete} component="svg"/>
    </Main>
  );
};

export default Item;
