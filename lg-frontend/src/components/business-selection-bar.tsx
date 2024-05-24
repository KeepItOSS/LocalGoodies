import Link from "next/link";

export default function BusinessTypeListing() {
    return (
        <ul className="menu menu-horizontal bg-base-200 rounded-box">
            <li>
                <Link href="/search/all">
                    All
                </Link>
            </li>
            <li>
                <Link href="/search/restaurants">
                    Restaurants
                </Link>
            </li>
            <li>
                <Link href="/search/handmade">
                    Handmade Goods
                </Link>
            </li>
            <li>
                <Link href="/search/repairs">
                    Repairs
                </Link>
            </li>
        </ul>
    );
}

