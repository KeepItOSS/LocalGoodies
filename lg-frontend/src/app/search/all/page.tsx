import RenderBusinesList from "@/components/shared/card-business";
import Paginate from "@/components/shared/pagination";
import { getActiveBusinesses } from "@/http/business-listing";
import { BusinessPage } from "@/models/business";
import { SearchParamProps } from "@/models/SearchParamProps";

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
