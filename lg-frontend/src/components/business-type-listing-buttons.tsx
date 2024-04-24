import Link from "next/link";

export default function BusinessTypeListing() {
    return (
        <span className="flex gap-5 pt-4">
            <CustomButton name='All' link="/search/all"/>
            <CustomButton name='Restaurants' link="/search/restaurants"/>
            <CustomButton name='Handmade Clothing' link="/search/handmade"/>
            <CustomButton name='Repairs' link="/search/repairs"/>
        </span>
    );
}

type CustomButtonProps = { name: string, link: string };

function CustomButton({name, link}: CustomButtonProps) {
    return (
        <button className="border-solid border-0 border-b-2 border-white hover:border-orange-300 hover:text-gray-600">
            <Link href={link}>
                { name }
            </Link>
        </button>
    );
}
