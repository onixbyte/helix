import type { WecomConfig } from "@/types"

const wecomConfig: WecomConfig = {
  corpId: import.meta.env.VITE_WE_COM_CORP_ID,
  agentId: import.meta.env.VITE_WE_COM_AGENT_ID
}

/**
 * WeCom configuration.
 */
export default wecomConfig
