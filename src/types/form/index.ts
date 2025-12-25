import type { User } from "@/types/entity"
import type { CountryCode as RegionAbbreviation } from "libphonenumber-js"
import type { Status } from "@/types/constant"
import type { Dayjs } from "dayjs"

export interface UserFormValues extends Omit<
  User,
  "id" | "password" | "regionAbbreviation" | "departmentId" | "positionId" | "createdAt" | "updatedAt"
> {
  id: number | string | null
  password: string | null
  regionAbbreviation: RegionAbbreviation | null
  departmentId: number | null
  positionId: number | null
}

export interface QueryRoleForm {
  qName: string | null
  qCode: string | null
  qStatus: Status | null
}

export interface QueryUserForm {
  qDepartmentId: number | null
  qUsername: string | null
  qRegionAbbreviation: RegionAbbreviation | null
  qPhoneNumber: string | null
  qStatus: Status | null
  qCreatedAtStart: Dayjs | null
  qCreatedAtEnd: Dayjs | null
}
