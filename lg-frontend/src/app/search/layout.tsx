import BusinessTypeListing from "@/components/business-selection-bar";
import SearchPanel from "@/components/search-panel";
import React from "react";

export default function SearchLayout({
    children,
}: {
        children: React.ReactNode
    }) {
    return (
        <>
            <BusinessTypeListing />
            {children}
        </>
    );
}
