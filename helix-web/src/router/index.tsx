import { createBrowserRouter } from "react-router"
import ProtectedRoute from "@/components/protected-route"
import LoginPage from "@/page/login"
import RegisterPage from "@/page/register"
import HomePage from "@/page/home"

const router = createBrowserRouter([
  {
    path: "/",
    element: <ProtectedRoute />,
    errorElement: (
      <>
        <div>Something went wrong.</div>
      </>
    ),
    children: [
      {
        index: true,
        element: <HomePage />
      },
    ],
  },
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/register",
    element: <RegisterPage />,
  },
])

export default router
