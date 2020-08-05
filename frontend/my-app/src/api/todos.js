import axios from "axios";

const BASE_API_URI = "/api/todo";

export const deleteTodo = async (id) => {
  const response = await axios.delete(`${BASE_API_URI}/${id}`);
  return response.status;
};
export const getTodos = async () => {
  const response = await axios.get(`${BASE_API_URI}/list`);
  return response.data;
};
export const createTodo = async (text) => {
  const response = await axios.post(BASE_API_URI, { text: text });
  return response.status;
};
export const setReverseCheckTodo = async (id) => {
  const response = await axios.put(`${BASE_API_URI}/${id}`);
  return response.status;
};
