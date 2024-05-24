'use client'
import Link from "next/link";

export function Navbar() {
    return (
        <div className="navbar bg-base-100">
            <MobileNavigation />
            <DesktopNavigation />
            <div className="navbar-end">
                <a className="btn">Button</a>
            </div>
        </div>
    )
}

export function MobileNavigation() {
    return (
        <div className="navbar-start">
            <div className="dropdown">
                <div tabIndex={0} role="button" className="btn btn-ghost lg:hidden">
                    <svg xmlns="http://www.w3.org/2000/svg" className="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h8m-8 6h16" /></svg>
                </div>
                <ul tabIndex={0} className="menu menu-sm dropdown-content mt-3 z-[1] p-2 shadow bg-base-100 rounded-box w-52">
                    <li><Link href="/search/all">Search</Link></li>
                    <li><a href="#">Dashboard</a></li>
                    <li><a href="#">Profile</a></li>
                    <li><a href="#">About</a></li>
                </ul>
            </div>
            <Link href="/" className="btn btn-ghost text-xl"> Local Goodies </Link>
        </div>
    );
}

function DesktopNavigation() {
    return(
        <div className="navbar-center hidden lg:flex">
            <ul className="menu menu-horizontal px-1">
                <li><Link href="/search/all">Search</Link></li>
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Profile</a></li>
                <li><a href="#">About</a></li>
            </ul>
        </div>
    );
}
