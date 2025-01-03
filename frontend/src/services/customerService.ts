import { AuthData, CustomerPurchaseData, PurchaseDetails } from "./apiResponseTypes";
import { createApiGetRequest } from "./requestService";

export const getCustomerPurchases = async (auth: AuthData) : Promise<CustomerPurchaseData[]> => {
  const response = await createApiGetRequest('/api/customer/purchase', auth);

  if (!response.ok) {
    throw new Error('Algo salio mal...');
  }

  const jsonData : any[] = await response.json()
  const data : CustomerPurchaseData[] = jsonData.map(d => ({
    id: d.id as number,
    createdAt: new Date(d.createdAt)
  }));
  
  return data;
}

export const getCustomerPurchaseDetails = async (auth: AuthData, purchaseId: number | string) : Promise<PurchaseDetails> => {

  const response = await createApiGetRequest(`/api/customer/purchase/${purchaseId}`, auth);

  if (!response.ok) {
    throw new Error('Algo salio mal...');
  }

  const jsonData : any = await response.json()
  const data : PurchaseDetails = {
    id: jsonData.id,
    createdAt: new Date(jsonData.createdAt),
    items: jsonData.items.map((i: { price: any; quantity: any; product: { id: any; name: any; description: any; }; }) => ({
      price: i.price,
      quantity: i.quantity,
      product: {
        id: i.product.id,
        name: i.product.name,
        description: i.product.description
      }
    }))
  }
  
  return data;
}

export const getCustomerTaxReceipt = async (auth: AuthData, purchaseId: number | string) : Promise<string> => {

  const response = await createApiGetRequest(`/api/bills/${purchaseId}`, auth);

  if (!response.ok) {
    throw new Error('Algo salio mal...');
  }

  const blob = await response.blob();
  const obectUrl = URL.createObjectURL(blob)
  
  return obectUrl;
}