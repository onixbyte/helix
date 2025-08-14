import { type MouseEvent, useRef, useState } from "react"

export default function UsernameAndPasswordLogin() {
  const loginFormRef = useRef<HTMLFormElement>(null)

  const [username, setUsername] = useState<string>("")
  const [password, setPassword] = useState<string>("")

  const performLogin = (event: MouseEvent) => {
    event.preventDefault()

    console.log(username, password)
  }

  return (
    <div className="flex justify-center">
      <fieldset className="fieldset bg-base-200 border-base-300 rounded-box w-xs border p-4">
        <legend className="fieldset-legend">Login</legend>

        <label className="label" htmlFor="txbUsername">
          Username
        </label>
        <input
          id="txbUsername"
          type="email"
          className="input"
          placeholder="Email"
          value={username}
          onChange={(event) => {
            setUsername(event.target.value)
          }}
        />

        <label className="label" htmlFor="txbPassword">
          Password
        </label>
        <input
          id="txbPassword"
          type="password"
          className="input"
          placeholder="Password"
          onChange={(event) => {
            setPassword(event.target.value)
          }}
        />

        <button className="btn btn-neutral mt-4" onClick={performLogin}>
          Login
        </button>
      </fieldset>
    </div>
  )
}
