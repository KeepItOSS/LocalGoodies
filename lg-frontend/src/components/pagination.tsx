'use client';

import { usePathname, useRouter } from "next/navigation";

type pageProps = { currentPage: number, maxPage: number };

export default function Paginate({ currentPage, maxPage }: pageProps) {
    const pathname = usePathname();
    const searchParams = new URLSearchParams();

    const pageDown = () => {
        if (currentPage === maxPage) return;
        router.push(createPageURL(currentPage + 1));
    }

    const pageUp = () => {
        if (currentPage === 1) return;
        router.push(createPageURL(currentPage - 1));
    }

    function createPageURL(pageNumber: number | string) {
        const params = new URLSearchParams(searchParams);
        params.set('page', String(pageNumber));
        return `${pathname}?${params}`;
    }; 


    const router = useRouter();

    return (
        <div className="pt-5 join">
            <button
                className="join-item btn"
                onClick={pageUp}> « </button>

            <button className="join-item btn">{ currentPage } </button>

            <button 
                className="join-item btn" 
                onClick={pageDown}> » </button>
        </div>
    );
}

