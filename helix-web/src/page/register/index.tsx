import { useEffect } from "react"
import { useNavigate } from "react-router"
import dayjs from "dayjs"
import type { AxiosError } from "axios"
import { message } from "antd"

import { AuthApi } from "@/api"
import type { GeneralErrorResponse } from "@/types"

export default function RegisterPage() {
  const [messageApi, contextHolder] = message.useMessage()
  const navigate = useNavigate()

  useEffect(() => {
    let cancelled = false

    AuthApi.fetchRegisterEnabled()
      .then((enabled) => {
        if (!enabled && !cancelled) {
          void messageApi
            .error("注册功能暂未开放", dayjs.duration({ seconds: 3 }).asSeconds())
            .then(() => {
              if (!cancelled) {
                void navigate("/login")
              }
            })
        }
      })
      .catch((reason: unknown) => {
        if (cancelled) return
        const error = reason as AxiosError<GeneralErrorResponse>
        void messageApi.error(error.response?.data.message ?? "发生未知错误，请稍后再试")
      })

    return () => {
      cancelled = true
    }
  }, [messageApi, navigate])

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center p-4">
      {contextHolder}
      <div className="absolute top-0 left-0 w-72 h-72 bg-blue-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>
      <div className="absolute top-0 right-0 w-72 h-72 bg-purple-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>
      <div className="absolute bottom-0 left-1/2 w-72 h-72 bg-pink-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>
      <div>注册页</div>
    </div>
  )
}
