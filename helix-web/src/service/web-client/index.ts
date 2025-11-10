import axios from "axios"
import dayjs from "dayjs"

const webClient = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: dayjs.duration({ seconds: 10 }).asMilliseconds(),
})

export default webClient
