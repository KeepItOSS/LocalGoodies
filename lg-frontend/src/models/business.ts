export type Business = {
    id: number,
    name: string,
    description: string,
    type: string

    phoneNumber?: string,
    email?: string,

    createdAt: Date,
    chanchedAt: Date,
    active: boolean,
}

export type BusinessPage = {
    content: Business[],
    number: number,
    totalElements: number,
    size: number,

    totalPages: number,
    last: boolean,
    first: boolean,
    numberOfElements: number,
    empty: boolean
};

export type BusinessForm = {
    name: string,
    description: string,
    type: BusinessType,
    phoneNumber?: string,
    email?: string,
};

export enum BusinessType {
    HANDMADE_GOODS = "Handmade Goods",
    REPAIRS = "Repairs",
    RESTAURANTS = "Restaurants"
};
