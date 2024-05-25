import { Business, BusinessPage } from "../models/business";
import { unstable_noStore as noStore } from 'next/cache';

const BASE_URL = 'http://localhost:8080/api/business-listing';

export async function getBusinessesPaged(queryParam: string, page: number): Promise<Business[]> {
    noStore();
    try {
        const response = await fetch(BASE_URL + `/search?type=${queryParam}&page=${page}`);
        return response.json();
    } catch(error) {
        throw new Error('Failed to fetch data');
    }
}

export async function getBusinessesPagedAsPage(page: number): Promise<BusinessPage> {
    noStore();
    try {
        const response = await fetch(BASE_URL + `/search/active/page?page=${page}`);
        return response.json();
    } catch(error) {
        throw new Error('Failed to fetch data');
    }
}

export async function getAllBusinessesPaged(page: number): Promise<Business[]> {
    noStore();
    try {
        const response = await fetch(BASE_URL + `/search/active?page=${page}`);
        return response.json();
    } catch(error) {
        throw new Error('Failed to fetch data');
    }
}

export async function getBusinessByQueryName(query: string): Promise<Business[]> {
    noStore();
    try {
        const response = await fetch(`${BASE_URL}/search/name?name=${query}`)
        return response.json();
    } catch(error) {
        throw new Error('Failed to fetch data');
    }
}
