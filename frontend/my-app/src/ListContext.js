import React, { createContext, useReducer, useContext, useRef } from "react";

const initialList = [
  {
    id: 1,
    check: false,
    text: "리액트 공부하기",
  },
  {
    id: 2,
    check: true,
    text: "리액트 안녕",
  },
  {
    id: 3,
    check: true,
    text: "리액트 수고",
  },
  {
    id: 4,
    check: false,
    text: "리액트 잘 있어",
  },
];

function listReducer(state, action) {
  switch (action.type) {
    case "CHANGE_CHECK":
      return state.map((todo) =>
        todo.id === action.id ? { ...todo, check: !todo.check } : todo
      );
    case "CREATE_TODO":
      return [...state, { id: action.id, check: false, text: action.text }];
    default:
      return state;
  }
}

const ListStateContext = createContext();
const ListDispatchContext = createContext();
const ListNextIdContext = createContext();

export function ListProvider({ children }) {
  const [state, dispatch] = useReducer(listReducer, initialList);
  const nextId = useRef(5);

  return (
    <ListStateContext.Provider value={state}>
      <ListDispatchContext.Provider value={dispatch}>
        <ListNextIdContext.Provider value={nextId}>
          {children}
        </ListNextIdContext.Provider>
      </ListDispatchContext.Provider>
    </ListStateContext.Provider>
  );
}

export function useListState() {
  return useContext(ListStateContext);
}
export function useListDispatch() {
  return useContext(ListDispatchContext);
}
export function useListNextId() {
  return useContext(ListNextIdContext);
}
