import SearchPanel from "@/components/shared/search-panel";
import { render, screen, fireEvent, waitFor, act } from '@testing-library/react';
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
        act(() => {
            fireEvent.change(input, { target: { value: 'Business' } });
        });
        // assert
        expect(input).toHaveValue('Business');
    });
    it('succesfully calls api on input', async () => {
        // arrange
        render(<SearchPanel />);
        const input = screen.getByPlaceholderText('What are you looking for?');
        // act
        await act(async () => {
            fireEvent.change(input, { target: { value: 'Business' } });
        });
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
        await act(async () => {
            fireEvent.focus(input);
            fireEvent.change(input, { target: { value: 'Business' } });
        });
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
        await act(async () => {
            fireEvent.change(input, { target: { value: 'Business' } });
        });
        await waitFor(() => {
            const businessList = screen.queryAllByText(/Business/);
            expect(businessList).toHaveLength(0);
        });
    });
    it('debounces input correctly', async () => {
        // arrange
        render(<SearchPanel />);
        const input = screen.getByPlaceholderText('What are you looking for?');
        // act
        await act(async () => {
            fireEvent.focus(input);

            fireEvent.change(input, { target: { value: 'B' } });
            fireEvent.change(input, { target: { value: 'Bu' } });
            fireEvent.change(input, { target: { value: 'Bus' } });

            jest.advanceTimersByTime(500);
        });
        // assert
        await waitFor(() => {
            expect(fetch).toHaveBeenCalledTimes(1);
            expect(fetch).toHaveBeenCalledWith("http://localhost:8080/api/business-listing/search/name?name=Bus");
        });
    });
    it('does not call api when char less than 3', async () => {
        // arrange
        render(<SearchPanel />);
        const input = screen.getByPlaceholderText('What are you looking for?');
        // act
        await act(async () => {
            fireEvent.focus(input);

            fireEvent.change(input, { target: { value: 'B' } });
            fireEvent.change(input, { target: { value: 'Bu' } });

            jest.advanceTimersByTime(500);
        });
        // assert
        await waitFor(() => {
            expect(fetch).not.toHaveBeenCalled();
        });
    });
});
