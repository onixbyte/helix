import { App, Form, type FormInstance, Input, InputNumber, Select, Switch } from "antd"
import { type Status, StatusOptions } from "@/types/constant"
import type { FormMode } from "@/types/form"
import { useEffect, useMemo } from "react"

/**
 * Role form values.
 */
export interface RoleFormValues {
  id: number | string | null
  name: string
  code: string
  sort: number
  defaultValue: boolean
  description: string | null
  status: Status
}

/**
 * Component props.
 */
export interface RoleDisplayFormProps {
  initialValues?: RoleFormValues
  form: FormInstance<RoleFormValues>
  mode: FormMode
}

export default function RoleDisplayForm({ initialValues, form, mode }: RoleDisplayFormProps) {
  const isEditing = useMemo<boolean>(() => mode == "edit", [mode])

  // Initialise form values
  useEffect(() => {
    if (initialValues) {
      form.setFieldsValue(initialValues)
    } else {
      form.resetFields()
    }
  }, [initialValues, form])

  return (
    <Form<RoleFormValues>
      form={form}
      initialValues={initialValues}
      layout="vertical"
      labelAlign="right"
      validateTrigger="onBlur">
      <Form.Item<RoleFormValues> label="角色编号" hidden={!isEditing} name="id">
        <Input disabled />
      </Form.Item>
      <Form.Item<RoleFormValues>
        label="角色名称"
        name="name"
        rules={[{ required: true, message: "角色名称不能为空" }]}>
        <Input />
      </Form.Item>
      <Form.Item<RoleFormValues>
        label="角色编码"
        name="code"
        rules={[
          { required: true, message: "角色编码不能为空" },
          { pattern: /^[a-z-]+$/, message: "角色编码格式错误，仅支持小写英文字母及 '-'" },
        ]}>
        <Input />
      </Form.Item>
      <Form.Item<RoleFormValues>
        label="排序编码"
        name="sort"
        rules={[
          { required: true, message: "排序不能为空" },
          { type: "number", min: 0, max: Number.MAX_VALUE, message: "排序必须是正数" },
        ]}>
        <InputNumber />
      </Form.Item>
      <Form.Item<RoleFormValues> label="是否为默认角色" name="defaultValue">
        <Switch disabled={isEditing}/>
      </Form.Item>
      <Form.Item<RoleFormValues> label="角色状态" name="status">
        <Select<Status> options={StatusOptions} />
      </Form.Item>
    </Form>
  )
}
