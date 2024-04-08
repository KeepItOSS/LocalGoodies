export function Navbar() {
    return(
        <nav className="bg-gray-800 p-4">
            <div className="container mx-auto flex justify-between items-center">
                <div className="text-white font-semibold text-xl"> LocalGoodies </div>
                <ul className="flex space-x-4">
                    <li><a href="#" className="text-white hover:text-gray-300">Search</a></li>
                    <li><a href="#" className="text-white hover:text-gray-300">About</a></li>
                </ul>
            </div>
        </nav>
    );
}
