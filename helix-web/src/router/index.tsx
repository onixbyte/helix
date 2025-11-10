import { createBrowserRouter } from "react-router"
import ProtectedRoute from "@/components/protected-route"
import LoginPage from "@/page/login"
import RegisterPage from "@/page/register"
import HomePage from "@/page/home"
import ErrorPage from "@/page/error"
import NotFoundPage from "@/page/not-found"

const router = createBrowserRouter([
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/register",
    element: <RegisterPage />,
  },
  {
    path: "/",
    element: <ProtectedRoute />,
    errorElement: <ErrorPage />,
    children: [
      {
        index: true,
        element: <HomePage />,
      },
    ],
  },
  {
    path: "*",
    element: <NotFoundPage />,
  },
])

export default router
