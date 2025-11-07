import { createBrowserRouter } from "react-router"
import ProtectedRoute from "@/components/protected-route"
import Login, { MsalLogin, UsernameAndPasswordLogin } from "@/page/login"
import Register from "@/page/register"

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
    ]
  },
  {
    path: "/login",
    element: <Login />,
    children: [
      {
        path: "username-password",
        element: <UsernameAndPasswordLogin />
      },
      {
        path: "msal",
        element: <MsalLogin />
      }
    ]
  },
  {
    path: "/register",
    element: <Register />
  }
])

export default router
