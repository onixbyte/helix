import { configureStore } from "@reduxjs/toolkit"
import { useDispatch, useSelector } from "react-redux"

const store = configureStore({
  reducer: {

  }
})

export default store
export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch
export type AppStore = typeof store

export const useAppDispatch = useDispatch.withTypes<AppDispatch>()
export const useAppSelector = useSelector.withTypes<RootState>()