import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { Navbar } from "@/components/nav";
import { Footer } from "@/components/footer";

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
        <html lang="en" data-theme="garden">
            <body className={inter.className}>
                <Navbar />
                <main className="flex min-h-screen">
                    {children}
                </main>
                <Footer />
            </body>
        </html>
    );
}
