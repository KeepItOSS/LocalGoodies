import BusinessTypeListing from "@/components/business-type-listing-buttons";
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
