import RenderBusinesList from "@/components/business-list";
import { Business } from "../../../models/business";
import { getAllBusinesses } from "@/http/business-listing";

export default async function Page() {
    let business: Business[] = await getAllBusinesses();

    return (
        <RenderBusinesList businesses={business} />
    );
}
