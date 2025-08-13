import { Link, Outlet } from "react-router"
import passwordLogo from "@/assets/password.svg"
import microsoftLogo from "@/assets/microsoft.svg"
import wecomLogo from "@/assets/wecom.svg"
import dingTalkLogo from "@/assets/dingtalk.svg"
import UsernameAndPasswordLogin from "./username-and-password"
import MsalLogin from "./msal-login"
import WeComLogin from "./we-com"
import DingTalkLogin from "./ding-talk"

export default function Login() {
  return (
    <div className="p-5 border-2 border-[#007bff] rounded-[10px] max-w-[660px] my-[50px] mx-auto bg-[#f9f9f9]">
      <nav className="flex justify-center gap-4">
        <Link to="/login" className="hover:text-blue-300">
          <img src={passwordLogo} alt="Password" className="inline-block w-[24px]" />
          Username and Password
        </Link>
        <Link to="/login/msal" className="hover:text-blue-300 flex gap-1">
          <img src={microsoftLogo} alt="Microsoft" className="inline-block w-[24px]" />
          Microsoft Entra ID
        </Link>
        <Link to="/login/we-com" className="hover:text-blue-300 flex gap-1">
          <img src={wecomLogo} alt="Wecom" className="inline-block w-[24px]" />
          Wecom
        </Link>
        <Link to="/login/ding-talk" className="hover:text-blue-300 flex gap-1">
          <img src={dingTalkLogo} alt="DingTalk" className="inline-block w-[24px]" />
          DingTalk
        </Link>
      </nav>
      <div className="border-t border-dashed border-[#dddddd] pt-5">
        <Outlet />
      </div>
    </div>
  )
}

export { UsernameAndPasswordLogin, MsalLogin, WeComLogin, DingTalkLogin }
