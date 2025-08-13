import { useEffect, useMemo, useRef } from "react"
import * as wecom from "@wecom/jssdk"
import {
  WWLoginLangType,
  WWLoginPanelSizeType,
  WWLoginRedirectType,
  WWLoginType,
} from "@wecom/jssdk"
import config from "@/config"
import * as AuthApi from "@/api/auth"

export default function WeComLogin() {
  const loginPanel = useMemo(
    () =>
      wecom.createWWLoginPanel({
        el: "#ww_login",
        params: {
          login_type: WWLoginType.corpApp,
          appid: config.wecom.corpId,
          agentid: config.wecom.agentId,
          redirect_uri: "https://boot.onixbyte.dev/api/auth/we-com",
          state: "",
          redirect_type: WWLoginRedirectType.callback,
          panel_size: WWLoginPanelSizeType.small,
          lang: WWLoginLangType.zh,
        },
        onLoginSuccess({ code }) {
          AuthApi.wecomLogin(code)
            .then((response) => {})
            .catch((error: unknown) => {})
        },
        onLoginFail({ errCode, errMsg }) {
          console.log("error", errCode, errMsg)
        },
      }),
    []
  )

  // Create a reference to hold the DOM element
  const loginPanelRef = useRef<HTMLDivElement>(null)

  useEffect(() => {
    if (loginPanelRef.current) {
      loginPanelRef.current.appendChild(loginPanel.el)
    }
  }, [loginPanel])

  return (
    <div className="flex justify-center">
      <div id="weComLoginPanel" ref={loginPanelRef}></div>
    </div>
  )
}
