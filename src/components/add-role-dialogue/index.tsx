import type { FormInstance } from "antd"
import RoleDisplayForm, { type RoleFormValues } from "@/components/role-display-form"

export interface AddRoleDialogueProps {
  form: FormInstance<RoleFormValues>
}

export default function AddRoleDialogue({ form }: AddRoleDialogueProps) {
  return (
    <RoleDisplayForm
      form={form}
      initialValues={{
        id: null,
        name: "",
        code: "",
        sort: 0,
        description: null,
        defaultValue: false,
        status: "ACTIVE",
      }}
      mode="add"
    />
  )
}
