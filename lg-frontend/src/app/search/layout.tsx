import BusinessTypeListing from "@/components/business-selection-bar";
import React from "react";

export default function SearchLayout({
    children,
}: {
        children: React.ReactNode
    }) {
    return (
        <div className="flex flex-col items-center justify-content center">
            <BusinessTypeListing />
            {children}
        </div>
    );
}
