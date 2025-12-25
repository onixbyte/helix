import type { Role, User } from "@/types/entity"
import type { Pageable, Sortable } from "@/types/page"

export interface PageResponse<T> {
  content: T[]
  last: boolean
  totalPages: number
  totalElements: number
  size: number
  sort: Sortable
  first: boolean
  numberOfElements: number
  empty: boolean,
  pageable: Pageable
}

export interface UserAuthResponse {
  user: User
  accessToken: string
}

export interface UserDetailResponse extends User {
  departmentName: string
  positionName: string
}

export interface CaptchaResponse {
  uuid: string
  captcha: string
}

export interface GeneralErrorResponse {
  message: string
  timestamp: string
}

export interface RoleResponse extends PageResponse<Role> {
}

