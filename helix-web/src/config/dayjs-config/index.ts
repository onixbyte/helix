// 配置插件
import dayjs from "dayjs"

import duration from "dayjs/plugin/duration"
dayjs.extend(duration)

// 配置语言
import "dayjs/locale/zh-cn"
dayjs.locale("zh-cn")

console.log("Global Dayjs plugins initialised.")
