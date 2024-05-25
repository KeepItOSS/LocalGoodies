import BusinessTypeListing from "@/components/business-selection-bar";
import SearchPanel from "@/components/search-panel";
import React from "react";

export default function SearchLayout({
    children,
}: {
        children: React.ReactNode
    }) {
    return (
        <div className="flex flex-col items-center justify-center">
            <BusinessTypeListing />
            <SearchPanel />
            {children}
        </div>
    );
}


