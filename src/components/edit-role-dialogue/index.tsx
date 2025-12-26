import type { FormInstance } from "antd"
import RoleDisplayForm, { type RoleFormValues } from "@/components/role-display-form"

export interface EditRoleDialogueProps {
  form: FormInstance<RoleFormValues>
  initialValues: RoleFormValues
}

export default function EditRoleDialogue({ form, initialValues }: EditRoleDialogueProps) {
  return <RoleDisplayForm form={form} initialValues={initialValues} />
}
