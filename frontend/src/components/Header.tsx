import Image from "next/image"
import Link from "next/link"

const Header = () => {
  return (
    <header className="bg-pink-800 text-white p-4 ml-64 flex items-center justify-stretch">
      <div className="mx-10 my-5">
        <Link href={'/'} className="">
            <Image
              width={50}
              height={50}
              src="/farmacia.png" alt=""/>
        </Link>
      </div>
      <h1 className="text-xl">FarmaFacil</h1>
    </header>
  )
}

export default Header