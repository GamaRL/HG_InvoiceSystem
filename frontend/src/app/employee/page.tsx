"use client"

import { useAuth } from '@/hooks/useAuth';
import { EmployeePaymentData } from '@/services/apiResponseTypes';
import { getEmployeePaymentReceipt, getEmployeePayments } from '@/services/employeeService';
import Link from 'next/link';
import router from 'next/router';
import React, { useEffect, useState } from 'react'

export default function PaysheetPage () {

  const {credentials ,user, isLoading} = useAuth();
  const [paymentsData, setPaymentsData] = useState<EmployeePaymentData[]>([]);

  const handleGetReceipt = async (id: string | number) => {
    if (credentials !== null) {
      const url = await getEmployeePaymentReceipt(credentials, id as string)
      window.open(url, "blank")
    }
  }

  useEffect(() => {
    const fetchData = async () => {
      try {
        if (credentials !== null)
          setPaymentsData(await getEmployeePayments(credentials))

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

  return (
    <>
        <section className="card text-justify text-2xl">
          <h2 className="font-black my-4">¡Historial de nómina!</h2>

          <div className="my-4">
            Aquí puedes ver tus comprobantes de nómina
          </div>

          <div className="container mx-auto p-4">
            <dl className="w-full divide-y divide-gray-200 dark:text-white dark:divide-gray-700">
              {paymentsData.map((payment, index) => (
                <div className="flex justify-between align-middle" key={payment.createdAt.toDateString()}>
                  <div className="flex flex-col pb-3">
                    <dt className="mb-1 text-black text-lg font-semibold">ID {payment.id}</dt>
                    <dd className="text-lg text-gray-500 font-semibold">Fecha: {payment.createdAt.toDateString()}</dd>
                    <dd className="text-lg text-gray-500 font-semibold">Hora: {payment.createdAt.getHours()} : {payment.createdAt.getMinutes()}</dd>
                    <dd className="text-lg text-gray-500 font-semibold">Monto: ${payment.amount}</dd>
                  </div>
                  <div className="block align-center">

                    <div>
                      <button
                        className="block text-sm bg-indigo-500 text-white p-3 rounded"
                        onClick={() => handleGetReceipt(payment.id)}
                        >Comprobante</button>
                    </div>
                  </div>
                </div>
              ))}
            </dl>
          </div>
        </section>
    </>
  )

}