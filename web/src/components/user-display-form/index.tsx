import { useEffect, useMemo, useState } from "react"
import { App, Checkbox, Form, type FormInstance, Input, Select, Space, TreeSelect } from "antd"
import type { AxiosError } from "axios"
import { getCountries, getCountryCallingCode } from "libphonenumber-js"
import { DeptApi, PositionApi } from "@/api"
import type { UserFormValues } from "@/types/form"
import type { Department, Position } from "@/types/entity"
import type { GeneralErrorResponse } from "@/types/web/response"
import type { AntTreeSelectOption } from "@/types/antd"

interface UserDisplayFormProps {
  initialValues?: UserFormValues
  isEditing: boolean
  isAdding?: boolean
  form: FormInstance<UserFormValues>
}

const countryOptions = getCountries().map((country) => {
  const callingCode = getCountryCallingCode(country)
  return {
    label: `${country} (+${callingCode})`,
    value: country,
    key: country,
  }
})

function transformDepartmentToTree(departments: Department[]): AntTreeSelectOption<number>[] {
  const sortedDepartments = [...departments].sort((a, b) => a.sort - b.sort)

  const map: Record<number, AntTreeSelectOption<number>> = {}
  const tree: AntTreeSelectOption<number>[] = []

  sortedDepartments.forEach((dept) => {
    map[dept.id] = {
      label: dept.name,
      value: dept.id,
      children: [],
    }
  })

  sortedDepartments.forEach((dept) => {
    const node = map[dept.id]
    if (dept.parentId !== null && map[dept.parentId]) {
      map[dept.parentId].children!.push(node)
    } else {
      tree.push(node)
    }
  })
  return tree
}

export default function UserDisplayForm({
  initialValues,
  isEditing,
  form,
  isAdding = false,
}: UserDisplayFormProps) {
  // Get message wrapper from Antd
  const { message } = App.useApp()

  // Build form values.
  const initialFormValues: UserFormValues | undefined = initialValues
    ? ({
        ...initialValues,
        password: "",
      } as UserFormValues)
    : undefined

  // Department state used to save all departments
  const [departments, setDepartments] = useState<Department[]>([])
  // Position state used to save all positions
  const [positions, setPositions] = useState<Position[]>([])

  // Departments options
  const departmentOptions = useMemo(() => {
    return transformDepartmentToTree(departments)
  }, [departments])

  const positionOptions = useMemo(() => {
    return positions.map((position) => ({
      label: position.name,
      value: position.id,
      key: position.id,
    }))
  }, [positions])

  // Initialise form values
  useEffect(() => {
    if (initialFormValues) {
      form.setFieldsValue(initialFormValues)
    } else {
      form.resetFields()
    }
  }, [initialValues, form])

  // Initialise department data on component mounted
  useEffect(() => {
    const fetchDepartmentsFuture = DeptApi.fetchDepartments()
    const fetchPositionsFuture = PositionApi.fetchPositions({ pageNum: 1, pageSize: 999 })

    Promise.all([fetchDepartmentsFuture, fetchPositionsFuture])
      .then(([departments, positionPage]) => {
        setDepartments(departments)
        setPositions(positionPage.content)
      })
      .catch((error: unknown) => {
        const err = error as AxiosError<GeneralErrorResponse>
        void message.error(err.response?.data.message ?? "获取部门或岗位数据失败，请稍后再试")
      })
  }, [])

  return (
    <Form<UserFormValues>
      form={form}
      initialValues={initialFormValues}
      layout="vertical"
      labelAlign="right"
      disabled={!isEditing}>
      <Form.Item<UserFormValues> label="用户 ID" name="id" hidden={isAdding}>
        <Input disabled />
      </Form.Item>

      <Form.Item<UserFormValues>
        label="全名"
        name="fullName"
        rules={[{ required: true, message: "用户全名不能为空" }]}>
        <Input />
      </Form.Item>

      <Form.Item<UserFormValues>
        label="用户名"
        name="username"
        rules={[{ required: true, message: "请输入用户名" }]}>
        <Input />
      </Form.Item>

      {isAdding && (
        <Form.Item<UserFormValues>
          label="密码"
          name="password"
          rules={[{ required: true, message: "请输入密码" }]}>
          <Input.Password />
        </Form.Item>
      )}

      <Form.Item<UserFormValues>
        label="电子邮箱"
        name="email"
        rules={[
          { type: "email", message: "邮箱格式不正确" },
          { required: true, message: "邮箱不能为空" },
        ]}>
        <Input />
      </Form.Item>

      <Form.Item<UserFormValues> label="联系电话" required>
        <Space.Compact className="w-full">
          <Form.Item<UserFormValues>
            noStyle
            name="regionAbbreviation"
            rules={[{ required: true, message: "请选择国际电信区域码" }]}>
            <Select
              options={countryOptions}
              showSearch
              placeholder="国家/地区"
              className="w-[26%]"
            />
          </Form.Item>
          <Form.Item<UserFormValues>
            noStyle
            name="phoneNumber"
            rules={[{ required: true, message: "请输入电话号码" }]}>
            <Input className="w-[74%]" />
          </Form.Item>
        </Space.Compact>
      </Form.Item>

      <Form.Item<UserFormValues>
        label="用户状态"
        name="status"
        rules={[{ required: true, message: "用户状态不能为空" }]}>
        <Select
          options={[
            { label: "已启用", value: "ACTIVE" },
            { label: "已停用", value: "INACTIVE" },
            { label: "已禁用", value: "LOCKED" },
          ]}
        />
      </Form.Item>

      <Form.Item<UserFormValues> label="所在部门" name="departmentId">
        <TreeSelect treeData={departmentOptions} placeholder="选择部门" />
      </Form.Item>

      <Form.Item<UserFormValues> label="岗位" name="positionId">
        <Select options={positionOptions} placeholder="选择岗位" />
      </Form.Item>
    </Form>
  )
}
