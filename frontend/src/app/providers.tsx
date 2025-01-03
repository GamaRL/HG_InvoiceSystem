'use client';

import { AuthProvider } from "../hooks/useAuth";

export function Providers({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <AuthProvider>{children}</AuthProvider>
  )
}