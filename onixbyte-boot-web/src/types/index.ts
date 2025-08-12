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
  token: string
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
