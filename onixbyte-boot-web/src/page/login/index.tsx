import { Link, Outlet } from "react-router"
import passwordLogo from "@/assets/password.svg"
import microsoftLogo from "@/assets/microsoft.svg"
import UsernameAndPasswordLogin from "./username-and-password"
import MsalLogin from "./msal-login"

export default function Login() {
  return (
    <div className="card bg-base-100 shadow-sm max-w-[660px] my-[50px] mx-auto">
      <nav className="flex justify-center gap-4 navbar bg-base-100 shadow-sm">
        <Link to="/login" className="hover:text-blue-300">
          <img src={passwordLogo} alt="Password" className="inline-block w-[24px]" />
          Username and Password
        </Link>
        <Link to="/login/msal" className="hover:text-blue-300 flex gap-1">
          <img src={microsoftLogo} alt="Microsoft" className="inline-block w-[24px]" />
          Microsoft Entra ID
        </Link>
      </nav>
      <div className="card-body">
        <Outlet />
      </div>
    </div>
  )
}

export { UsernameAndPasswordLogin, MsalLogin }
