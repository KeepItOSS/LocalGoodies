import { Business } from "./types";

const BASE_URL = 'http:localhost:8080/api';

export async function getBusinesses(queryParam: string): Promise<Business[]> {
    try {
        const response = await fetch(BASE_URL + `/search?type=${queryParam}`, {
            next: { revalidate: 60 }
        });
        return response.json();
    } catch(error) {
        throw new Error('Failed to fetch data');
    }
}

export async function getAllBusinesses(): Promise<Business[]> {
    try {
        const response = await fetch(BASE_URL + '/search/all', {
            next: { revalidate: 60 }
        });
        return response.json();
    } catch(error) {
        throw new Error('Failed to fetch data');
    }
}
