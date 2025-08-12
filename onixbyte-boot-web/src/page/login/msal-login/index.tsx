import { useMsal } from "@azure/msal-react"
import { useAppDispatch } from "@/store"
import { useNavigate } from "react-router"

export default function MsalLogin() {
  const { instance } = useMsal()
  const dispatch = useAppDispatch()
  const navigate = useNavigate()

  return (
    <div className="bg-[#D0D0D0]">
      <button
        onClick={() => {
          // void doMsalLogin(instance, dispatch, () => void navigate("/"))
        }}
        className="bg-[#00A3EE] text-white px-4 py-2 rounded hover:bg-[#00ADFF]">
        Login with Microsoft Entra ID
      </button>
    </div>
  )
}
