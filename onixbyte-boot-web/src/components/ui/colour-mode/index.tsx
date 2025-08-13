import type { IconButtonProps, SpanProps } from "@chakra-ui/react"
import { ClientOnly, IconButton, Skeleton, Span } from "@chakra-ui/react"
import { ThemeProvider, useTheme } from "next-themes"
import type { ThemeProviderProps } from "next-themes"
import * as React from "react"
import { LuMoon, LuSun } from "react-icons/lu"

export interface ColourModeProviderProps extends ThemeProviderProps {}

export function ColourModeProvider(props: ColourModeProviderProps) {
  return <ThemeProvider attribute="class" disableTransitionOnChange {...props} />
}

export type Index = "light" | "dark"

export interface UseColourModeReturn {
  colourMode: Index
  setColourMode: (colourMode: Index) => void
  toggleColourMode: () => void
}

export function useColourMode(): UseColourModeReturn {
  const { resolvedTheme, setTheme, forcedTheme } = useTheme()
  const colourMode = forcedTheme || resolvedTheme
  const toggleColourMode = () => {
    setTheme(resolvedTheme === "dark" ? "light" : "dark")
  }
  return {
    colourMode: colourMode as Index,
    setColourMode: setTheme,
    toggleColourMode,
  }
}

export function useColourModeValue<T>(light: T, dark: T) {
  const { colourMode } = useColourMode()
  return colourMode === "dark" ? dark : light
}

export function ColourModeIcon() {
  const { colourMode } = useColourMode()
  return colourMode === "dark" ? <LuMoon /> : <LuSun />
}

interface ColourModeButtonProps extends Omit<IconButtonProps, "aria-label"> {}

export const ColourModeButton = React.forwardRef<HTMLButtonElement, ColourModeButtonProps>(
  function ColourModeButton(props, ref) {
    const { toggleColourMode } = useColourMode()
    return (
      <ClientOnly fallback={<Skeleton boxSize="8" />}>
        <IconButton
          onClick={toggleColourMode}
          variant="ghost"
          aria-label="Toggle colour mode"
          size="sm"
          ref={ref}
          {...props}
          css={{
            _icon: {
              width: "5",
              height: "5",
            },
          }}>
          <ColourModeIcon />
        </IconButton>
      </ClientOnly>
    )
  }
)

export const LightMode = React.forwardRef<HTMLSpanElement, SpanProps>(
  function LightMode(props, ref) {
    return (
      <Span
        color="fg"
        display="contents"
        className="chakra-theme light"
        colorPalette="gray"
        colorScheme="light"
        ref={ref}
        {...props}
      />
    )
  }
)

export const DarkMode = React.forwardRef<HTMLSpanElement, SpanProps>(function DarkMode(props, ref) {
  return (
    <Span
      color="fg"
      display="contents"
      className="chakra-theme dark"
      colorPalette="gray"
      colorScheme="dark"
      ref={ref}
      {...props}
    />
  )
})
