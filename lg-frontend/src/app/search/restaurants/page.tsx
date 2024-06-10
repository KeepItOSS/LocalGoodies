import RenderBusinesList from "@/components/shared/card-business";
import { BusinessPage } from "../../../models/business";
import { getBusinessesByType } from "@/http/business-listing";
import { SearchParamProps } from "@/models/SearchParamProps";
import Paginate from "@/components/shared/pagination";

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
