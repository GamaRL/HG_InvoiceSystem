"use client"

import { LoginRequest } from "./apiRequestTypes";
import { AuthData, UserData } from "./apiResponseTypes";
import { createApiGetRequest, createApiPostRequest } from "./requestService";

export const getStoredAuthInfo = () => {
  const storage = localStorage.getItem('auth') || ''
  return <AuthData>JSON.parse(storage)
}

export const storeAuthInfo = (info: AuthData) => {
  const storage = JSON.stringify(info)
  localStorage.setItem('auth', storage)
}

export const removeAuthInfo = () => {
  localStorage.removeItem('auth');
}

export const requestLogin = async (request: LoginRequest) : Promise<AuthData> => {
  const response = await createApiPostRequest('/auth/login', request);

  if (response.status === 401) {
    throw new Error('Credenciales incorrectas');
  } else if (!response.ok) {
    throw new Error('Algo salió mal...')
  }

  const data = <AuthData> await response.json();
  storeAuthInfo(data)

  return data;
}

export const validateToken = async (auth: AuthData) : Promise<UserData> => {
  const response = await createApiGetRequest('/auth', auth);

  return response.json() as Promise<UserData>;
}

export const logOut = async () : Promise<void> => {
  removeAuthInfo();
}