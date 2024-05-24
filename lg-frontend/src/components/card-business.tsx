import { Business } from "@/models/business";

export default function RenderBusinesList({ businesses }: { businesses: Business[] }) {
    return (
        <div className="pt-12 p-5 flex flex-col gap-5">
            {businesses.length > 0 && ( businesses.map( (business: Business) => 
                <ResponsiveCard key={business.email} {...business} />
            ))}
        </div>
    );
}

type CardProps = { name: string, description: string, type: string }
function ResponsiveCard({ name, description, type }: CardProps) {
    return (
        <div className="card lg:card-side bg-accent text-base-100 shadow-xl">
            <figure><img src="https://t3.ftcdn.net/jpg/04/00/23/94/360_F_400239416_rEE43A7lnmsMmnbMkcGnInylyJRBLdU3.jpg" alt="Album"/></figure>
            <div className="card-body">
                <h2 className="card-title"> { name }</h2>
                <p> { description }</p>
                <div className="card-actions justify-end">
                    <a className="btn btn-neutral"> { type } </a>
                </div>
            </div>
        </div>
    );
}

