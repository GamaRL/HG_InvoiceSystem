"use client"

export default function Home() {
  return (
    <>
        <div className="ml-64">
          <section className="card text-justify text-2xl">
            <h2 className="font-black">¡Presentamos el Sistema de Facturación de FarmaFacil!</h2>

            Olvídate de las esperas y filas para facturar. Con nuestro sistema, tú mismo puedes generar tu factura de forma rápida y sencilla en solo unos pasos.

            <hr className="h-px my-8 bg-gray-200 border-0 dark:bg-gray-700" />

            <h2 className="font-black">¿Cómo funciona?</h2>

            <ul className="list-decimal px-12">
              <li>Ingresa tus datos y los de la compra.</li>
              <li>Selecciona los productos que deseas adquirir.</li>
              <li>Revisa tu resumen de compra y confirma.</li>
              <li>¡Listo! Descarga tu factura en formato electrónico.</li>
            </ul>

            <hr className="h-px my-8 bg-gray-200 border-0 dark:bg-gray-700" />

            <h2 className="font-black">Beneficios:</h2>
            <ul className="list-decimal px-12">
              <li>Ahorra tiempo y evita filas.</li>
              <li>Facturación rápida y segura.</li>
              <li>Acceso a tu historial de compras.</li>
              <li>Contribuyes al cuidado del medio ambiente.</li>
            </ul>

            <hr className="h-px my-8 bg-gray-200 border-0 dark:bg-gray-700" />

            <h2 className="font-black">
              ¡Empieza a disfrutar de los beneficios de la autofacturación en tu farmacia!
            </h2>

            ¿Te queda alguna duda? No dudes en contactarnos.
          </section>
        </div>
    </>
  );
}
