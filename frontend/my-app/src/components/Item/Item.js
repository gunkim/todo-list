import React from "react";
import { GrCheckbox, GrCheckboxSelected } from "react-icons/gr";
import { AiFillDelete } from "react-icons/ai";
import "./Item.scss";
import { useDispatch } from "react-redux";
import { deleteTodo } from "../../modules/todos";
import { setReverseCheckTodo } from "../../modules/todos";

function Item({ id, text, check }) {
  const dispatch = useDispatch();
  const onClick = () => {
    dispatch(setReverseCheckTodo(id));
  };
  const onDelete = () => {
    dispatch(deleteTodo(id));
  };
  return (
    <div className="item">
      {!check ? (
        <GrCheckbox onClick={onClick} />
      ) : (
        <GrCheckboxSelected onClick={onClick} />
      )}
      <span>{text}</span>
      <AiFillDelete className="floatRight" onClick={onDelete} />
    </div>
  );
}

export default Item;
