'use client'
import { ClassNames } from "@emotion/react";
import { Tab, Tabs } from "@mui/material";
import React from "react";

export function TabsGroup() {
    const [value, setValue] = React.useState(0);
    const handleChange = (event: React.SyntheticEvent, newValue: number) => {
        setValue(newValue);
    };

    return (
        <Tabs
            value={value}
            onChange={handleChange}
            aria-label="business type tabs"
            indicatorColor="secondary"
        >
            <Tab label="Just browsing" />
            <Tab label="Handmade Clothes" />
            <Tab label="Artisan Beverages" />
            <Tab label="Repairs" />
        </Tabs>
    )
}
