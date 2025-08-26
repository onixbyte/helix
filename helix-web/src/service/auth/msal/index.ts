import type { IPublicClientApplication } from "@azure/msal-browser"
import * as AuthApi from "@/api/auth"
import type { AppDispatch } from "@/store"
import { loginSuccess } from "@/store/auth-slice"

/**
 * Login with Microsoft Entra ID.
 *
 * @param instance Microsoft Entra ID application instance
 * @param dispatch app dispatcher
 * @param onSuccess callback when login succeeded
 */
export async function doMsalLogin(
  instance: IPublicClientApplication,
  dispatch: AppDispatch,
  onSuccess?: () => void
) {
  try {
    const response = await instance.loginPopup({
      scopes: ["openid", "profile", "email"],
    })

    const { token, user } = await AuthApi.msalLogin(response.idToken)
    dispatch(loginSuccess({ user, token }))
    if (onSuccess) onSuccess()
  } catch (err) {
    console.error("MSAL login failed", err)
  }
}
