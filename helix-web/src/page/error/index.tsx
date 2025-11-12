import React from "react"
import { Link } from "react-router-dom"

/**
 * General error page, with a custom error message.
 *
 * @param message custom error message, default to `发生了未知错误，请稍后再试`
 * @constructor
 */
export default function ErrorPage({ message = "发生了未知错误，请稍后再试" }) {
  return (
    <div className="flex justify-center items-center min-h-screen bg-red-50 font-sans text-gray-800 p-5">
      <div className="text-center bg-white p-10 lg:p-14 rounded-xl shadow-lg md:shadow-xl max-w-xl w-full">
        <h1 className="text-5xl md:text-6xl mb-5 leading-none">⚠️</h1>
        <h2 className="text-3xl md:text-4xl text-red-600 m-0 font-bold mb-4">
          系统出现了一些异常。
        </h2>
        <p className="text-lg text-gray-700 leading-relaxed mb-4">{message}</p>
        <p className="text-base text-gray-600 mb-8">我们已经知晓该问题并正在进行处理。</p>
        <Link
          to="/"
          className="inline-block bg-blue-600 text-white py-3 px-7 rounded-lg no-underline
                     text-lg font-semibold transition-all duration-300 ease-in-out
                     shadow-md shadow-blue-500/20 hover:bg-blue-700 hover:-translate-y-0.5 hover:shadow-lg hover:shadow-blue-500/30">
          返回主页
        </Link>
      </div>
    </div>
  )
}
