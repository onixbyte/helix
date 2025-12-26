import type { QueryRoleRequest } from "@/types/web/request"
import webClient from "@/service/web-client"
import type { PageResponse, RoleResponse } from "@/types/web/response"
import type { RoleFormValues } from "@/components/role-display-form"

export async function fetchRoles(
  request: QueryRoleRequest | null
): Promise<RoleResponse> {
  const params = new URLSearchParams()
  params.append("pageNum", `${request?.pageNum ?? 1}`)
  params.append("pageSize", `${request?.pageSize ?? 1}`)

  if (request?.name) {
    params.append("name", request.name)
  }

  if (request?.code) {
    params.append("code", request.code)
  }

  if (request?.status) {
    params.append("status", request.status)
  }

  const { data } = await webClient.get<RoleResponse>(`/roles?${params.toString()}`)
  return data
}

export async function addRole(request: RoleFormValues) {
  return await webClient.post("/roles", request)
}

export async function editRole(request: RoleFormValues) {
  return await webClient.put("/roles", request)
}
