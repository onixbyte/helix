import { Navigate, Outlet, useLocation } from "react-router"
import { useAppSelector } from "@/store"

/**
 * 需要身份验证的前置组件
 * @constructor
 */
export default function ProtectedRoute() {
  const isAuthenticated = useAppSelector((state) => state.auth.isAuthenticated)
  const location = useLocation()

  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />
  }

  return <Outlet />
}
