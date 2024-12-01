import axios from "axios";

// Obtiene la URL base de la API desde las variables de entorno de Vite
const baseURL = localStorage.getItem("port");

console.log(baseURL);

// Configura Axios con la baseURL
const api = axios.create({
  baseURL: `http://localhost:8080`,
  headers: {
    "Content-type": "application/json",
  },
});

export default api;
