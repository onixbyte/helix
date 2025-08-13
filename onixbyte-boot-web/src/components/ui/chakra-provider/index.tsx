import { ChakraProvider, defaultSystem } from "@chakra-ui/react"
import {
  ColourModeProvider,
  type ColourModeProviderProps,
} from "@/components/ui/colour-mode"

export function Index(props: ColourModeProviderProps) {
  return (
    <ChakraProvider value={defaultSystem}>
      <ColourModeProvider {...props} />
    </ChakraProvider>
  )
}
