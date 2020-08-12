import * as todosAPI from "../api/todos";

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

export const createTodo = (text) => async (dispatch) => {
  dispatch({ type: CREATE_TODO });

  try {
    await todosAPI.createTodo(text);
    const response = await todosAPI.getTodos();
    dispatch({ type: CREATE_TODO_SUCCESS, data: response });
  } catch (e) {
    dispatch({ type: CREATE_TODO_ERROR });
  }
};

export const deleteTodo = (id) => async (dispatch) => {
  dispatch({ type: DELETE_TODO });

  try {
    await todosAPI.deleteTodo(id);
    const response = await todosAPI.getTodos();
    dispatch({ type: DELETE_TODO_SUCCESS, data: response });
  } catch (e) {
    dispatch({ type: DELETE_TODO_ERROR });
  }
};

export const getTodos = () => async (dispatch) => {
  dispatch({ type: GET_TODOS });

  try {
    const response = await todosAPI.getTodos();
    dispatch({ type: GET_TODOS_SUCCESS, data: response });
  } catch (e) {
    dispatch({ type: GET_TODOS_ERROR });
  }
};
export const setReverseCheckTodo = (id) => async (dispatch) => {
  dispatch({ type: SET_REVERSE_CHECK_TODO });

  try {
    await todosAPI.setReverseCheckTodo(id);
    const response = await todosAPI.getTodos();
    dispatch({ type: SET_REVERSE_CHECK_TODO_SUCCESS, data: response });
  } catch (e) {
    dispatch({ type: SET_REVERSE_CHECK_TODO_ERROR });
  }
};

const initialState = {
  loading: false,
  error: null,
  data: [],
};
export default function todos(state = initialState, action) {
  switch (action.type) {
    case GET_TODOS:
      return {
        ...state,
        loading: true,
        error: false,
      };
    case GET_TODOS_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.data,
        error: false,
      };
    case GET_TODOS_ERROR:
      return {
        ...state,
        loading: false,
        error: true,
      };
    case DELETE_TODO:
      return {
        ...state,
        loading: true,
        error: false,
      };
    case DELETE_TODO_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.data,
        error: false,
      };
    case DELETE_TODO_ERROR:
      return {
        ...state,
        loading: false,
        error: true,
      };
    case CREATE_TODO:
      return {
        ...state,
        loading: true,
        error: false,
      };
    case CREATE_TODO_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.data,
        error: false,
      };
    case CREATE_TODO_ERROR:
      return {
        ...state,
        loading: false,
        error: true,
      };
    case SET_REVERSE_CHECK_TODO:
      return {
        ...state,
        loading: true,
        error: false,
      };
    case SET_REVERSE_CHECK_TODO_SUCCESS:
      return {
        ...state,
        loading: false,
        data: action.data,
        error: false,
      };
    case SET_REVERSE_CHECK_TODO_ERROR:
      return {
        ...state,
        loading: false,
        error: true,
      };
    default:
      return state;
  }
}
