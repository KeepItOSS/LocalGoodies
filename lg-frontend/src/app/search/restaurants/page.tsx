import RenderBusinesList from "@/components/card-business";
import { BusinessPage, SearchParamProps } from "../../../models/business";
import { getBusinessesByType } from "@/http/business-listing";
import Paginate from "@/components/pagination";

export default async function Page({ searchParams }: Readonly<SearchParamProps>) {
    const currentPage = Number(searchParams?.page || 1);

    // todo add type enum (or object) to avoid hardcoding
    let businesses: BusinessPage = await getBusinessesByType(currentPage, "RESTAURANT");

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
