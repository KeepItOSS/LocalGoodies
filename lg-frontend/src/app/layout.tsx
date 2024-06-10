import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { Navbar } from "@/components/navigation/nav";
import { Footer } from "@/components/navigation/footer";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
    title: "Local Goodies",
    description: "Support your local business!",
};

export default function RootLayout({
    children,
}: Readonly<{
        children: React.ReactNode;
    }>) {
    return (
        <html lang="en">
            <body className={inter.className}>
                <Navbar />
                <main className="mx-auto">
                    {children}
                </main>
                <Footer />
            </body>
        </html>
    );
}
