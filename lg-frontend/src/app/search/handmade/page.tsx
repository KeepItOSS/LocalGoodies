import RenderBusinesList from "@/components/card-business";
import { Business } from "../../../models/business";
import { getBusinessesPaged } from "@/http/business-listing";

export default async function Page() {
    let business: Business[] = await getBusinessesPaged("HANDMADE", 0);

    return (
        <RenderBusinesList businesses={business} />
    );
}
