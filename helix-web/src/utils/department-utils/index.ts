import { type TreeDataNode } from "antd"
import type { TreeNode } from "@/types/tree"
import type { Department } from "@/types/entity"

export function transformDepartmentData(departments: TreeNode<Department>[]): TreeDataNode[] {
  if (!departments || departments.length === 0) {
    return []
  }

  return departments
    .sort((a, b) => a.item.sort - b.item.sort)
    .map((node) => {
      const { item, children } = node
      const hasChildren = children && children.length > 0

      const treeItem: TreeDataNode = {
        key: item.id,
        title: item.name,
      }

      if (hasChildren) {
        // Append children
        return { ...treeItem, children: transformDepartmentData(children) }
      }

      return treeItem
    })
}
