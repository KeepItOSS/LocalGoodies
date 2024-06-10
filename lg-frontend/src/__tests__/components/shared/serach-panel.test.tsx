import SearchPanel from "@/components/shared/search-panel";
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import { Business } from "@/models/business";
import { describe, beforeEach, it, expect, jest } from '@jest/globals';
import '@testing-library/jest-dom';

global.fetch = jest.fn(() =>
    Promise.resolve({
        json: () => Promise.resolve(businessListMock),
    })
) as jest.Mock;
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
    it('succesfully calls api on input', async () => {
        // arrange
        render(<SearchPanel />);
        const input = screen.getByPlaceholderText('What are you looking for?');
        // act
        fireEvent.change(input, { target: { value: 'Business' } });
        // assert
        await waitFor(() => {
            expect(fetch).toHaveBeenCalledTimes(1);
            expect(fetch).toHaveBeenCalledWith("http://localhost:8080/api/business-listing/search/name?name=Business");
        });
    });
    it('succesfully calls api and displays list on input', async () => {
        // arrange
        render(<SearchPanel />);
        const input = screen.getByPlaceholderText('What are you looking for?');
        // act
        fireEvent.focus(input);
        fireEvent.change(input, { target: { value: 'Business' } });
        // assert
        await waitFor(() => {
            const businessList = screen.queryAllByText(/Business/);
            expect(businessList).toHaveLength(2);
        });
    });
    it('does not show list input not focused', async () => {
        // arrange
        render(<SearchPanel />);
        const input = screen.getByPlaceholderText('What are you looking for?');
        // act
        fireEvent.change(input, { target: { value: 'Business' } });
        // assert
        await waitFor(() => {
            const businessList = screen.queryAllByText(/Business/);
            expect(businessList).toHaveLength(0);
        });
    });
});
