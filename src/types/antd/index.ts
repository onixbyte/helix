import type { GetProps } from "antd"
import type Icon from "@ant-design/icons"

export type AntIconComponentProps = GetProps<typeof Icon>

export interface AntTreeSelectOption<T> {
  value: T
  label: string
  children: AntTreeSelectOption<T>[]
  disabled?: boolean
  disableCheckbox?: boolean
  selectable?: boolean
  checkable?: boolean
}

export interface AntSelectOptionItem {
  label: string
  value: string
}

export type AntSelectOption = AntSelectOptionItem[]
