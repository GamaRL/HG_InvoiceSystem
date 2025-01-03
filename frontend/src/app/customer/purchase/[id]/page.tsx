"use client"

import { useAuth } from "@/hooks/useAuth";
import { PurchaseDetails } from "@/services/apiResponseTypes";
import { getCustomerPurchaseDetails, getCustomerTaxReceipt } from "@/services/customerService";
import { useParams, useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const colors : string[] = ['pink', 'blue', 'lime']

export default function CustomerPurchase() {
  const {credentials ,user, isLoading} = useAuth();
  const [purchaseDetails, setPurchaseDetails] = useState<PurchaseDetails | null>(null);

  const router = useRouter();
  const params = useParams();

  const handleGetReceipt = async () => {
    if (credentials !== null) {
      const url = await getCustomerTaxReceipt(credentials, params.id as string)
      window.open(url, "blank")
    }
  }

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (credentials !== null)
          setPurchaseDetails(await getCustomerPurchaseDetails(credentials, params.id as string))

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

  const price = purchaseDetails?.items
    .reduce((sum, curr) => sum + curr.price * curr.quantity, 0);

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
          <h2 className="font-black my-4">¡Detalles de la compra!</h2>

          <div className="my-4">
            Aquí puedes ver los detalles de la compra que realizaste
          </div>

          <div className="flex justify-end">
            <div>
              <button
                className="block text-sm bg-green-500 text-white p-3 rounded"
                onClick={handleGetReceipt}
                >Mi Factura</button>
            </div>
          </div>

          <div className="container mx-auto p-4">
            <dl className="w-full divide-y divide-gray-200 dark:text-white dark:divide-gray-700">
              {purchaseDetails?.items.map((purchase, index) => (
                <div className="flex justify-between align-middle" key={purchase.product.id.toString()}>
                  <div className="flex flex-col pb-3">
                    <dt className="mb-1 text-black text-lg font-semibold"># {index + 1} - {purchase.product.name}</dt>
                    <dd className="text-lg text-gray-500 font-semibold">{purchase.product.description}</dd>
                    <dd className="text-lg text-gray-500 font-semibold">Cantidad: {purchase.quantity}</dd>
                    <dd className="text-lg text-gray-500 font-semibold">Precio Unitario: ${purchase.price}</dd>
                  </div>
                  <div className="flex h-full align-center">
                    <span className="text-black">
                      ${purchase.price * purchase.quantity}
                    </span>
                  </div>
                </div>
              ))}
              <div className="flex justify-between align-middle">
                <div className="flex flex-col pb-3">
                  <dt className="mb-1 text-black text-lg font-semibold">Subtotal:</dt>
                </div>
                <div className="flex h-full align-center">
                  <span className="text-black">
                    ${price}
                  </span>
                </div>
              </div>
              <div className="flex justify-between align-middle">
                <div className="flex flex-col pb-3">
                  <dt className="mb-1 text-black text-lg font-semibold">IVA:</dt>
                </div>
                <div className="flex h-full align-center">
                  <span className="text-black">
                    ${price && price * 0.16}
                  </span>
                </div>
              </div>
              <div className="flex justify-between align-middle">
                <div className="flex flex-col pb-3">
                  <dt className="mb-1 text-black text-lg font-semibold">Total:</dt>
                </div>
                <div className="flex h-full align-center">
                  <span className="text-black">
                    ${price && price * 1.16}
                  </span>
                </div>
              </div>
            </dl>
          </div>
        </section>
    </>
  )
}