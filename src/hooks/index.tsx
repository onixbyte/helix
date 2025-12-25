import { useMatches } from "react-router"
import type { BreadcrumbItemType } from "antd/es/breadcrumb/Breadcrumb"
import type { RouteHandle } from "@/types/route"

export function useAntBreadcrumbs(): BreadcrumbItemType[] {
  const matches = useMatches()

  return matches
    .filter((match) => (match.handle as RouteHandle)?.label)
    .map((match) => {
      const handle = match.handle as RouteHandle

      const path = match.pathname
      const title = handle.label || ""

      return {
        title,
      }
    })
}
