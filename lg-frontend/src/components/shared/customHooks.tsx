import { useEffect, useState } from "react";

export function useDebounceInputQuery(inputValue: string, wait: number) {
    const [query, setQuery] = useState<string>('');

    useEffect(() => {
        const handler = setTimeout(() => {
            setQuery(inputValue as string);
        }, wait);

        return () => {
            clearTimeout(handler);
        };
    }, [inputValue, wait]);

    return query;
}
