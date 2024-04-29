import { Business } from "./types";
import { unstable_noStore as noStore } from 'next/cache';

const BASE_URL = 'http:localhost:8080/api';

export async function getBusinesses(queryParam: string): Promise<Business[]> {
    noStore();
    try {
        const response = await fetch(BASE_URL + `/search?type=${queryParam}`);
        return response.json();
    } catch(error) {
        throw new Error('Failed to fetch data');
    }
}

export async function getAllBusinesses(): Promise<Business[]> {
    noStore();
    try {
        const response = await fetch(BASE_URL + '/search/all');
        return response.json();
    } catch(error) {
        throw new Error('Failed to fetch data');
    }
}
