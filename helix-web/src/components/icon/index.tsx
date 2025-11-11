import type { ForwardRefExoticComponent } from "react"
import Icon from "@ant-design/icons"

import BrandMicrosoft from "@/assets/microsoft.svg?react"
import BrandDingTalk from "@/assets/dingtalk.svg?react"
import BrandWeCom from "@/assets/wecom.svg?react"
import BrandLark from "@/assets/lark.svg?react"
import BrandSlack from "@/assets/slack.svg?react"
import BrandGoogle from "@/assets/google.svg?react"
import BrandGitlab from "@/assets/gitlab.svg?react"
import BrandEmail from "@/assets/email.svg?react"
import BrandDiscord from "@/assets/discord.svg?react"
import type { CustomIconComponentProps } from "@/types"

export function MicrosoftFilled(props: Partial<CustomIconComponentProps>) {
  return <Icon component={BrandMicrosoft as ForwardRefExoticComponent<unknown>} {...props} />
}

export function DingTalkFilled(props: Partial<CustomIconComponentProps>) {
  return <Icon component={BrandDingTalk as ForwardRefExoticComponent<unknown>} {...props} />
}

export function WeComFilled(props: Partial<CustomIconComponentProps>) {
  return <Icon component={BrandWeCom as ForwardRefExoticComponent<unknown>} {...props} />
}

export function LarkFilled(props: Partial<CustomIconComponentProps>) {
  return <Icon component={BrandLark as ForwardRefExoticComponent<unknown>} {...props} />
}

export function SlackFilled(props: Partial<CustomIconComponentProps>) {
  return <Icon component={BrandSlack as ForwardRefExoticComponent<unknown>} {...props} />
}

export function GoogleFilled(props: Partial<CustomIconComponentProps>) {
  return <Icon component={BrandGoogle as ForwardRefExoticComponent<unknown>} {...props} />
}

export function GitlabFilled(props: Partial<CustomIconComponentProps>) {
  return <Icon component={BrandGitlab as ForwardRefExoticComponent<unknown>} {...props} />
}

export function EmailFilled(props: Partial<CustomIconComponentProps>) {
  return <Icon component={BrandEmail as ForwardRefExoticComponent<unknown>} {...props} />
}

export function DiscordFilled(props: Partial<CustomIconComponentProps>) {
  return <Icon component={BrandDiscord as ForwardRefExoticComponent<unknown>} {...props} />
}
