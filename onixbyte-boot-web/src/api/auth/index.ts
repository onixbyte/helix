import webClient from "@/service/web-client"
import type { User, UserResponse } from "@/types"

export async function weComLogin(code: string): Promise<UserResponse> {
  const urlSearchParams = new URLSearchParams()
  urlSearchParams.append("code", code)

  const { data, headers } = await webClient.get<User>(`/auth/we-com/login?${urlSearchParams}`)

  const authorisationHeader = headers["authorization"] as string || ""

  return {
    user: data,
    jsonWebToken: authorisationHeader
  }
}

export async function weComRegister(code: string): Promise<UserResponse> {
  const urlSearchParams = new URLSearchParams()
  urlSearchParams.append("code", code)

  const { data, headers } = await webClient.get<User>(`/auth/we-com/register?${urlSearchParams}`)

  const authorisationHeader = headers["authorization"] as string || ""

  return {
    user: data,
    jsonWebToken: authorisationHeader
  }
}
