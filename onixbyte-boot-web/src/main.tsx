import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import { Provider } from "react-redux"
import { BrowserRouter, Route, Routes } from "react-router"
import store from "@/store"
import "./index.css"
import ProtectedRoute from "@/components/protected-route"
import AuthLayout from "@/layouts/auth-layout"
import Login from "@/page/login"
import WeComLogin from "@/page/login/we-com"
import MsalLogin from "@/page/login/msal-login"

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Provider store={store}>
      <BrowserRouter>
        <Routes>
          <Route path="login" element={<AuthLayout />}>
            <Route index element={<Login />} />
            <Route path="we-com" element={<WeComLogin />}></Route>
            <Route path="msal" element={<MsalLogin />}></Route>
          </Route>
          <Route path="/" element={<ProtectedRoute />}></Route>
        </Routes>
      </BrowserRouter>
    </Provider>
  </StrictMode>
)
