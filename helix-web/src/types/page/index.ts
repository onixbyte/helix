export interface Sortable {
  empty: boolean
  sorted: boolean
  unsorted: boolean
}

export interface Pageable {
  pageNumber: number
  pageSize: number
  sort: Sortable
  offset: number
  paged: boolean
  unpaged: boolean
}
