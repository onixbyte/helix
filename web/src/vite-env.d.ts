/// <reference types="vite/client" />
/// <reference types="vite-plugin-svgr/client" />

import type { CountryCode as RegionAbbreviation } from "libphonenumber-js"

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

  /**
   * Default region for new users
   */
  readonly VITE_DEFAULT_REGION_ABBREVIATION: RegionAbbreviation

  /**
   * Application title, can be set to any preferred string as your wish.
   */
  readonly VITE_APP_TITLE: string
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
