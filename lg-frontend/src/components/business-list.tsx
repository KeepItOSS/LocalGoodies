import { Business } from "@/models/business";

export default function RenderBusinesList({ businesses }: { businesses: Business[] }) {
    return (
        <div className="pt-12 p-5 flex flex-col gap-5">
            {businesses.length > 0 && ( businesses.map( (business: Business) => 
                <CardL key={business.email} {...business} />
            ))}
        </div>
    );
}

function CardL({ name, description, type }: CardProps) {
    return (
        <div className="flex flex-col items-center bg-white border border-gray-200 rounded-lg shadow md:flex-row md:max-w-xl">
                <img className="object-cover w-full rounded-t-lg h-96 md:h-auto md:w-48 md:rounded-none md:rounded-s-lg" src="https://t3.ftcdn.net/jpg/04/00/23/94/360_F_400239416_rEE43A7lnmsMmnbMkcGnInylyJRBLdU3.jpg" alt="Logo or image of a business" />
    <div className="flex flex-col justify-between p-4 leading-normal">
        <h5 className="mb-2 text-2xl font-bold tracking-tight text-gray-700"> { name } </h5>
        <p className="mb-3 font-normal text-gray-700"> { description } </p>
        <p className="mb-0 font-bold text-orange-700" > { type } </p>
    </div>
        </div>
    );
}

type CardProps = { name: string, description: string, type: string }

