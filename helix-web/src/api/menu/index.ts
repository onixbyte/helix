import webClient from "@/client/web-client"
import type { TreeNode } from "@/types/tree"
import type { MenuItem } from "@/types/entity"

export async function fetchMenuTree() {
  const { data } = await webClient.get<TreeNode<MenuItem>[]>("/menus")
  return data
}
