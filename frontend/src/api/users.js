import axios from "axios";

const BASE_API_URI = "/api/auth";

export const loginPro = async (id, pw) => {
  const response = axios.post(`${BASE_API_URI}/login`, {
    username: id,
    password: pw,
  });
  return await response;
};
