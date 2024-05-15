'use client'

import { getBusinessByQueryName } from "@/http/business-listing";
import { Business } from "@/models/business";
import { useEffect, useState } from "react";

export default function SearchPanel() {
    // todo 14.05.2024 fill initial state with suggestions after caching is implemented
    const [businesses, setBusinesses] = useState<Business[]>([]);
    const [query, setQuery] = useState<string>('');
    const inputQuery = debounceInputQuery(query, 500);

    useEffect(() => {
        if (!inputQuery ||
            !inputQuery.trim().length ||
            inputQuery.length < 3) {

            setBusinesses([]);
            return;
        }
        getBusinessByQueryName(inputQuery).then((businesses: Business[]) => {
            setBusinesses(businesses);
        });
    }, [inputQuery]);

    return (
        <div className="container mx-auto">
            <div className="flex items-center justify-center w-full">
                <div className="flex flex-row p-4 w-3/12 rounded border-2 border-gray-600 focus:outline-none focus:border-b-2 focus:border-b-black-800">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5} stroke="currentColor" className="w-6 h-6">
                        <path strokeLinecap="round" strokeLinejoin="round" d="m21 21-5.197-5.197m0 0A7.5 7.5 0 1 0 5.196 5.196a7.5 7.5 0 0 0 10.607 10.607Z" />
                    </svg>
                    <label htmlFor="search" className="sr-only">Search</label>
                    <input
                        className="pl-2 focus:outline-none"
                        type="search"
                        placeholder="Looking for something?"
                        defaultValue={''}
                        onChange={(e) => {
                            setQuery(e.target.value);
                        }}
                    />
                </div>
            </div>
            <SearchResults business={businesses} />
        </div>
    );
}

function SearchResults({ business }: { business: Business[] }) {
    return (
        <div id="b-list" className="flex items-center justify-center w-full">
            {business.length > 0 && (
                <ul className="w-3/12">
                    {business.map((business: Business) => (
                        <li className="flex pl-12 p-2 border-b-2 items-center w-full"
                            key={business.id}>
                            <button>{business.name} {business.type.toLowerCase()}</button>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}

function debounceInputQuery(inputValue: string, wait: number) {
    const [query, setQuery] = useState<string>('');

    useEffect(() => {
        const handler = setTimeout(() => {
            setQuery(inputValue as string);
        }, wait);

        return () => {
            clearTimeout(handler);
        };
    }, [inputValue, wait]);

    return query;
}
