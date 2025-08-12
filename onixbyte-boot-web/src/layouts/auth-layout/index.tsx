import { Link, Outlet } from "react-router"

export default function AuthLayout() {
  return (
    <div
      style={{
        padding: "20px",
        border: "2px solid #007bff",
        borderRadius: "10px",
        maxWidth: "600px",
        margin: "50px auto",
        backgroundColor: "#f9f9f9",
      }}>
      <h2>Choose Your Login Method:</h2>
      <nav style={{ marginBottom: "20px", borderBottom: "1px solid #eee", paddingBottom: "10px" }}>
        <Link to="/login" style={{ marginRight: "15px" }}>
          Standard Login
        </Link>
        <Link to="/login/we-com" style={{ marginRight: "15px" }}>
          WeCom Login
        </Link>
        <Link to="/login/msal">MSAL Login</Link>
      </nav>
      {/* This is where the specific login components (StandardLoginForm, WeComLogin, MsalLogin) will be rendered */}
      <div style={{ borderTop: "1px dashed #ddd", paddingTop: "20px" }}>
        <Outlet />
      </div>
    </div>
  )
}
