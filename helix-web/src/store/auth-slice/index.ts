import { createSlice, type PayloadAction } from "@reduxjs/toolkit"
import type { User } from "@/types"

interface AuthState {
  isAuthenticated: boolean
  user: User | null
  token: string | null
}

const initialState: AuthState = {
  isAuthenticated: false,
  user: null,
  token: null,
}

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    loginSuccess(
      state,
      action: PayloadAction<{
        user: User
        token: string
      }>
    ) {
      state.isAuthenticated = true
      state.user = action.payload.user
      state.token = action.payload.token
    },
    logout(state) {
      state.isAuthenticated = false
      state.user = null
      state.token = null
    },
  },
})

export const { loginSuccess } = authSlice.actions
export default authSlice.reducer
