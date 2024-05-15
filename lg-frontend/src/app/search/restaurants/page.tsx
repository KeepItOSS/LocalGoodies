import RenderBusinesList from "@/components/business-list";
import { Business } from "../../../models/business";
import { getBusinesses } from "@/http/business-listing";

export default async function Page() {
    let business: Business[] = await getBusinesses("RESTAURANT");

    return (
        <RenderBusinesList businesses={business} />
    );
}
