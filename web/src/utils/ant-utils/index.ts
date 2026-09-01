import type { AntFormValidationError } from "@/types/antd"

export function getFieldErrorMessage(error: AntFormValidationError<unknown>): string {
  return error.errorFields.map((errorField) => errorField.errors.join("，")).join("；")
}
