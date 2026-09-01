import type { ComponentType } from "react"
import { createBrowserRouter } from "react-router-dom"
import ErrorPage from "@/page/error"
import LoadingFallback from "@/components/loading-fallback"

/**
 * 懒加载组件
 * @param importer
 */
function lazyLoading<T extends { default: ComponentType<unknown> }>(importer: () => Promise<T>) {
  return async () => {
    const module = await importer()
    return {
      Component: module.default,
    }
  }
}

const router = createBrowserRouter([
  {
    path: "/login",
    lazy: lazyLoading(() => import("@/page/login")),
    handle: {
      label: "用户登录",
    },
    hydrateFallbackElement: <LoadingFallback />,
  },
  {
    path: "/register",
    lazy: lazyLoading(() => import("@/page/register")),
    handle: {
      label: "用户注册",
    },
    hydrateFallbackElement: <LoadingFallback />,
  },
  {
    path: "/",
    lazy: lazyLoading(() => import("@/components/protected-route")),
    errorElement: <ErrorPage />,
    handle: {
      label: "控制台",
    },
    hydrateFallbackElement: <LoadingFallback />,
    children: [
      {
        index: true,
        lazy: lazyLoading(() => import("@/page/home")),
        handle: {
          label: "用户主页",
        },
        hydrateFallbackElement: <LoadingFallback />,
      },
      {
        path: "users",
        lazy: lazyLoading(() => import("@/page/user")),
        handle: {
          label: "用户管理",
        },
        hydrateFallbackElement: <LoadingFallback />,
      },
      {
        path: "roles",
        lazy: lazyLoading(() => import("@/page/role")),
        handle: {
          label: "角色管理",
        },
        hydrateFallbackElement: <LoadingFallback />,
      },
      {
        path: "menus",
        lazy: lazyLoading(() => import("@/page/menu")),
        handle: {
          label: "菜单管理",
        },
        hydrateFallbackElement: <LoadingFallback />,
      },
    ],
  },
  {
    path: "*",
    lazy: lazyLoading(() => import("@/page/not-found")),
    hydrateFallbackElement: <LoadingFallback />,
  },
])

export default router
