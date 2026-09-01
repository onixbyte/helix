import { type FormInstance } from "antd"
import UserDisplayForm from "@/components/user-display-form"
import type { UserFormValues } from "@/types/form"

interface AddUserProps {
  form: FormInstance<UserFormValues>
}

export default function AddUserDialogue({ form }: AddUserProps) {
  return (
    <UserDisplayForm
      isEditing={true}
      form={form}
      initialValues={{
        id: null,
        username: "",
        password: "",
        regionAbbreviation: import.meta.env.VITE_DEFAULT_REGION_CODE,
        fullName: "",
        email: "",
        phoneNumber: "",
        avatarUrl: "",
        status: "ACTIVE",
        departmentId: null,
        positionId: null,
      }}
      isAdding
    />
  )
}
