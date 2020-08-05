import { combineReducers } from "redux";
import todos from "./todos";
import users from "./users";

const rootReducer = combineReducers({ todos, users });

export default rootReducer;
