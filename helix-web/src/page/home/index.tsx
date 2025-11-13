import { useAppSelector } from "@/store"

export default function HomePage() {
  const user = useAppSelector((store) => store.auth.user)

  return <>Welcome to Helix, {user!.fullName}</>
}
