/// <reference types="vite/client" />
interface ViteTypeOptions {
  strictImportMetaEnv: unknown
}

interface ImportMetaEnv {
  /**
   * Server Base URL
   */
  readonly VITE_API_BASE_URL: string

  /**
   * Microsoft Entra ID Client ID
   */
  readonly VITE_MSAL_CLIENT_ID: string

  /**
   * Microsoft Entra ID Tenant ID
   */
  readonly VITE_MSAL_TENANT_ID: string
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
