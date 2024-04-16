import CardS from "@/components/cards";
import { Business } from ".././types";
import { GET} from "@/utils/http";

export default async function Page() {
    let business: Business[] = await getBusinesses();
    
    return (
        <div className="pt-12 p-5 flex flex-col gap-5">
            { business.map( (bus:any) => 
                <CardS
                    name= {bus.name}
                    desc= {bus.description} 
                    type= {bus.type}
                />
            )}
        </div>
    );
}

export async function getBusinesses(): Promise<Business[]> {
        const businesses: Business[] = await GET("/search");
        return businesses;
}
