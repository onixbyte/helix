import type { CountryCode as RegionAbbreviation } from "libphonenumber-js"
import type { Status, UserStatus } from "@/types/constant"
import type { User } from "@/types/entity"

export interface PageRequest {
  pageNum?: number
  pageSize?: number
}

export interface UsernamePasswordLoginRequest {
  username: string
  password: string
  captcha?: string
  uuid?: string
}

export interface QueryUserRequest extends PageRequest {
  departmentId: number | null
  username: string | null
  regionAbbreviation: RegionAbbreviation | null
  phoneNumber: string | null
  status: Status | null
  createdAtStart: string | null
  createdAtEnd: string | null
}

export interface QueryPositionRequest extends PageRequest {
}

export interface AddUserRequest extends Omit<User, "id" | "createdAt" | "updatedAt">{
  roleIds: number[] | null
}

export interface EditUserRequest {
  id: number | string
  username: string | null
  fullName: string | null
  email: string | null
  regionAbbreviation: string | null
  phoneNumber: string | null
  avatarUrl: string | null
  status: UserStatus | null
  departmentId: number | null
  positionId: number | null
  roleIds: number[] | null
}

export interface QueryRoleRequest extends PageRequest {
  name: string | null
  code: string | null
  status: Status | null
}


