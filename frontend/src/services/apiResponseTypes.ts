export interface AuthData {
  readonly token: string;
}

export interface UserData {
  readonly id: number;
  readonly name: string;
  readonly email: string;
  readonly rfc: string;
  readonly role: "ADMIN" | "CUSTOMER" | "EMPLOYEE";
}

export interface CustomerPurchaseData {
  readonly id: number;
  readonly createdAt: Date;
}

export interface PurchaseDetails {
  readonly id: number;
  readonly createdAt: Date;
  readonly items: PurchaseProductData[];
}

export interface PurchaseProductData {
  readonly product: {
    readonly id: number;
    readonly name: string;
    readonly description: string;
  }
  readonly price: number;
  readonly quantity: number
}

export interface EmployeePaymentData {
  readonly id: number;
  readonly amount: number;
  readonly createdAt: Date;
}

export interface EmployeePaymentDetails {
  readonly id: number;
  readonly createdAt: Date;
}