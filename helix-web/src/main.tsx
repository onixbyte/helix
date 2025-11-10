import { StrictMode } from "react"
import { createRoot } from "react-dom/client"
import { Provider as ReduxProvider } from "react-redux"
import { RouterProvider } from "react-router"
import store from "@/store"
import "./index.css"
import router from "@/router"

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <ReduxProvider store={store}>
      <RouterProvider router={router} />
    </ReduxProvider>
  </StrictMode>
)
