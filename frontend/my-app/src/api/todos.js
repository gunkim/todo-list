import axios from "axios";

const BASE_API_URI = "/api/todo";

export const deleteTodo = async (id) => {
  const token = localStorage.getItem("token");
  const response = await axios.delete(`${BASE_API_URI}/${id}`, {
    headers: { Authorization: token },
  });
  return response.data;
};
export const getTodos = async () => {
  const token = localStorage.getItem("token");
  const response = await axios.get(`${BASE_API_URI}/list`, {
    headers: { Authorization: token },
  });
  return response.data;
};
export const createTodo = async (text) => {
  const token = localStorage.getItem("token");
  const response = await axios.post(
    BASE_API_URI,
    { text: text },
    {
      headers: { Authorization: token },
    }
  );
  return response.data;
};
export const setReverseCheckTodo = async (id) => {
  const token = localStorage.getItem("token");
  const response = await axios.put(`${BASE_API_URI}/${id}`, null, {
    headers: { Authorization: token },
  });
  return response.data;
};
