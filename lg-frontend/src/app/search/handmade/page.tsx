import { Business } from ".././types";
import { GET} from "@/utils/http";

export default async function Page() {
    let business: Business[] = await getBusinesses();
    
    return (
        <>
            { business.map( (bus:any) => 
                <div> {bus.id +  ' ' + bus.name} </div>
            )}
        </>
    );
}

export async function getBusinesses(): Promise<Business[]> {
        const businesses: Business[] = await GET("/search");
        return businesses;
}
