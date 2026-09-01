import type { FormInstance } from "antd"
import UserDisplayForm from "@/components/user-display-form"
import type { User } from "@/types/entity"
import type { UserFormValues } from "@/types/form"

interface EditUserProps {
  user: User
  form: FormInstance<UserFormValues>
}

export default function EditUserDialogue({ user, form }: EditUserProps) {
  return (
    <UserDisplayForm
      initialValues={{
        ...user,
        password: null
      }}
      isEditing={true}
      form={form}
    />
  )
}
