import RenderBusinesList from "@/components/card-business";
import { getBusinessesPagedAsPage } from "@/http/business-listing";
import Paginate from "@/components/pagination";

export default async function Page({ searchParams }: Readonly<SearchParamProps>) {
    const currentPage = Number(searchParams?.page || 1);

    const data = await getBusinessesPagedAsPage(currentPage);

    if (!data) return null;
    return (
        <>
            <RenderBusinesList 
                businesses={data.content} />
            <Paginate
                currentPage={currentPage} 
                maxPage={data.totalPages} />
        </>
    );
}

interface SearchParamProps {
    searchParams?: {
        page?: string;
        query?: string;
    };
}
