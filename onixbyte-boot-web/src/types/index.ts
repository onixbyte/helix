export interface User {
  id: number
  username: string
  name: string
  msalOpenId: string
  dingTalkOpenId: string
  weComOpenId: string
}

export interface UserResponse {
  user: User
  jsonWebToken: string
}
