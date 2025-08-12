import { type Configuration, PublicClientApplication } from "@azure/msal-browser"

const clientId = import.meta.env.VITE_MSAL_CLIENT_ID
const tenantId = import.meta.env.VITE_MSAL_TENANT_ID

const msalConfig: Configuration = {
  auth: {
    clientId,
    authority: `https://login.microsoftonline.com/${tenantId}`,
    redirectUri: "http://localhost:5173"
  },
  cache: {
    cacheLocation: "localStorage",
  }
}

export const msalInstance = new PublicClientApplication(msalConfig)