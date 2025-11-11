import { type MouseEvent, useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router"
import { Form, Input, Button, Card, message } from "antd"
import dayjs from "dayjs"
import type { AxiosError } from "axios"
import type { CaptchaResponse, GeneralErrorResponse, UsernamePasswordLoginRequest } from "@/types"
import * as AuthApi from "@/api/auth"
import { useAppDispatch } from "@/store"
import { loginSuccess } from "@/store/auth-slice"

export default function LoginPage() {
  const dispatch = useAppDispatch()
  const navigate = useNavigate()

  const [messageApi, contextHolder] = message.useMessage()
  const [form] = Form.useForm<UsernamePasswordLoginRequest>()

  const [hasCaptcha, setHasCaptcha] = useState<boolean>(false)
  const [captchaData, setCaptchaData] = useState<CaptchaResponse | null>()

  const fetchCaptcha = useCallback(async () => {
    try {
      const response = await AuthApi.getCaptcha()
      if (response) {
        setHasCaptcha(true)
        setCaptchaData(response)
        form.setFieldValue("uuid", response.uuid)
      } else {
        setHasCaptcha(false)
        setCaptchaData(null)
        form.setFieldValue("uuid", null)
      }
    } catch (error) {
      console.error("Failed to fetch captcha due to an error:", error)
      setHasCaptcha(false)
      setCaptchaData(null)
      form.setFieldValue("uuid", null)
    }
  }, [form])

  useEffect(() => {
    void fetchCaptcha()
  }, [fetchCaptcha])

  const performLogin = useCallback(
    async (values: UsernamePasswordLoginRequest) => {
      try {
        console.log("Login values:", values)

        const loginResponse = await AuthApi.usernamePasswordLogin(values)
        if (loginResponse) {
          dispatch(
            loginSuccess({
              user: loginResponse.user,
              token: loginResponse.accessToken,
            })
          )
          messageApi.success("登录成功", dayjs.duration({ seconds: 3 }).asSeconds())
          await navigate("/")
        } else {
          messageApi.error("登录失败：服务器响应异常。")
        }
      } catch (errorInfo: unknown) {
        const error = errorInfo as AxiosError<GeneralErrorResponse>
        console.log(error)
        messageApi.error(
          error.response?.data.message ?? "登录失败，请稍后再试",
          dayjs.duration({ seconds: 3 }).asSeconds()
        )
      }
    },
    [dispatch, navigate, messageApi]
  )

  // 刷新验证码图片
  const refreshCaptcha = (event: MouseEvent) => {
    event.preventDefault()
    void fetchCaptcha()
  }

  return (
    <div>
      {contextHolder}
      <Card title="用户登录" className="w-[400px]! my-5! mx-auto!">
        <Form<UsernamePasswordLoginRequest>
          name="usernamePasswordLoginForm"
          form={form}
          onFinish={(values) => {
            void performLogin(values)
          }}
          layout="vertical">
          <Form.Item<UsernamePasswordLoginRequest>
            label="用户名"
            name="username"
            rules={[{ required: true, message: "请输入用户名!" }]}>
            <Input placeholder="请输入用户名" />
          </Form.Item>
          <Form.Item<UsernamePasswordLoginRequest>
            label="密码"
            name="password"
            rules={[{ required: true, message: "请输入密码!" }]}>
            <Input.Password placeholder="请输入密码" />
          </Form.Item>

          {hasCaptcha ? (
            <>
              <Form.Item<UsernamePasswordLoginRequest>
                label="验证码"
                name="captcha"
                rules={[{ required: true, message: "请输入验证码!" }]}>
                <div className="flex items-center gap-2">
                  <Input placeholder="请输入验证码" className="flex-1!" />
                  {captchaData?.captcha && (
                    <img
                      src={captchaData.captcha}
                      alt="验证码"
                      onClick={refreshCaptcha}
                      className="cursor-pointer h-8 border border-gray-300"
                    />
                  )}
                </div>
              </Form.Item>
              <Form.Item<UsernamePasswordLoginRequest> name="uuid" hidden>
                <div>Placeholder</div>
              </Form.Item>
            </>
          ) : null}

          <Form.Item>
            <Button type="primary" htmlType="submit" block>
              登录
            </Button>
          </Form.Item>
        </Form>

        {/* <Divider>第三方帐号登录</Divider> */}
      </Card>
    </div>
  )
}
