/**
 * Get application title.
 */
export function getAppTitle(): string {
  return import.meta.env.VITE_APP_TITLE ?? "Helix"
}
