import RenderBusinesList from "@/components/card-business";
import { Business } from "../../../models/business";
import { getBusinesses } from "@/http/business-listing";

export default async function Page() {
    let business: Business[] = await getBusinesses("REPAIR");

    return (
        <RenderBusinesList businesses={business} />
    );
}
