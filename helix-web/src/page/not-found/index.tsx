import { Link } from "react-router"

/**
 * General page not found page.
 * @constructor
 */
export default function NotFoundPage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center p-4">
      <div className="absolute top-0 left-0 w-72 h-72 bg-blue-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>
      <div className="absolute top-0 right-0 w-72 h-72 bg-purple-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>
      <div className="absolute bottom-0 left-1/2 w-72 h-72 bg-pink-200 rounded-full mix-blend-multiply filter blur-xl opacity-70"></div>

      <div className="text-center bg-white p-10 lg:p-14 rounded-xl shadow-lg md:shadow-xl max-w-xl w-full">
        <h1 className="text-7xl lg:text-8xl text-amber-500 m-0 font-bold tracking-wider">404</h1>
        <h2 className="text-3xl md:text-4xl text-gray-700 mt-4 mb-6">找不到页面</h2>
        <p className="text-lg text-gray-600 leading-relaxed mb-9">
          找不到您需要的页面，您是否输入了错误的地址？
        </p>
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
