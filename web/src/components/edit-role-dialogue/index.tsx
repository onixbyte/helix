import type { FormInstance } from "antd"
import RoleDisplayForm, { type RoleFormValues } from "@/components/role-display-form"
import type { Role } from "@/types/entity"

export interface EditRoleDialogueProps {
  form: FormInstance<RoleFormValues>
  initialValues: RoleFormValues
}

export default function EditRoleDialogue({ form, initialValues }: EditRoleDialogueProps) {
  return <RoleDisplayForm form={form} initialValues={initialValues} mode="edit" />
}
