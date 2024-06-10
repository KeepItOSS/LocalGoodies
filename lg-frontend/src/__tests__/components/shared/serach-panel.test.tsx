jest.mock('@/http/business-listing', () => ({
    getBusinessByQueryName: jest.fn(() => Promise.resolve(businessListMock)),
}));

import SearchPanel from "@/components/shared/search-panel";
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { Business } from "@/models/business";
import { describe, beforeEach, it, expect, jest } from '@jest/globals';
import '@testing-library/jest-dom';
import '@/http/business-listing';

const getBusinessByQueryName = require('@/http/business-listing');

jest.useFakeTimers();

const businessListMock: Business[] = [
    {
        id: 1,
        name: "Business 1",
        description: "Descrip tion 1",
        type: "HANDMADE",
        createdAt: new Date(),
        chanchedAt: new Date(),
        active: true,
    },
    {
        id: 2,
        name: "Business 2",
        description: "Description 2",
        type: "HANDMADE",
        createdAt: new Date(),
        chanchedAt: new Date(),
        active: true,
    }
];

describe('SearchPanel', () => {
    beforeEach(() => {
        jest.clearAllMocks();
    });
    it('renders input field correctly', () => {
        // arrange
        render(<SearchPanel />);
        const input = screen.getByPlaceholderText('What are you looking for?');
        // act/assert
        expect(input).toBeInTheDocument();
    });
    it('updates query state on input change', () => {
        // arrange
        render(<SearchPanel />);
        const input = screen.getByPlaceholderText('What are you looking for?');
        // act
        fireEvent.change(input, { target: { value: 'Business' } });
        // assert
        expect(input).toHaveValue('Business');
    });
    it('calls getBusinessByQueryName after debounce', async () => {
        // arrange
        render(<SearchPanel />);
        const input = screen.getByPlaceholderText('What are you looking for?');
        // act
        fireEvent.change(input, { target: { value: 'Business' } });
        // assert
        await waitFor(() => {
            const businesses = screen.getAllByText(/Business/);
            expect(businesses).toHaveLength(2);
        });
    });
});
