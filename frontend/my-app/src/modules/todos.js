import axios from "axios";

// const CREATE_TODO = "CREATE_TODO";
// const CREATE_TODO_SUCCESS = "CREATE_TODO_SUCCESS";
// const CREATE_TODO_ERROR = "CREATE_TODO_ERROR";

const GET_TODOS = "GET_TODOS";
const GET_TODOS_SUCCESS = "GET_TODOS_SUCCESS";
const GET_TODOS_ERROR = "GET_TODOS_ERROR";

const DELETE_TODO = "DELETE_TODO";
const DELETE_TODO_SUCCESS = "DELETE_TODO_SUCCESS";
const DELETE_TODO_ERROR = "DELETE_TODO_ERROR";

export const deleteTodo = (id) => async (dispatch) => {
  dispatch({ type: DELETE_TODO });

  try {
    await axios.delete(`/api/todo/${id}`);

    const response = await axios.get("/api/todo/list");
    dispatch({ type: DELETE_TODO_SUCCESS, data: response.data });
  } catch (e) {
    dispatch({ type: DELETE_TODO_ERROR });
  }
};

export const getTodos = () => async (dispatch) => {
  dispatch({ type: GET_TODOS });

  try {
    const response = await axios.get("/api/todo/list");
    dispatch({ type: GET_TODOS_SUCCESS, data: response.data });
  } catch (e) {
    dispatch({ type: GET_TODOS_ERROR });
  }
};

const initialState = {
  loading: false,
  error: null,
  data: [],
};
export default function todos_test(state = initialState, action) {
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
    default:
      return state;
  }
}
