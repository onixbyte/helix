import { type MouseEvent, useCallback, useEffect, useState } from "react"
import { useNavigate } from "react-router"
import { Form, Input, Button, Card, message, Divider } from "antd"
import dayjs from "dayjs"
import type { AxiosError } from "axios"
import type { CaptchaResponse, GeneralErrorResponse, UsernamePasswordLoginRequest } from "@/types"
import { AuthApi } from "@/api"
import { useAppDispatch } from "@/store"
import { loginSuccess } from "@/store/auth-slice"
import {
  DingTalkFilled,
  GoogleFilled,
  LarkFilled,
  MicrosoftFilled,
  SlackFilled,
  WeComFilled,
  GitlabFilled,
  DiscordFilled,
  EmailFilled,
} from "@/components/icon"
import { useMsal } from "@azure/msal-react"
import { doMsalLogin } from "@/service/auth/msal"
import { GithubFilled } from "@ant-design/icons"

export default function LoginPage() {
  const dispatch = useAppDispatch()
  const navigate = useNavigate()

  const msalContext = useMsal()

  const [messageApi, contextHolder] = message.useMessage()
  const [form] = Form.useForm<UsernamePasswordLoginRequest>()

  // const [isLoading, setIsLoading] = useState<boolean>(false)
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

  /**
   * 用户名密码登录
   */
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

  /**
   * 刷新验证码图片
   */
  const refreshCaptcha = (event: MouseEvent) => {
    event.preventDefault()
    void fetchCaptcha()
  }

  /**
   * 使用 Microsoft Entra ID 登录
   */
  const performMsalLogin = () => {
    console.log("使用 Microsoft 账号登录")
    // void doMsalLogin(msalContext.instance, dispatch, () => void navigate("/"))
  }

  /**
   * 使用 DingTalk 登录
   */
  const performDingTalkLogin = () => {
    console.log("使用钉钉登录")
    // todo implement this
  }

  /**
   * 使用 WeCom 登录
   */
  const performWeComLogin = () => {
    console.log("使用企业微信登录")
    // todo implement this
  }

  /**
   * 使用 Lark 登录
   */
  const performLarkLogin = () => {
    console.log("使用飞书登录")
    // todo implement this
  }

  /**
   * 使用 Slack 登录
   */
  const performSlackLogin = () => {
    console.log("使用 Slack 登录")
    // todo implement this
  }

  /**
   * 使用 Github 登录
   */
  const performGithubLogin = () => {
    console.log("使用 GitHub 登录")
    // todo implement this
  }

  /**
   * 使用 Google 登录
   */
  const performGoogleLogin = () => {
    console.log("使用 Google 登录")
    // todo implement this
  }

  /**
   * 使用 Gitlab 登录
   */
  const performGitlabLogin = () => {
    console.log("使用 Gitlab 登录")
    // todo implement this
  }

  /**
   * 使用 Discord 登录
   */
  const performDiscordLogin = () => {
    console.log("使用 Discord 登录")
    // todo implement this
  }

  /**
   * 使用 Email 登录
   */
  const performEmailLogin = () => {
    console.log("使用 Email 登录")
    // todo implement this
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center p-4">
      {contextHolder}

      {/* 背景装饰元素 */}
      <div className="absolute top-0 left-0 w-72 h-72 bg-blue-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>
      <div className="absolute top-0 right-0 w-72 h-72 bg-purple-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>
      <div className="absolute bottom-0 left-1/2 w-72 h-72 bg-pink-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>

      <Card
        title={
          <div className="text-center py-4">
            <h1 className="text-2xl font-bold text-gray-800 mb-2">欢迎回来</h1>
            <p className="text-gray-600 text-sm">请登录您的账户</p>
          </div>
        }
        className="w-full max-w-md shadow-2xl border-0 backdrop-blur-sm bg-white/90 relative z-10"
        styles={{
          body: {
            padding: "32px",
          },
        }}>
        <Form<UsernamePasswordLoginRequest>
          name="usernamePasswordLoginForm"
          form={form}
          onFinish={(values) => {
            void performLogin(values)
          }}
          layout="vertical"
          className="space-y-4">
          <Form.Item<UsernamePasswordLoginRequest>
            label={<span className="text-gray-700 font-medium">用户名</span>}
            name="username"
            rules={[{ required: true, message: "请输入用户名!" }]}>
            <Input
              placeholder="请输入用户名"
              size="large"
              className="rounded-lg border-gray-300 hover:border-blue-400 focus:border-blue-500 transition-colors"
            />
          </Form.Item>

          <Form.Item<UsernamePasswordLoginRequest>
            label={<span className="text-gray-700 font-medium">密码</span>}
            name="password"
            rules={[{ required: true, message: "请输入密码!" }]}>
            <Input.Password
              placeholder="请输入密码"
              size="large"
              className="rounded-lg border-gray-300 hover:border-blue-400 focus:border-blue-500 transition-colors"
            />
          </Form.Item>

          {hasCaptcha ? (
            <>
              <Form.Item<UsernamePasswordLoginRequest>
                label={<span className="text-gray-700 font-medium">验证码</span>}
                name="captcha"
                rules={[{ required: true, message: "请输入验证码!" }]}>
                <div className="flex items-center gap-3">
                  <Input
                    placeholder="请输入验证码"
                    size="large"
                    className="rounded-lg border-gray-300 hover:border-blue-400 focus:border-blue-500 transition-colors"
                  />
                  {captchaData?.captcha && (
                    <img
                      src={captchaData.captcha}
                      alt="验证码"
                      onClick={refreshCaptcha}
                      className="cursor-pointer h-10 border border-gray-300 rounded-lg transition-transform hover:scale-105"
                    />
                  )}
                </div>
              </Form.Item>
              <Form.Item<UsernamePasswordLoginRequest> name="uuid" hidden>
                <div>Placeholder</div>
              </Form.Item>
            </>
          ) : null}

          <Form.Item className="mb-0">
            <Button
              type="primary"
              htmlType="submit"
              block
              size="large"
              className="h-12 rounded-lg font-semibold text-base bg-gradient-to-r from-blue-500 to-purple-600 border-0 hover:from-blue-600 hover:to-purple-700 transition-all duration-300 shadow-lg hover:shadow-xl">
              登录
            </Button>
          </Form.Item>
        </Form>

        <Divider className="my-6 text-gray-400">
          <span className="text-gray-500 text-sm font-medium">第三方帐号登录</span>
        </Divider>

        <div className="grid grid-cols-5 gap-4 justify-items-center w-full">
          <Button
            icon={<DingTalkFilled className="text-blue-600" />}
            size="large"
            onClick={performDingTalkLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-blue-300 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="钉钉登录"
          />
          <Button
            icon={<DiscordFilled className="text-indigo-600" />}
            size="large"
            onClick={performDiscordLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-indigo-300 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="Discord登录"
          />
          <Button
            icon={<EmailFilled className="text-red-600" />}
            size="large"
            onClick={performEmailLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-red-300 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="邮箱登录"
          />
          <Button
            icon={<GithubFilled className="text-gray-800" />}
            size="large"
            onClick={performGithubLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-gray-400 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="GitHub登录"
          />
          <Button
            icon={<GitlabFilled className="text-orange-600" />}
            size="large"
            onClick={performGitlabLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-orange-300 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="GitLab登录"
          />
          <Button
            icon={<GoogleFilled className="text-red-500" />}
            size="large"
            onClick={performGoogleLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-red-300 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="Google登录"
          />
          <Button
            icon={<LarkFilled className="text-blue-700" />}
            size="large"
            onClick={performLarkLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-blue-400 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="飞书登录"
          />
          <Button
            icon={<MicrosoftFilled className="text-blue-600" />}
            size="large"
            onClick={performMsalLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-blue-300 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="Microsoft登录"
          />
          <Button
            icon={<SlackFilled className="text-purple-600" />}
            size="large"
            onClick={performSlackLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-purple-300 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="Slack登录"
          />
          <Button
            icon={<WeComFilled className="text-green-600" />}
            size="large"
            onClick={performWeComLogin}
            className="w-14 h-12 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 hover:border-green-300 transition-all duration-300 shadow-sm hover:shadow-md flex items-center justify-center"
            title="企业微信登录"
          />
        </div>

        <div className="mt-8 text-center">
          <p className="text-gray-500 text-xs">&copy; 2024 Your Company. All rights reserved.</p>
        </div>
      </Card>
    </div>
  )
}
