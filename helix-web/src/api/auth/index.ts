import webClient from "@/service/web-client"
import type { CaptchaResponse, User, UsernamePasswordLoginRequest, UserResponse } from "@/types"
import { HttpStatus } from "@/constant"

/**
 * Get captcha image and captcha id.
 */
async function getCaptcha(): Promise<CaptchaResponse | null> {
  const { data, status } = await webClient.get<CaptchaResponse | null>("/captcha")
  if (status == HttpStatus.OK) {
    return data as CaptchaResponse
  } else {
    return null
  }
}

/**
 * Login with username and password.
 * @param request
 */
async function usernamePasswordLogin(
  request: UsernamePasswordLoginRequest
): Promise<UserResponse | null> {
  const { data, headers } = await webClient.post<User>("/auth/login", request)

  const token = (headers as Record<string, string>).authorization ?? ""
  if (!token) {
    return Promise.reject(new Error("未获取到身份令牌，登录失败"))
  }

  return {
    user: data,
    token,
  }
}

/**
 * Login with WeCom.
 * @param code wecom authorisation code
 */
async function wecomLogin(code: string): Promise<UserResponse> {
  const urlSearchParams = new URLSearchParams()
  urlSearchParams.append("code", code)

  const { data, headers } = await webClient.get<User>(`/auth/wecom/login?${urlSearchParams}`)

  const token = (headers as Record<string, string>).authorization ?? ""

  if (!token) {
    return Promise.reject(new Error("未获取到身份令牌，登录失败"))
  }

  return {
    user: data,
    token,
  }
}

/**
 * Login with Microsoft Entra ID.
 * @param msalToken an identification token provided by Microsoft Entra ID
 */
async function msalLogin(msalToken: string): Promise<UserResponse> {
  const { data, headers } = await webClient.post<User>(`/auth/msal/login`, {
    msalToken,
  })

  const token = (headers as Record<string, string>).authorization ?? ""

  if (!token) {
    return Promise.reject(new Error("未获取到身份令牌，登录失败"))
  }

  return {
    user: data,
    token,
  }
}

export { wecomLogin, msalLogin, getCaptcha }
