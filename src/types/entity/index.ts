import type { Status, UserStatus } from "@/types/constant"
import type { CountryCode as RegionAbbreviation } from "libphonenumber-js"
import type { Dayjs } from "dayjs"

/**
 * User information
 */
export interface User {
  id: number
  username: string
  password: string
  fullName: string
  email: string
  regionAbbreviation: RegionAbbreviation
  phoneNumber: string
  avatarUrl: string
  status: UserStatus
  departmentId: number
  positionId: number
  createdAt: string
  updatedAt: string
}

/**
 * Menu Item
 */
export interface MenuItem {
  id: number
  name: string
  parentId: number | null
  code: string
  sort: number
  isExternalLink: boolean
  isVisible: boolean
  status: Status
  authorityCode: string | null
  icon: string | null
  createdAt: string
  updatedAt: string
}

export interface Department {
  id: number
  name: string
  parentId: number | null
  sort: number
  status: Status
  createdAt: string
  updatedAt: string
}

export interface Position {
  id: number
  name: string
  code: string | null
  description: string | null
  sort: number
  status: Status
  createdAt: string
  updatedAt: string
}

export interface Role {
  id: number | string
  name: string
  code: string
  sort: number
  defaultValue: boolean
  description: string | null
  status: Status
  createdAt: Dayjs | string
  updatedAt: Dayjs | string
}
