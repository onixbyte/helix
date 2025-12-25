import { useEffect, useState } from "react"
import type { AxiosError } from "axios"
import { App, Button, Form, Input, Select, Space, Switch, Table } from "antd"
import type { Role } from "@/types/entity"
import { RoleApi } from "@/api"
import type { QueryRoleRequest } from "@/types/web/request"
import type { GeneralErrorResponse, RoleResponse } from "@/types/web/response"
import {
  DeleteOutlined,
  ExportOutlined,
  ImportOutlined,
  PlusOutlined,
  SearchOutlined,
  UndoOutlined,
} from "@ant-design/icons"
import type { QueryRoleForm } from "@/types/form"
import type { Status } from "@/types/constant"

export default function RolePage() {
  const { message } = App.useApp()

  const [queryForm] = Form.useForm<QueryRoleForm>()
  const [roles, setRoles] = useState<Role[]>([])
  const [pageNum, setPageNum] = useState<number>(1)
  const [pageSize, setPageSize] = useState<number>(10)
  const [totalElementCount, setTotalElementCount] = useState<number>(0)

  const queryRoles = (pageNum: number, pageSize: number, queryRoleForm: QueryRoleForm | null) => {
    const queryRoleRequest: QueryRoleRequest = {
      code: queryRoleForm?.qCode ?? null,
      name: queryRoleForm?.qName ?? null,
      status: queryRoleForm?.qStatus ?? null,
      pageNum,
      pageSize,
    }

    RoleApi.fetchRoles(queryRoleRequest)
      .then((response) => {
        console.log("role response", response)
        setPageNum(response.pageable.pageNumber + 1)
        setPageSize(response.pageable.pageSize)
        setTotalElementCount(response.totalElements)
        setRoles(response.content.sort((role1, role2) => role1.sort - role2.sort))
      })
      .catch((error) => {
        const err = error as AxiosError<GeneralErrorResponse>
        void message.error(err.response?.data.message ?? "获取角色失败")
      })
  }

  const changeRoleStatus = (roleId: number | string, checked: boolean) => {
    const status: Status = checked ? "ACTIVE" : "INACTIVE"
    setRoles((prevRoles) =>
      prevRoles.map((role) => (role.id === roleId ? { ...role, status } : role))
    )
  }

  useEffect(() => {
    queryRoles(pageNum, pageSize, null)
  }, [pageNum, pageSize])

  return (
    <div className="flex gap-6 flex-col">
      <Form<QueryRoleForm>
        form={queryForm}
        className="mt-0 mb-6"
        layout="inline"
        labelAlign="right"
        onFinish={(values) => {
          queryRoles(pageNum, pageSize, values)
        }}
        onReset={() => {
          queryRoles(pageNum, pageSize, null)
        }}>
        <Form.Item<QueryRoleForm> label="角色名称" name="qName">
          <Input />
        </Form.Item>
        <Form.Item<QueryRoleForm> label="角色编码" name="qCode">
          <Input />
        </Form.Item>
        <Form.Item<QueryRoleForm> label="角色状态" name="qStatus">
          <Select<Status>
            className="w-26"
            options={[
              {
                label: "已启用",
                value: "ACTIVE",
              },
              {
                label: "未启用",
                value: "INACTIVE",
              },
            ]}
          />
        </Form.Item>
        <Form.Item<QueryRoleForm>>
          <Space.Compact>
            <Button color="primary" variant="solid" htmlType="submit" icon={<SearchOutlined />}>
              查询
            </Button>
            <Button color="orange" variant="solid" htmlType="reset" icon={<UndoOutlined />}>
              重置
            </Button>
          </Space.Compact>
        </Form.Item>
      </Form>

      <Space size={8}>
        <Button variant="solid" type="primary" icon={<PlusOutlined />} onClick={() => {}}>
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

      <Table<Role>
        columns={[
          { title: "角色编号", dataIndex: "id" },
          { title: "角色名称", dataIndex: "name" },
          { title: "角色编码", dataIndex: "code" },
          { title: "显示顺序", dataIndex: "sort" },
          {
            title: "状态",
            key: "status",
            render: (role: Role) => (
              <>
                <Switch
                  value={role.status == "ACTIVE"}
                  onChange={(checked) => changeRoleStatus(role.id, checked)}
                />
              </>
            ),
          },
          {
            title: "默认角色",
            key: "defaultValue",
            render: (role: Role) => (
              <>
                <Switch value={role.defaultValue} disabled />
              </>
            ),
          },
          { title: "创建时间", dataIndex: "createdAt" },
          {
            title: "操作",
            render: (role: Role) => (
              <>
                <Space.Compact>
                  <Button variant="solid">修改</Button>
                  <Button variant="solid" danger>
                    删除
                  </Button>
                </Space.Compact>
              </>
            ),
            key: "operations",
          },
        ]}
        dataSource={roles}
        rowKey="id"
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
      />
    </div>
  )
}
