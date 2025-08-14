import { useRef, useState } from "react"

export default function UsernameAndPasswordLogin() {
  const loginFormRef = useRef<HTMLFormElement>(null)

  const [username, setUsername] = useState<string>("")

  return (
    <div className="flex justify-center">
      <form>

      </form>
    </div>
  )
}
