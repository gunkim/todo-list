import React from "react";
import { GrCheckbox, GrCheckboxSelected } from "react-icons/gr";
import { AiFillDelete } from "react-icons/ai";
import "./Item.scss";

function Item({ text, check }) {
  return (
    <div className="item">
      {!check ? <GrCheckbox /> : <GrCheckboxSelected />}
      <span>{text}</span>
      <AiFillDelete className="floatRight">삭제</AiFillDelete>
    </div>
  );
}

export default Item;
