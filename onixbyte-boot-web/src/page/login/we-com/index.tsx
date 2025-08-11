import { useEffect, useMemo, useRef } from "react"
import * as wecom from "@wecom/jssdk"
import {
  WWLoginLangType,
  WWLoginPanelSizeType,
  WWLoginRedirectType,
  WWLoginType,
} from "@wecom/jssdk"
import { WeComConfig } from "@/config"
import * as AuthApi from "@/api/auth"
import { AxiosError } from "axios"

export default function WeComLogin() {
  const weComLoginPanel = useMemo(
    () =>
      wecom.createWWLoginPanel({
        el: "#ww_login",
        params: {
          login_type: WWLoginType.corpApp,
          appid: WeComConfig.corpId,
          agentid: WeComConfig.agentId,
          redirect_uri: "https://boot.onixbyte.dev/api/auth/we-com",
          state: "",
          redirect_type: WWLoginRedirectType.callback,
          panel_size: WWLoginPanelSizeType.small,
          lang: WWLoginLangType.zh,
        },
        onLoginSuccess({ code }) {
          AuthApi.weComLogin(code)
            .then(({ user, jsonWebToken }) => {})
        },
        onLoginFail({ errCode, errMsg }) {
          console.log("error", errCode, errMsg)
        },
      }),
    []
  )

  // Create a reference to hold the DOM element
  const weComLoginPanelRef = useRef<HTMLDivElement>(null)

  useEffect(() => {
    // Access the DOM element after the component has mounted
    if (weComLoginPanelRef.current) {
      weComLoginPanelRef.current.appendChild(weComLoginPanel.el)
    }
  }, [weComLoginPanel]) // Empty dependency array ensures this runs only once after mount

  return (
    <div>
      <div id="weComLoginPanel" ref={weComLoginPanelRef}></div>
    </div>
  )
}
