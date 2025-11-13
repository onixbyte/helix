import axios from "axios"
import dayjs from "dayjs"
import store from "@/store"

const webClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: dayjs.duration({ seconds: 10 }).asMilliseconds(),
})

webClient.interceptors.request.use((config) => {
  const state = store.getState()
  if (state.auth.isAuthenticated) {
    config.headers["Authorization"] = state.auth.token!
  }

  return config
}, (error: unknown) => {
  console.log(error)
  return Promise.reject(new Error("请求错误，请稍后再试"))
})

export default webClient
