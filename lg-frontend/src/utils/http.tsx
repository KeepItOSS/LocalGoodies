const BASE_URL = 'http:localhost:8080/api';

export async function GET(route: string): Promise<any> {
    try {
        const response = await fetch(BASE_URL + route, {
            next: { revalidate: 60 }
        });
        return response.json();
    } catch(error) {
            throw new Error('Failed to fetch data');
    }
}

export function POST(route: string, body: any) {
    return fetch(BASE_URL + route, {
        method: 'POST',
        body: body,
    })
}
