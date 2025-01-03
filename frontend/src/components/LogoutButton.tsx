import { useAuth } from "../hooks/useAuth";
import { AuthData } from "../../services/apiResponseTypes";

export default function LoginButton() {

  const {signout} = useAuth();

  const handleClick = () => {
    signout();
  }

  return (
    <div className="flex justify-center my-5">
      <button
        type="button"
        onClick={handleClick}
        className={"bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-700"}
      >
        <div className="flex justify-center align-middle">
          <span className="mx-1">
            Logout
          </span>
        </div>
      </button>
    </div>
  )
}