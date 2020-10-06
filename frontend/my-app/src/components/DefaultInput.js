import React from "react";
import styled from "styled-components";

const Input = styled.input`
  border-style: none;
  border-bottom: 3px solid #00b389;
  font-size: 1.5rem;
  margin-top: 10px;
  margin-bottom: 10px;
  &:focus {
    outline: none;
  }
`;

const DefaultInput = ({
  name,
  type,
  placeholder,
  value,
  onChange,
  onKeyPress,
  ref,
}) => {
  return (
    <>
      <Input
        name={name}
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        onKeyPress={onKeyPress}
        ref={ref}
      />
    </>
  );
};

export default DefaultInput;
