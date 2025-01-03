import { headers } from "next/headers";
import { AuthData } from "./apiResponseTypes";

const BASE_API_PATH = 'http://localhost:8080';

export const createApiPostRequest = (endpoint: string, request: any, auth: AuthData | null = null) => {

  endpoint = endpoint.replace(/^\/+/g, "")
  endpoint = endpoint.replace(/\/+$/g, "")

  const authHeader : any = {}

  if (auth !== null) authHeader['Authorization'] = `Bearer ${auth.token}`;

  return fetch(`${BASE_API_PATH}/${endpoint}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      ...authHeader
    },
    body: JSON.stringify(request),
  });
}

export const createApiGetRequest = (endpoint: string, auth: AuthData) => {

  endpoint = endpoint.replace(/^\/+/g, "")
  endpoint = endpoint.replace(/\/+$/g, "")

  const authHeader : any = {}

  if (auth !== null) authHeader['Authorization'] = `Bearer ${auth.token}`;

  return fetch(`${BASE_API_PATH}/${endpoint}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      ...authHeader
    },
  });
}