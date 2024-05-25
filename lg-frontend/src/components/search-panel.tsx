'use client'

import { getBusinessByQueryName } from "@/http/business-listing";
import { Business } from "@/models/business";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function SearchPanel() {
    // todo 14.05.2024 fill initial state with suggestions after caching is implemented
    const [businesses, setBusinesses] = useState<Business[]>([]);
    const [query, setQuery] = useState<string>('');
    const [isInputFocused, setIsInputFocused] = useState<boolean>(false);
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
        <div className="container mx-auto flex flex-col items-center justify-center pt-5">
            <div className="relative">
                <label htmlFor="search" className="input input-lg input-bordered flex w-full items-center gap-2">
                    <input
                        type="search"
                        placeholder="What are you looking for?"
                        defaultValue={''}
                        onChange={(e) => {
                            setQuery(e.target.value);
                        }}
                        onFocus={() => setIsInputFocused(true)}
                        onBlur={() => { setTimeout(() => {setIsInputFocused(false)} , 200) }}
                    />
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 16 16" fill="currentColor" className="w-4 h-4 opacity-70"><path fillRule="evenodd" d="M9.965 11.026a5 5 0 1 1 1.06-1.06l2.755 2.754a.75.75 0 1 1-1.06 1.06l-2.755-2.754ZM10.5 7a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0Z" clipRule="evenodd" /></svg>
                </label>
                {isInputFocused && <SearchResultList business={businesses} />}
            </div>
        </div>
    );
}

function SearchResultList({ business }: { business: Business[] }) {
    return (
        <div className="absolute pt-2 left-0 z-10 w-full">
            <div className="border bg-base-100 border-base rounded shadow-xl h-48 overflow-y-auto">
                <ul className="divide-y divide-base-100 p-2">
                    {business.length > 0 && (business.map((business: Business) =>
                        <li key={business.id} className="flex justify-center p-2">
                            <div className="flex items-center justify-between">
                                <Link href={`/business/#`}> 
                                    <button className="btn btn-ghost">
                                        <span>{business.name} </span>
                                    </button>
                                </Link>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
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
