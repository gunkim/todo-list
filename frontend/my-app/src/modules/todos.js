import * as todosAPI from "../api/todos";
import {
  createReducer,
  createStateUtils,
  createPromiseThunk,
} from "../lib/asyncUtils";

const CREATE_TODO = "CREATE_TODO";
const CREATE_TODO_SUCCESS = `${CREATE_TODO}_SUCCESS`;
const CREATE_TODO_ERROR = `${CREATE_TODO}_ERROR`;

const GET_TODOS = "GET_TODOS";
const GET_TODOS_SUCCESS = `${GET_TODOS}_SUCCESS`;
const GET_TODOS_ERROR = `${GET_TODOS}_ERROR`;

const DELETE_TODO = "DELETE_TODO";
const DELETE_TODO_SUCCESS = `${DELETE_TODO}_SUCCESS`;
const DELETE_TODO_ERROR = `${DELETE_TODO}_ERROR`;

const SET_REVERSE_CHECK_TODO = "SET_REVERSE_CHECK_TODO";
const SET_REVERSE_CHECK_TODO_SUCCESS = `${SET_REVERSE_CHECK_TODO}_SUCCESS`;
const SET_REVERSE_CHECK_TODO_ERROR = `${SET_REVERSE_CHECK_TODO}_ERROR`;

export const createTodo = createPromiseThunk(CREATE_TODO, todosAPI.createTodo);
export const deleteTodo = createPromiseThunk(DELETE_TODO, todosAPI.deleteTodo);
export const getTodos = createPromiseThunk(GET_TODOS);
export const setReverseCheckTodo = createPromiseThunk(
  SET_REVERSE_CHECK_TODO,
  todosAPI.setReverseCheckTodo
);

const initialState = createStateUtils.initial([]);

export default function todos(state = initialState, action) {
  switch (action.type) {
    case GET_TODOS:
    case GET_TODOS_SUCCESS:
    case GET_TODOS_ERROR:
      return createReducer(GET_TODOS)(state, action);
    case DELETE_TODO:
    case DELETE_TODO_SUCCESS:
    case DELETE_TODO_ERROR:
      return createReducer(DELETE_TODO)(state, action);
    case CREATE_TODO:
    case CREATE_TODO_SUCCESS:
    case CREATE_TODO_ERROR:
      return createReducer(CREATE_TODO)(state, action);
    case SET_REVERSE_CHECK_TODO:
    case SET_REVERSE_CHECK_TODO_SUCCESS:
    case SET_REVERSE_CHECK_TODO_ERROR:
      return createReducer(SET_REVERSE_CHECK_TODO)(state, action);
    default:
      return state;
  }
}
