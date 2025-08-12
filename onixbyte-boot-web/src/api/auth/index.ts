import webClient from "@/service/web-client"
import type { User, UserResponse } from "@/types"

/**
 * Login with WeCom.
 * @param code wecom authorisation code
 */
async function wecomLogin(code: string): Promise<UserResponse> {
  const urlSearchParams = new URLSearchParams()
  urlSearchParams.append("code", code)

  const { data, headers } = await webClient.get<User>(`/auth/wecom/login?${urlSearchParams}`)

  const token = (headers as Record<string, string>).authorization ?? ""

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
    msalToken
  })

  const token = (headers as Record<string, string>).authorization ?? ""

  return {
    user: data,
    token,
  }
}

export { wecomLogin, msalLogin }
