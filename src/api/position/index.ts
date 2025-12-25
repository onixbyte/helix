import webClient from "@/service/web-client"
import type { QueryPositionRequest } from "@/types/web/request"
import type { PageResponse } from "@/types/web/response"
import type { Position } from "@/types/entity"

export async function fetchPositions(request: QueryPositionRequest): Promise<PageResponse<Position>> {
  const { data } = await webClient.get<PageResponse<Position>>("/positions")
  return data
}
