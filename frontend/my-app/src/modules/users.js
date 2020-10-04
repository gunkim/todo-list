import * as usersAPI from "../api/users";
import { createReducer, createStateUtils } from "../lib/reducerUtils";

const LOGIN_PROCESS = "LOGIN_PROCESS";
const LOGIN_PROCESS_SUCCESS = `${LOGIN_PROCESS}_SUCCESS`;
const LOGIN_PROCESS_ERROR = `${LOGIN_PROCESS}_ERROR`;

export const loginPro = (id, pw) => async (dispatch, getState, { history }) => {
  dispatch({ type: LOGIN_PROCESS });
  try {
    const response = await usersAPI.loginPro(id, pw);
    localStorage.setItem("token", response.data);
    dispatch({ type: LOGIN_PROCESS_SUCCESS });

    history.push("/list");
  } catch (e) {
    dispatch({ type: LOGIN_PROCESS_ERROR });
  }
};

const initialState = createStateUtils.initial();

export default function users(state = initialState, action) {
  switch (action.type) {
    case LOGIN_PROCESS:
    case LOGIN_PROCESS_SUCCESS:
    case LOGIN_PROCESS_ERROR:
      return {
        ...state,
        ...createReducer(LOGIN_PROCESS)(state, action),
      };
    default:
      return state;
  }
}
