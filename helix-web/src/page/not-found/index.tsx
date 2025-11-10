import { Link } from "react-router"

/**
 * General page not found page.
 * @constructor
 */
export default function NotFoundPage() {
  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100 font-sans text-gray-800 p-5">
      <div className="text-center bg-white p-10 lg:p-14 rounded-xl shadow-lg md:shadow-xl max-w-xl w-full">
        <h1 className="text-7xl lg:text-8xl text-amber-500 m-0 font-bold tracking-wider">404</h1>
        <h2 className="text-3xl md:text-4xl text-gray-700 -mt-4 mb-6">Page Not Found</h2>
        <p className="text-lg text-gray-600 leading-relaxed mb-9">
          Oh dear, it appears the page you were looking for doesn't exist or has been moved. Perhaps
          you typed the address incorrectly?
        </p>
        <Link
          to="/"
          className="inline-block bg-blue-600 text-white py-3 px-7 rounded-lg no-underline
                     text-lg font-semibold transition-all duration-300 ease-in-out
                     shadow-md shadow-blue-500/20 hover:bg-blue-700 hover:-translate-y-0.5 hover:shadow-lg hover:shadow-blue-500/30">
          Go Back Home
        </Link>
      </div>
    </div>
  )
}
