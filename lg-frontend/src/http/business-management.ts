import { Business, BusinessForm } from "@/models/business";

const BASE_URL = process.env.NEXT_PUBLIC_API_URL + '/business-listing';

export async function addNewBusiness(business: BusinessForm): Promise<Business> {
    try {
        const response = await fetch(BASE_URL + '/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(business),
        });
        return response.json();
    } catch(error) {
        throw new Error('Failed to fetch data');
    }
}
