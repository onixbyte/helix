import type { ComponentType } from "react"
import { createBrowserRouter } from "react-router-dom"
import ErrorPage from "@/page/error"

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
  },
  {
    path: "/register",
    lazy: lazyLoading(() => import("@/page/register")),
  },
  {
    path: "/",
    lazy: lazyLoading(() => import("@/components/protected-route")),
    errorElement: <ErrorPage />,
    children: [
      {
        index: true,
        lazy: lazyLoading(() => import("@/page/home")),
      },
    ],
  },
  {
    path: "*",
    lazy: lazyLoading(() => import("@/page/not-found")),
  },
])

export default router
