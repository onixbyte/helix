import { useMsal } from "@azure/msal-react"
import { useAppDispatch } from "@/store"
import { useNavigate } from "react-router"

export default function MsalLogin() {
  const { instance } = useMsal()
  const dispatch = useAppDispatch()
  const navigate = useNavigate()

  return (
    <div className="flex justify-center">
      <button
        onClick={() => {
          // void doMsalLogin(instance, dispatch, () => void navigate("/"))
        }}
        className="btn btn-outline btn-primary">
        Login with Microsoft Entra ID
      </button>
    </div>
  )
}
