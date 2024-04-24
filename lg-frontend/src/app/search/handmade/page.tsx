import CardL from "@/components/cards";
import { Business } from ".././types";
import { getBusinesses } from "../http";

export default async function Page() {
    let business: Business[] = await getBusinesses("HANDMADE");
    
    return (
        <div className="pt-12 p-5 flex flex-col gap-5">
            { business.map( (bus: Business) => 
                <CardL key={bus.id}
                    name = {bus.name}
                    desc = {bus.description} 
                    type = {bus.type}
                />
            )}
        </div>
    );
}
