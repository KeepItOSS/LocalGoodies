'use client'
import Link from "next/link";
import { useState } from "react"

/*
 * This component is temporary and should be replaced with one using Material UI for component integrity
 */

export function Navbar() {
    return(
        <nav className="bg-emerald-800 p-4">
            <div className="container mx-auto flex justify-between items-center">
                <Link href="/" className="text-white font-semibold text-xl"> LocalGoodies </Link>
                <DesktopNavigation />
                <MobileNavigation />
            </div>
        </nav>
    );
}

function MobileNavigation() {
    const [isMenuOpen, setIsMenuOpen] = useState(false);
    const toggleMenu = () => {
        setIsMenuOpen(!isMenuOpen);
    };  

    return (
        <div className="md:hidden">
            <button 
                className="text-white hover:text-gray-300 focus:outline-none"
                onClick={toggleMenu}
            >
                <svg 
                    className="w-6 h-6" 
                    fill="none" 
                    viewBox="0 0 24 24" 
                    stroke="currentColor"
                >
                    <path 
                        strokeLinecap="round" 
                        strokeLinejoin="round" 
                        strokeWidth="2" 
                        d="M4 6h16M4 12h16m-7 6h7" 
                        style={{display: isMenuOpen ? 'none' : 'inline-block'}}
                    />
                    <path 
                        strokeLinecap="round" 
                        strokeLinejoin="round" 
                        strokeWidth="2" 
                        d="M6 18L18 6M6 6l12 12" 
                        style={{display: isMenuOpen ? 'inline-block' : 'none'}}
                    />
                </svg>
            </button>
            {isMenuOpen && (
                <ul className="absolute right-0 bg-emerald-800 mt-2 p-6 rounded text-xl">
                    <li className="mb-2"><Link href="/search" className="block text-white hover:text-gray-300">Search</Link></li>
                    <li className="mb-2"><a href="#" className="block text-white hover:text-gray-300">Dashboard</a></li>
                    <li className="mb-2"><a href="#" className="block text-white hover:text-gray-300">Profile</a></li>
                    <li className="mb-2"><a href="#" className="block text-white hover:text-gray-300">About</a></li>
                </ul>
            )}
        </div>
    );
}

function DesktopNavigation() {
    return(
        <>
            <ul className="hidden md:flex md:space-x-4">
                <li><Link href="/search" className="block text-white hover:text-gray-300">Search</Link></li>
                <li><a href="#" className="text-white hover:text-gray-300">Dashboard</a></li>
                <li><a href="#" className="text-white hover:text-gray-300">Profile</a></li>
                <li><a href="#" className="text-white hover:text-gray-300">About</a></li>
            </ul>
        </>
    );
}
