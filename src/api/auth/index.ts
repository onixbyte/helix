import webClient from "@/client/web-client"
import { HttpStatus } from "@/constant"
import type { CaptchaResponse, UserAuthResponse } from "@/types/web/response"
import type { UsernamePasswordLoginRequest } from "@/types/web/request"

/**
 * 获取验证码图片及验证码 UUID
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
 * 使用用户名密码登录
 * @param request 用户名密码
 */
async function usernamePasswordLogin(
  request: UsernamePasswordLoginRequest
): Promise<UserAuthResponse | null> {
  const { data } = await webClient.post<UserAuthResponse>("/auth/login", request)
  return data
}

/**
 * 使用企业微信登录
 * @param code 由企业微信提供的身份验证 code
 */
async function wecomLogin(code: string): Promise<UserAuthResponse> {
  const urlSearchParams = new URLSearchParams()
  urlSearchParams.append("code", code)


  const { data } = await webClient.get<UserAuthResponse>(`/auth/wecom/login?${urlSearchParams.toString()}`)
  return data
}

/**
 * 使用 Microsoft Entra 登录
 * @param msalToken 由 Microsoft Entra 提供的用户身份令牌
 */
async function msalLogin(msalToken: string): Promise<UserAuthResponse> {
  const { data, headers } = await webClient.post<UserAuthResponse>(`/auth/msal/login`, {
    msalToken,
  })

  const token = (headers as Record<string, string>).authorization ?? ""

  if (!token) {
    return Promise.reject(new Error("未获取到身份令牌，登录失败"))
  }

  return data
}

/**
 * 获取注册功能是否启用
 */
async function fetchRegisterEnabled() {
  const { data } = await webClient.get<boolean>("/auth/register-enabled")
  return data
}

export { usernamePasswordLogin, wecomLogin, msalLogin, getCaptcha, fetchRegisterEnabled }
