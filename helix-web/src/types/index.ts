import type { GetProps } from "antd"
import type Icon from "@ant-design/icons"

export interface User {
  id: number
  username: string
  fullName: string
  email: string
  countryCode: string
  phoneNumber: string
  avatarUrl: string
  status: "ACTIVE" | "INACTIVE"
  departmentId: number
  positionId: number
  createdAt: string
  updatedAt: string
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

export interface GeneralErrorResponse {
  message: string
  timestamp: string
}

export type CustomIconComponentProps = GetProps<typeof Icon>
