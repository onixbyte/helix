import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import { Provider as ReduxProvider } from "react-redux"
import { BrowserRouter, Route, Routes } from "react-router"
import store from "@/store"
import "./index.css"
import ProtectedRoute from "@/components/protected-route"
import Login, { MsalLogin, UsernameAndPasswordLogin } from "@/page/login"

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <ReduxProvider store={store}>
      <BrowserRouter>
        <Routes>
          <Route path="login" element={<Login />}>
            <Route index element={<UsernameAndPasswordLogin />} />
            <Route path="msal" element={<MsalLogin />}></Route>
          </Route>
          <Route path="/" element={<ProtectedRoute />}></Route>
        </Routes>
      </BrowserRouter>
    </ReduxProvider>
  </StrictMode>
)
