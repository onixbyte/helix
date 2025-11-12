import { useEffect } from "react"

import { AuthApi } from "@/api"
import { message } from "antd"
import dayjs from "dayjs"
import { useNavigate } from "react-router"
import type { AxiosError } from "axios"
import type { GeneralErrorResponse } from "@/types"

export default function RegisterPage() {
  const [messageApi, contextHolder] = message.useMessage()
  const navigate = useNavigate()

  useEffect(() => {
    AuthApi.fetchRegisterEnabled()
      .then((enabled) => {
        if (!enabled) {
          void messageApi
            .error("注册功能暂未开放", dayjs.duration({ seconds: 3 }).asSeconds())
            .then(() => void navigate("/login"))
        }
      })
      .catch((reason: unknown) => {
        const error = reason as AxiosError<GeneralErrorResponse>
        void messageApi.error(error.response?.data.message ?? "发生未知错误，请稍后再试")
      })
  }, [])

  return (
    <>
      {contextHolder}
      <div>注册页</div>
    </>
  )
}
