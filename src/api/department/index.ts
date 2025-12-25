import webClient from "@/service/web-client"
import type { TreeNode } from "@/types/tree"
import type { Department } from "@/types/entity"

export async function fetchDepartmentTree(): Promise<TreeNode<Department>> {
  const { data } = await webClient.get<TreeNode<Department>>("/departments/tree")
  return data
}

export async function fetchDepartments(): Promise<Department[]> {
  const { data } = await webClient.get<Department[]>("/departments")
  return data
}
