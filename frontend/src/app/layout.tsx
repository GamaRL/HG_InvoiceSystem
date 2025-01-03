import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { AuthProvider } from "../hooks/useAuth";
import Header from "../components/Header";
import MainContent from "../components/MainContent";
import SideMenu from "../components/SideMenu";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Sistema de Facturación",
  description: "Proyecto final Temas Selectos de Ingeniería en Computación",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="es">
      <body className={inter.className}>
        <AuthProvider>
          <Header/>
          <MainContent>
            <SideMenu/>
            {children}
          </MainContent>
        </AuthProvider>
      </body>
    </html>
  );
}