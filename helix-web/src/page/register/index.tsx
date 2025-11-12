import { useEffect } from "react"

import { AuthApi } from "@/api"
import { message } from "antd"
import dayjs from "dayjs"
import { useNavigate } from "react-router"

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
      .catch((reason: unknown) => {})
  }, [])

  return (
    <>
      {contextHolder}
      <div>注册页</div>
    </>
  )
}
