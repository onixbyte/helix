import { type CountryCode as RegionAbbreviation, getCountryCallingCode, parsePhoneNumberWithError, PhoneNumber } from "libphonenumber-js"

/**
 * Format user's phone number as user's country/region.
 *
 * @param regionAbbreviation user's region abbreviation
 * @param phoneNumber user's phone number
 * @return formatted phone number
 */
export function formatInternationalPhoneNumber(regionAbbreviation: string, phoneNumber: string): string {
  try {
    const _phoneNumber = parsePhoneNumberWithError(phoneNumber, regionAbbreviation as RegionAbbreviation)

    if (!_phoneNumber.isValid()) {
      console.warn(`Phone number ${_phoneNumber.formatInternational()} is not valid`)
    }
    return _phoneNumber.formatInternational()
  } catch (error) {
    console.error("Phone number parsing failed:", error)
    const _regionAbbreviation: RegionAbbreviation = regionAbbreviation as RegionAbbreviation
    const callingCode = getCountryCallingCode(_regionAbbreviation)
    return `+${callingCode} ${phoneNumber}`
  }
}

export function getDefaultCountryCode(): RegionAbbreviation {
  return import.meta.env.VITE_DEFAULT_REGION_CODE
}
