export interface User {
  id: number
  username: string
  name: string
  msalOpenId: string
  dingTalkOpenId: string
  wecomOpenId: string
}

export interface UserResponse {
  user: User
  accessToken: string
}

export type WecomConfig = {
  /**
   * Corporation ID
   */
  corpId: string

  /**
   * Application ID
   */
  agentId: string
}

export interface CaptchaResponse {
  uuid: string
  captcha: string
}

export interface UsernamePasswordLoginRequest {
  username: string
  password: string
  captcha?: string
  uuid?: string
}
