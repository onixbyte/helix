import { createSlice, type PayloadAction } from "@reduxjs/toolkit"
import type { User } from "@/types/entity"

interface AuthState {
  isAuthenticated: boolean
  user: User | null
  token: string | null,
  registrationEnabled: boolean
}

const initialState: AuthState = {
  isAuthenticated: false,
  user: null,
  token: null,
  registrationEnabled: false
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
      console.log("更新用户信息：", action.payload.user)
      state.isAuthenticated = true
      state.user = action.payload.user
      state.token = action.payload.token
    },
    logout(state) {
      state.isAuthenticated = false
      state.user = null
      state.token = null
    },
    updateRegistrationEnabled(state, action: PayloadAction<boolean>) {
      state.registrationEnabled = action.payload
    }
  },
})

export const { loginSuccess, logout, updateRegistrationEnabled } = authSlice.actions
export default authSlice.reducer
