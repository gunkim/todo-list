import * as usersAPI from "../api/users";

const LOGIN_PROCESS = "LOGIN_PROCESS";
const LOGIN_PROCESS_SUCCESS = `${LOGIN_PROCESS}_SUCCESS`;
const LOGIN_PROCESS_ERROR = `${LOGIN_PROCESS}_ERROR`;

export const loginPro = (id, pw) => async (dispatch, getState, { history }) => {
  dispatch({ type: LOGIN_PROCESS });
  try {
    const response = await usersAPI.loginPro(id, pw);

    dispatch({ type: LOGIN_PROCESS_SUCCESS, payload: response.data });

    history.push("/list");
  } catch (e) {
    dispatch({ type: LOGIN_PROCESS_ERROR });
  }
};

const initialState = {
  loading: false,
  error: null,
  jwtToken: null,
};

export default function users(state = initialState, action) {
  switch (action.type) {
    case LOGIN_PROCESS:
      return {
        ...state,
        loading: true,
        error: false,
      };
    case LOGIN_PROCESS_SUCCESS:
      return {
        ...state,
        loading: false,
        error: false,
        jwtToken: action.payload,
      };
    case LOGIN_PROCESS_ERROR:
      return {
        ...state,
        loading: false,
        error: true,
      };
    default:
      return state;
  }
}
