import RenderBusinesList from "@/components/card-business";
import { getActiveBusinesses } from "@/http/business-listing";
import Paginate from "@/components/pagination";
import { BusinessPage, SearchParamProps } from "@/models/business";

export default async function Page({ searchParams }: Readonly<SearchParamProps>) {
    const currentPage = Number(searchParams?.page || 1);

    const businesses: BusinessPage = await getActiveBusinesses(currentPage);

    if (!businesses) return null;
    return (
        <>
            <RenderBusinesList 
                businesses={businesses.content} />
            <Paginate
                currentPage={currentPage} 
                maxPage={businesses.totalPages} />
        </>
    );
}
