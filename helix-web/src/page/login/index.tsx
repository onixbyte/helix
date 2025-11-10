import { type MouseEvent, useCallback, useEffect, useState } from "react"
import { Form, Input, Button, Card, Divider, message } from "antd"
import type { CaptchaResponse, UsernamePasswordLoginRequest } from "@/types"
import * as AuthApi from "@/api/auth"
import { useAppDispatch } from "@/store"
import { loginSuccess } from "@/store/auth-slice"
import moment from "moment"

export default function Login() {
  const dispatch = useAppDispatch()

  const [messageApi, contextHolder] = message.useMessage()
  const [form] = Form.useForm<UsernamePasswordLoginRequest>()

  const [hasCaptcha, setHasCaptcha] = useState<boolean>(false)
  const [captchaData, setCaptchaData] = useState<CaptchaResponse | undefined>(undefined) // 重命名为 captchaData 避免与类型混淆，并明确初始 undefined 状态

  const fetchCaptcha = useCallback(async () => {
    try {
      const response = await AuthApi.getCaptcha()
      if (response) {
        setHasCaptcha(true)
        setCaptchaData(response)
        // form.setFieldValue 是 form 实例上的方法，form 实例本身是稳定的
        form.setFieldValue("uuid", response.uuid)
      } else {
        console.warn("API returned an empty or invalid captcha response.")
        setHasCaptcha(false)
        setCaptchaData(undefined)
        form.setFieldValue("uuid", undefined)
      }
    } catch (error) {
      console.error("Failed to fetch captcha due to an error:", error)
      setHasCaptcha(false)
      setCaptchaData(undefined)
      form.setFieldValue("uuid", undefined)
    }
  }, [form])

  useEffect(() => {
    void fetchCaptcha()
  }, [fetchCaptcha])

  const performLogin = async () => {
    try {
      // 触发表单验证
      const values = await form.validateFields()
      console.log("Login values:", values)

      // 在这里可以调用实际的登录 API
      const loginResponse = await AuthApi.usernamePasswordLogin(values)
      console.log("Login successful:", loginResponse)
      if (loginResponse) {
        dispatch(
          loginSuccess({
            user: loginResponse.user,
            token: loginResponse.accessToken,
          })
        )
        message.open({
          type: "success",
          content: "登录成功",
          duration: moment.duration({ second: 3 }).asSeconds(),
        })
      }
    } catch (errorInfo) {
      console.error("Login validation failed:", errorInfo)
      // 如果验证失败，可以显示 Ant Design 的校验信息
    }
  }

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
          onFinish={() => void performLogin()}
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
                <div style={{ display: "flex", alignItems: "center", gap: "8px" }}>
                  <Input placeholder="请输入验证码" style={{ flex: 1 }} />{" "}
                  {/* Input 占据剩余空间 */}
                  {captchaData?.captcha && (
                    <img
                      src={captchaData.captcha}
                      alt="验证码"
                      onClick={refreshCaptcha}
                      style={{ cursor: "pointer", height: "32px", border: "1px solid #d9d9d9" }}
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

        <Divider>第三方帐号登录</Divider>
      </Card>
    </div>
  )
}
