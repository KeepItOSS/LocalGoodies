import BusinessTypeListing from "@/components/navigation/business-selection-bar";
import SearchPanel from "@/components/shared/search-panel";
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


