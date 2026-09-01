import type { AntSelectOption } from "@/types/antd"

/**
 * Status
 */
export type Status = "ACTIVE" | "INACTIVE"

export type UserStatus = Status | "LOCKED"

export const StatusOptions: AntSelectOption = [
  { label: "已启用", value: "ACTIVE" },
  { label: "已停用", value: "INACTIVE" },
]

export const UserStatusOptions: AntSelectOption = [
  ...StatusOptions,
  { label: "已禁用", value: "LOCKED" },
]
