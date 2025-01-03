"use client"

import { useAuth } from "@/hooks/useAuth";
import { CustomerPurchaseData } from "@/services/apiResponseTypes";
import { getCustomerPurchases } from "@/services/customerService";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const colors : string[] = ['pink', 'blue', 'lime']

export default function PurchaseDetails() {
  const {credentials ,user, isLoading} = useAuth();
  const [customerPurchaseData, setCustomerPurchaseData] = useState<CustomerPurchaseData[]>([]);
  const router = useRouter();

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (credentials !== null)
          setCustomerPurchaseData(await getCustomerPurchases(credentials))

        if (user?.role !== "CUSTOMER")
          router.push("/");
      } catch (error: any) {
        console.error(error);
      }
    }

    if (!isLoading && credentials === null) {
      router.push('/login')
    } else {
      fetchData()
    }
  }, [credentials])

  if (isLoading || user === null)
    return (
      <>
          <div>
            Cargando...
          </div>
      </>
    )

    

  return (
    <>
        <section className="card text-justify text-2xl">
          <h2 className="font-black my-4">¡Historial de compras!</h2>

          <div className="my-4">
            Aquí puedes ver tu historial de compra
          </div>

          <div className="container mx-auto p-4">
            <dl className="w-full divide-y divide-gray-200 dark:text-white dark:divide-gray-700">
              {customerPurchaseData.map((purchase, index) => (
                <div className="flex justify-between align-middle" key={purchase.createdAt.toDateString()}>
                  <div className="flex flex-col pb-3">
                    <dt className="mb-1 text-black text-lg font-semibold">ID {purchase.id}</dt>
                    <dd className="text-lg text-gray-500 font-semibold">Fecha: {purchase.createdAt.toDateString()}</dd>
                    <dd className="text-lg text-gray-500 font-semibold">Hora: {purchase.createdAt.getHours()} : {purchase.createdAt.getMinutes()}</dd>
                  </div>
                  <div className="block align-center">
                    <Link
                      href={`/customer/purchase/${purchase.id}`}
                      className={`block bg-green-500 hover:bg-green-400 text-white p-2 m-4 text-sm rounded shadow-md`}>
                      Ver más detalles
                    </Link>
                  </div>
                </div>
              ))}
            </dl>
          </div>
        </section>
    </>
  )
}