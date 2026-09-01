import { type Key, useEffect, useState } from "react"
import {
  Avatar,
  Button,
  DatePicker,
  Form,
  Input,
  Select,
  Space,
  Table,
  Tag,
  Tree,
  type TreeDataNode,
  Typography,
} from "antd"
import { App } from "antd"
import { type AxiosError } from "axios"
import { getCountries, getCountryCallingCode } from "libphonenumber-js"
import { DeptApi, UserApi } from "@/api"
import { PhoneNumberUtils, DepartmentUtils } from "@/utils"
import {
  DeleteOutlined,
  EditOutlined,
  ExportOutlined,
  ImportOutlined,
  KeyOutlined,
  PlusOutlined,
  SearchOutlined,
  UndoOutlined,
} from "@ant-design/icons"
import AddUserDialogue from "@/components/add-user-dialogue"
import EditUserDialogue from "@/components/edit-user-dialogue"
import type { QueryUserRequest } from "@/types/web/request"
import type { QueryUserForm, UserFormValues } from "@/types/form"
import type { GeneralErrorResponse, UserDetailResponse } from "@/types/web/response"
import type { Department } from "@/types/entity"
import type { TreeNode } from "@/types/tree"
import { useAppSelector } from "@/store"

export default function UserPage() {
  const { message, modal } = App.useApp()

  const user = useAppSelector((state) => state.auth.user!)

  const [queryForm] = Form.useForm<QueryUserForm>()
  const [addUserForm] = Form.useForm<UserFormValues>()
  const [editUserForm] = Form.useForm<UserFormValues>()
  const [department, setDepartment] = useState<TreeNode<Department>>()
  const [departmentTree, setDepartmentTree] = useState<TreeDataNode[]>([])
  const [selectedDepartment, setSelectedDepartment] = useState<number>(1)
  const [users, setUsers] = useState<UserDetailResponse[]>([])
  const [pageNum, setPageNum] = useState<number>(1)
  const [totalElementCount, setTotalElementCount] = useState<number>(0)
  const [pageSize, setPageSize] = useState<number>(10)

  const onAddUserFinish = async () => {
    try {
      const values = await addUserForm.validateFields()

      await UserApi.addUser(values)

      void message.success(`用户 ${values.username} 创建成功`)
      return true
    } catch (error: unknown) {
      console.log(error)
      if (error instanceof Error && error.message.includes("Validation Failed")) {
        return false
      }
      const err = error as AxiosError<GeneralErrorResponse>
      void message.error(err.response?.data.message ?? "创建失败，请稍后再试")
      return false
    }
  }

  const handleAddUser = () => {
    modal
      .confirm({
        title: "添加用户",
        content: <AddUserDialogue form={addUserForm} />,
        width: 600,
        onOk: onAddUserFinish,
      })
      .then(
        () => {
          const formValues = queryForm.getFieldsValue()
          queryUsers(pageNum, pageSize, selectedDepartment!, formValues)
        },
        () => {
          console.error("用户取消添加用户")
        }
      )
  }

  const onEditUserFinish = async () => {
    try {
      const values = await editUserForm.validateFields()

      await UserApi.editUser(values)

      void message.success(`用户更新成功`)
      return true
    } catch (error: unknown) {
      if (error instanceof Error && error.message.includes("Validation Failed")) {
        return false
      }
      const err = error as AxiosError<GeneralErrorResponse>
      void message.error(err.response?.data.message ?? "更新失败，请稍后再试")
      return false
    }
  }

  const handleEditUser = (user: UserDetailResponse) => {
    modal
      .confirm({
        title: `编辑用户: ${user.username}`,
        content: <EditUserDialogue form={editUserForm} user={user} />,
        width: 600,
        onOk: onEditUserFinish,
      })
      .then(
        () => {
          const formValues = queryForm.getFieldsValue()
          queryUsers(pageNum, pageSize, selectedDepartment, formValues)
        },
        () => {
          console.error("用户取消添加用户")
        }
      )
  }

  const handleDeleteUser = (user: UserDetailResponse) => {
    const isLastElementOnPage = users.length === 1 && pageNum > 1
    modal.confirm({
      title: (
        <>
          确认删除用户 <Typography.Text code>{user.username}</Typography.Text> 吗？
        </>
      ),
      content: "删除后数据将无法恢复。",
      okText: "删除",
      cancelText: "取消",
      okButtonProps: { danger: true },
      onOk: async () => {
        try {
          await UserApi.deleteUser(user.id)
          void message.success(`用户 ${user.username} 删除成功`)
          setTotalElementCount((count) => count - 1)
          if (isLastElementOnPage) {
            setPageNum((prevPage) => prevPage - 1)
          } else {
            queryUsers(pageNum, pageSize, selectedDepartment, queryForm.getFieldsValue())
          }
        } catch (error: unknown) {
          const err = error as AxiosError<GeneralErrorResponse>
          void message.error(err.response?.data.message ?? "删除失败，请稍后再试")
        }
      },
    })
  }

  // Query users
  const queryUsers = (
    pageNum: number,
    pageSize: number,
    departmentId: number,
    formValues: QueryUserForm | null
  ) => {
    const queryUserRequest: QueryUserRequest = {
      createdAtEnd: null,
      createdAtStart: null,
      phoneNumber: null,
      regionAbbreviation: null,
      status: null,
      username: null,
      pageNum,
      pageSize,
      departmentId,
    }

    if (formValues && formValues.qUsername) {
      queryUserRequest.username = formValues.qUsername
    }

    if (formValues && formValues.qRegionAbbreviation) {
      queryUserRequest.regionAbbreviation = formValues.qRegionAbbreviation
    }

    if (formValues && formValues.qPhoneNumber) {
      queryUserRequest.phoneNumber = formValues.qPhoneNumber
    }

    if (formValues && formValues.qStatus) {
      queryUserRequest.status = formValues.qStatus
    }

    if (formValues && formValues.qCreatedAtStart) {
      queryUserRequest.createdAtStart = formValues.qCreatedAtStart.format("YYYY-MM-DD HH:mm:ss")
    }

    if (formValues && formValues.qCreatedAtEnd) {
      queryUserRequest.createdAtEnd = formValues.qCreatedAtEnd.format("YYYY-MM-DD HH:mm:ss")
    }

    console.log(`queryUsers ==> `, queryUserRequest)

    UserApi.fetchUsers(queryUserRequest)
      .then((userResponse) => {
        console.log(userResponse)
        setUsers(userResponse.content)
        setPageNum(userResponse.pageable.pageNumber + 1)
        setPageSize(userResponse.pageable.pageSize)
        setTotalElementCount(userResponse.totalElements)
      })
      .catch((error: unknown) => {
        const err = error as AxiosError<GeneralErrorResponse>
        void message.error(err.response?.data.message ?? "发生未知错误，请稍后再试")
      })
  }

  useEffect(() => {
    DeptApi.fetchDepartmentTree()
      .then((departmentResponse) => {
        setDepartment(departmentResponse)
        setDepartmentTree(DepartmentUtils.transformDepartmentData([departmentResponse]))
      })
      .catch((error: unknown) => {
        const err = error as AxiosError<GeneralErrorResponse>
        void message.error(err.response?.data.message ?? "发生未知错误，请稍后再试")
      })
  }, [])

  useEffect(() => {
    const formValues = queryForm.getFieldsValue()
    queryUsers(pageNum, pageSize, selectedDepartment, formValues)
  }, [pageNum, pageSize, selectedDepartment])

  const regionOptions = getCountries().map((country) => {
    const callingCode = getCountryCallingCode(country)
    return {
      label: `${country} (+${callingCode})`,
      value: country,
      key: country,
    }
  })

  const userStatusOptions = [
    {
      label: "已启用",
      value: "ACTIVE",
      key: "ACTIVE",
    },
    {
      label: "已停用",
      value: "INACTIVE",
      key: "INACTIVE",
    },
    {
      label: "已禁用",
      value: "LOCKED",
      key: "LOCKED",
    },
  ]

  return (
    <div className="flex h-full gap-2">
      <Tree
        multiple={false}
        treeData={departmentTree}
        classNames={{
          item: "w-64",
        }}
        onSelect={([selectedKey]) => {
          if (typeof selectedKey == "number") {
            setSelectedDepartment(selectedKey)
          }
        }}
        selectedKeys={[selectedDepartment]}
      />
      <div className="flex flex-col w-full">
        <Form<QueryUserForm>
          className="mt-0 mb-6"
          form={queryForm}
          layout="inline"
          labelAlign="right"
          onFinish={(values) => {
            console.log("Query User Form ==> ", values)
            queryUsers(pageNum, pageSize, Number(selectedDepartment), values)
          }}
          onReset={() => {
            queryUsers(pageNum, pageSize, Number(selectedDepartment), null)
          }}>
          <Form.Item<QueryUserForm> label="用户名" name="qUsername">
            <Input className="w-40" />
          </Form.Item>

          <Form.Item<QueryUserForm> label="用户状态" name="qStatus">
            <Select
              options={userStatusOptions}
              className="w-24"
              allowClear
              onSelect={(item: unknown, option) => {
                console.log(`selectedItem = ${item} ${option}`)
              }}
            />
          </Form.Item>

          <Form.Item label="手机号码">
            <Space.Compact>
              <Form.Item<QueryUserForm> noStyle name="qRegionAbbreviation">
                <Select
                  options={regionOptions}
                  className="w-28"
                  allowClear
                  showSearch={{
                    optionFilterProp: "label",
                  }}
                  placeholder="国家/地区"
                />
              </Form.Item>

              <Form.Item<QueryUserForm> noStyle name="qPhoneNumber">
                <Input className="w-40" />
              </Form.Item>
            </Space.Compact>
          </Form.Item>

          <Form.Item label="创建时间">
            <Space.Compact className="w-full align-baseline">
              <Form.Item<QueryUserForm> name="qCreatedAtStart">
                <DatePicker showTime={true} showNow={true} format="YYYY-MM-DD HH:mm:ss" />
              </Form.Item>
              <Form.Item<QueryUserForm> name="qCreatedAtEnd">
                <DatePicker showTime={true} showNow={true} format="YYYY-MM-DD HH:mm:ss" />
              </Form.Item>
            </Space.Compact>
          </Form.Item>

          <Form.Item>
            <Space size={8}>
              <Button htmlType="submit" type="primary" icon={<SearchOutlined />}>
                查询
              </Button>
              <Button
                htmlType="reset"
                type="primary"
                icon={<UndoOutlined />}
                onClick={() => {
                  if (department) {
                    // Set department to first root department
                    const { item } = department
                    setSelectedDepartment(item.id)
                  } else {
                    void message.error("未获取到任何部门信息")
                  }
                }}>
                重置
              </Button>
            </Space>
          </Form.Item>
        </Form>

        <Space size={8} className="mb-6">
          <Button variant="solid" type="primary" icon={<PlusOutlined />} onClick={handleAddUser}>
            新增
          </Button>
          <Button variant="solid" danger icon={<DeleteOutlined />}>
            删除
          </Button>
          <Button variant="solid" color="green" icon={<ImportOutlined />}>
            导入
          </Button>
          <Button variant="solid" color="green" icon={<ExportOutlined />}>
            导出
          </Button>
        </Space>

        <Table<UserDetailResponse>
          rowKey="id"
          dataSource={users}
          pagination={{
            current: pageNum,
            total: totalElementCount,
            pageSize: pageSize,
            defaultCurrent: 1,
            defaultPageSize: 10,
            pageSizeOptions: [10, 25, 50],
            showSizeChanger: true,
            onShowSizeChange: (pageNum, pageSize) => {
              console.log(`onSizeChange ==> pageNum = ${pageNum}, pageSize = ${pageSize}`)
              setPageNum(pageNum)
              setPageSize(pageSize)
            },
            onChange: (pageNum, pageSize) => {
              console.log(`onChange ==> pageNum = ${pageNum}, pageSize = ${pageSize}`)
              setPageNum(pageNum)
              setPageSize(pageSize)
            },
          }}
          columns={[
            { title: "用户 ID", dataIndex: "id" },
            { title: "用户名称", dataIndex: "username" },
            { title: "姓名", dataIndex: "fullName" },
            { title: "电子邮箱", dataIndex: "email" },
            {
              title: "联系电话",
              render: (user: UserDetailResponse) => {
                return PhoneNumberUtils.formatInternationalPhoneNumber(
                  user.regionAbbreviation,
                  user.phoneNumber
                )
              },
              key: "phoneNumber",
            },
            {
              title: "头像",
              render: (user: UserDetailResponse) => {
                return <Avatar src={user.avatarUrl} alt="用户头像" />
              },
              key: "avatarUrl",
            },
            {
              title: "用户状态",
              render: (user: UserDetailResponse) => {
                const colour = user.status == "ACTIVE" ? "green" : "red"
                return (
                  <Tag color={colour} key={`${user.id}-${user.status}`} variant="outlined">
                    {user.status}
                  </Tag>
                )
              },
              key: "status",
            },
            { title: "所在部门", dataIndex: "departmentName" },
            { title: "用户岗位", dataIndex: "positionName" },
            {
              title: "操作",
              render: (value: UserDetailResponse) => {
                return String(value.id) == String(user.id) ? (
                  <></>
                ) : (
                  <Space.Compact>
                    <Button icon={<EditOutlined />} onClick={() => handleEditUser(value)}>
                      编辑
                    </Button>
                    <Button icon={<KeyOutlined />} onClick={() => {}}>
                      重置密码
                    </Button>
                    <Button
                      danger
                      icon={<DeleteOutlined />}
                      onClick={() => handleDeleteUser(value)}>
                      删除
                    </Button>
                  </Space.Compact>
                )
              },
            },
          ]}
        />
      </div>
    </div>
  )
}
