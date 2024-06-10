import { Business } from "@/models/business";

export default function RenderBusinesList({ businesses }: { businesses: Business[] }) {
    return (
        <div className="pt-12 mx-auto justify-center flex flex-col gap-5">
            {businesses.length > 0 && ( businesses.map( (business: Business) => 
                <Card key={business.id} {...business} />
            ))}
        </div>
    );
}

type CardProps = { name: string, description: string, type: string }

function Card({ name, description, type }: CardProps) {
    return (
        <div className="card w-96 bg-base-200 shadow-xl">
            <figure><img src="/undraw_house_searching_re_stk8.svg" alt="Business photo" /></figure>
            <div className="card-body">
                <h2 className="card-title">
                    { name }
                </h2>
                <p>{ description }</p>
                <div className="card-actions justify-end">
                    <div className="badge badge-outline text-primary">{ type }</div>
                </div>
            </div>
        </div>
    );
}
