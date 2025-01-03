const MainContent = ({ children }: Readonly<{
  children: React.ReactNode;
}>) => {
  return (
    <main className="max-w-[1200px] w-full pl-24 pr-20 py-9 ml-64">
      {children}
    </main>
  )
}

export default MainContent