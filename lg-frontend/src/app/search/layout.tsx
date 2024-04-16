import BusinessTypeListing from "@/components/business-type-listing";
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
