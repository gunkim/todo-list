import React from "react";
import { GrCheckbox, GrCheckboxSelected } from "react-icons/gr";
import { AiFillDelete } from "react-icons/ai";
import "./Item.scss";
import { useListDispatch } from "../../ListContext";

function Item({ id, text, check }) {
  const dispatch = useListDispatch();
  const onClick = () => {
    dispatch({
      type: "CHANGE_CHECK",
      id: id,
    });
  };
  return (
    <div className="item">
      {!check ? (
        <GrCheckbox onClick={onClick} />
      ) : (
        <GrCheckboxSelected onClick={onClick} />
      )}
      <span>{text}</span>
      <AiFillDelete className="floatRight">삭제</AiFillDelete>
    </div>
  );
}

export default Item;
