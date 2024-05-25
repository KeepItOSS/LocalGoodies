'use client';

import { usePathname, useRouter } from "next/navigation";
import { useState } from "react";

// fix the bug where the page number gets added when we click the next button
// add clause to check if user goes below 0 in page count
// maybe add debounce on buttons 
export default function Paginate() {
    const pathname = usePathname();
    const searchParams = new URLSearchParams();
    const [page, setPage] = useState(Number(searchParams.get('page') || 1));

    const createPageURL = (pageNumber: number | string) => {
        const params = new URLSearchParams(searchParams);
        params.set('page', String(pageNumber));
        return `${pathname}?${params.toString()}`;
    };

    function handlePageForward() {
        setPage(page + 1);
        router.push(createPageURL(page));
    }

    function handlePageBackward() {
        setPage(page - 1);
        router.push(createPageURL(page));
    }

    const router = useRouter();

    return (
        <div className="join">
            <button className="join-item btn" onClick={handlePageBackward}>«</button>
            <button className="join-item btn">{ page }</button>
            <button className="join-item btn" onClick={handlePageForward}>»</button>
        </div>
    );
}

