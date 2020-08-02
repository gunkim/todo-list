const SET_REVERSE_CHECK_TODO = "SET_REVERSE_CHECK_TODO";
const CREATE_TODO = "CREATE_TODO";
const DELETE_TODO = "DELETE_TODO";

let nextId = 5;

const initialState = [
  {
    id: 1,
    check: true,
    text: "리액트 공부하기",
  },
  {
    id: 2,
    check: false,
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

export default function todos(state = initialState, action) {
  switch (action.type) {
    case SET_REVERSE_CHECK_TODO:
      return state.map((todo) =>
        todo.id === action.id ? { ...todo, check: !todo.check } : todo
      );
    case CREATE_TODO:
      return [...state, { id: nextId++, check: false, text: action.text }];
    case DELETE_TODO:
      return state.filter((todo) => todo.id !== action.id);
    default:
      return state;
  }
}
