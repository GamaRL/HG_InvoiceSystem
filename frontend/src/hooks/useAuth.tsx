"use client"

import { createContext, ReactNode, useContext, useEffect, useState } from "react"
import { AuthData, UserData } from "../services/apiResponseTypes";
import { LoginRequest } from "../services/apiRequestTypes";
import { getStoredAuthInfo as getStoredAuthData, logOut, requestLogin, validateToken } from "../services/authService";

interface AuthHook {
  credentials: AuthData | null,
  user: UserData | null,
  isLoading: boolean,
  signin: (req :LoginRequest) => Promise<void>,
  signout: () => void,
}

const authContext = createContext<AuthHook>({
  credentials: null,
  user: null,
  isLoading: true,
  async signin(req) { },
  signout() {},
});

export function AuthProvider({ children, }: Readonly<{ children: ReactNode; }>) {
  const auth = useProvideAuth();

  return <authContext.Provider value={auth}>{children}</authContext.Provider>
}

export const useAuth = () => {
  return useContext(authContext)
}

function useProvideAuth() {
  
  const [isLoading, setIsLoading] = useState(true)
  const [credentials, setCredentials] = useState<AuthData | null>(null)
  const [user, setUser] = useState<UserData | null>(null);

  const verifyToken: () => void = async () => {
    const storage = localStorage.getItem('auth')

    if (storage === null) {
      setIsLoading(false);
    } else {

      try {
        const authData: AuthData = getStoredAuthData();
        const userData: UserData = await validateToken(authData)

        if (userData) {
          setCredentials(authData)
          setUser(userData)
        }
      } catch(error: any) {
        if (error.status === 403) {
          signout()
          setUser(null)
          setCredentials(null)
        }
      } finally {
        setIsLoading(false);
      }
    }
  }

  const signin = async ({email, password}: LoginRequest) => {
    const response = await requestLogin({ email, password })
    setCredentials(response)
  }

  const signout = () => {
    logOut()
    setCredentials(null)
    setUser(null)
  }

  useEffect(() => {
    verifyToken()
  }, [credentials?.token])

  return {
    user,
    credentials,
    isLoading,
    signin,
    signout
  }
}
