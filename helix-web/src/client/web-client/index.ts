import axios, { type AxiosError } from "axios"
import dayjs from "dayjs"
import store from "@/store"
import { HttpStatus } from "@/constant"
import { logout } from "@/store/auth-slice"
import type { GeneralErrorResponse } from "@/types/web/response"

const webClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: dayjs.duration({ seconds: 10 }).asMilliseconds(),
  withCredentials: true,
  withXSRFToken: true,
})

webClient.interceptors.request.use(
  (config) => {
    return config
  },
  (error: unknown) => {
    console.log(error)
    return Promise.reject(new Error("请求错误，请稍后再试"))
  }
)

webClient.interceptors.response.use(
  (response) => {
    return response
  },
  (error: unknown) => {
    const err = error as AxiosError<GeneralErrorResponse>
    if (err.response?.status == HttpStatus.UNAUTHORIZED) {
      store.dispatch(logout())
    }
    return Promise.reject(error as AxiosError)
  }
)

export default webClient
