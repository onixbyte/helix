import webClient from "@/client/web-client"
import { getCountryCallingCode } from "libphonenumber-js"
import { getDefaultCountryCode } from "@/utils/phone-number-utils"
import type { AddUserRequest, EditUserRequest, QueryUserRequest } from "@/types/web/request"
import type { PageResponse, UserDetailResponse } from "@/types/web/response"
import type { UserFormValues } from "@/types/form"

export async function fetchUsers(
  request?: QueryUserRequest
): Promise<PageResponse<UserDetailResponse>> {
  const urlSearchParam = new URLSearchParams()
  urlSearchParam.append("pageNum", `${request?.pageNum ?? 1}`)
  urlSearchParam.append("pageSize", `${request?.pageSize ?? 10}`)

  if (request?.departmentId) {
    urlSearchParam.append("departmentId", `${request.departmentId}`)
  }

  if (request?.username) {
    urlSearchParam.append("username", request.username)
  }

  if (request?.regionAbbreviation) {
    urlSearchParam.append("regionAbbreviation", request.regionAbbreviation)
  }

  if (request?.phoneNumber) {
    urlSearchParam.append("phoneNumber", request.phoneNumber)
  }

  if (request?.status) {
    urlSearchParam.append("status", request.status)
  }

  if (request?.createdAtStart) {
    urlSearchParam.append("createdAtStart", request.createdAtStart)
  }

  if (request?.createdAtEnd) {
    urlSearchParam.append("createdAtEnd", request.createdAtEnd)
  }

  const { data } = await webClient.get<PageResponse<UserDetailResponse>>(
    `/users?${urlSearchParam.toString()}`
  )
  return data
}

export async function fetchUserById(userId: number): Promise<UserDetailResponse> {
  const { data } = await webClient.get<UserDetailResponse>(`/users/${userId}`)
  return data
}

export async function addUser(values: UserFormValues) {
  await webClient.post("/users", {
    username: values.username,
    password: values.password,
    fullName: values.fullName,
    email: values.email,
    regionAbbreviation: values.regionAbbreviation ?? getDefaultCountryCode(),
    phoneNumber: values.phoneNumber,
    avatarUrl: values.avatarUrl,
    status: values.status,
    departmentId: values.departmentId,
    positionId: values.positionId,
    roleIds: [],
  } as AddUserRequest)
}

export async function editUser(values: UserFormValues) {
  await webClient.put("/users", {
    id: values.id,
    username: values.username,
    fullName: values.fullName,
    email: values.email,
    regionAbbreviation: values.regionAbbreviation ?? getDefaultCountryCode(),
    phoneNumber: values.phoneNumber,
    avatarUrl: values.avatarUrl,
    status: values.status,
    departmentId: values.departmentId,
    positionId: values.positionId,
    roleIds: [],
  } as EditUserRequest)
}

export async function deleteUser(userId: number | string) {
  await webClient.delete(`/users/${userId}`)
}
