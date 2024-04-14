import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { Navbar } from "@/components/nav";
import { AppRouterCacheProvider } from '@mui/material-nextjs/v13-appRouter';
import { Footer } from "@/components/footer";
import { ThemeProvider, createTheme } from "@mui/material";
import theme from "./theme";

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
                <AppRouterCacheProvider>
                    <Navbar />
                    <main className="flex min-h-screen flex-col items-center">
                        <ThemeProvider theme={theme}>
                            {children}
                        </ThemeProvider>
                    </main>
                    <Footer />
                </AppRouterCacheProvider>
            </body>
        </html>
    );
}
