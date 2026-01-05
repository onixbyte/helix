import React, { useEffect, useMemo, useState } from "react"
import { useNavigate } from "react-router"
import {
  App,
  Avatar,
  Breadcrumb,
  Dropdown,
  Layout,
  Menu,
  type MenuProps,
  message,
  Space,
} from "antd"
import { DownOutlined } from "@ant-design/icons"
import { ApplicationLogo } from "@/components/icon"
import { useAppDispatch, useAppSelector } from "@/store"
import { useAntBreadcrumbs } from "@/hooks"
import { logout } from "@/store/auth-slice"
import { MenuApi } from "@/api"
import axios, { type AxiosError } from "axios"
import type { TreeNode } from "@/types/tree"
import type { MenuItem } from "@/types/entity"
import { AppUtils } from "@/utils"
import type { GeneralErrorResponse } from "@/types/web/response"

const { Header, Footer, Sider, Content } = Layout
type AntMenuItem = Required<MenuProps>["items"][number]

function transformMenuData(nodes: TreeNode<MenuItem>[]): AntMenuItem[] {
  if (!nodes || nodes.length === 0) {
    return []
  }

  return nodes
    .sort((a, b) => a.item.sort - b.item.sort)
    .map((node) => {
      const { item, children } = node
      const hasChildren = children && children.length > 0

      const menuItem: AntMenuItem = {
        key: item.code,
        label: item.name,
      }

      if (hasChildren) {
        // Append children
        return { ...menuItem, children: transformMenuData(children) }
      }

      return menuItem
    })
}

export default function DashboardLayout({ children }: { children: React.ReactNode }) {
  const { modal, message } = App.useApp()
  const user = useAppSelector((store) => store.auth.user!)
  const dispatch = useAppDispatch()
  const breadcrumbItems = useAntBreadcrumbs()
  const navigate = useNavigate()

  const onLogout = ({ key }: { key: string }) => {
    if (key == "logout") {
      modal.confirm({
        title: "确定要注销吗？",
        okText: "确定",
        cancelText: "取消",
        onOk: () => {
          dispatch(logout())
          void navigate("/login")
        },
        maskClosable: false,
        keyboard: false,
      })
    }
  }

  const [menuItems, setMenuItems] = useState<AntMenuItem[]>([])

  useEffect(() => {
    MenuApi.fetchMenuTree()
      .then((response) => {
        setMenuItems(transformMenuData(response))
      })
      .catch((error: unknown) => {
        console.log(error)
        const errorMessage =
          axios.isAxiosError<GeneralErrorResponse>(error) && error.response?.data.message
            ? error.response?.data.message
            : "无法读取菜单数据"
        void message.error({
          content: errorMessage,
        })
      })
  }, [])

  const dropDownMenuItems: MenuProps["items"] = [
    {
      key: "logout",
      danger: true,
      label: "注销",
    },
  ]

  const appTitle = useMemo<string>(() => AppUtils.getAppTitle(), [])

  return (
    <Layout className="h-full">
      <Header className="flex items-center justify-between bg-linear-to-br from-blue-50 to-indigo-100">
        <div className="flex gap-4 items-center">
          <ApplicationLogo className="text-4xl" />
          <span className="text-xl">{appTitle}</span>
        </div>

        <div className="flex gap-4 items-center">
          <Dropdown
            className="text-white text-xl"
            menu={{ items: dropDownMenuItems, onClick: onLogout }}>
            <Space>
              {user.fullName}
              <DownOutlined />
            </Space>
          </Dropdown>
          <Avatar src={user.avatarUrl} alt="用户头像" />
        </div>
      </Header>
      <Layout>
        <Sider width={200} className="bg-white">
          <Menu
            mode="inline"
            className="h-full max-h-full border-e-0"
            items={menuItems}
            onSelect={({ key }) => {
              console.log(`key = ${key}`)
              switch (key) {
                case "user-mgmt":
                  void navigate("/users")
                  break
                case "role-mgmt":
                  void navigate("/roles")
                  break
                case "menu-mgmt":
                  void navigate("/menus")
                  break
              }
            }}
          />
        </Sider>
        <Layout className="pt-0 px-6 pb-6">
          <Breadcrumb items={breadcrumbItems} className="my-4 mx-0" />
          <Content className="p-6 m-0 min-h-70 bg-white">{children}</Content>
        </Layout>
      </Layout>
    </Layout>
  )
}
