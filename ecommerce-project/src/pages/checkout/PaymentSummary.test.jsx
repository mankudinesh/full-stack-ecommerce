import { it, expect, describe, vi, beforeEach } from 'vitest';
import { render, screen, within } from '@testing-library/react';
import { userEvent } from '@testing-library/user-event';
import { PaymentSummary } from './PaymentSummary';
import axios from 'axios';
import { MemoryRouter,useLocation } from 'react-router';

vi.mock('axios');

describe('PaymentSummary component', () => {
    let loadCart;
    let user;
    let paymentSummary;
    beforeEach(() => {
            paymentSummary = {
            totalItems: 3,
            productCostCents: 4275,
            shippingCostCents: 499,
            totalCostBeforeTaxCents: 4774,
            taxCents: 477,
            totalCostCents: 5251
        };
        loadCart = vi.fn();
        user = userEvent.setup();
    });

    it('displays the correct payment summary', () => {

        render(
            <MemoryRouter>
                <PaymentSummary paymentSummary={paymentSummary} loadCart={loadCart} />
            </MemoryRouter>
        );
        expect(
            screen.getByText('Items (3):')
        ).toBeInTheDocument();
        expect(
            within(screen.getByTestId('payment-summary-product-cost')).getByText('$42.75')
        ).toBeInTheDocument();
        expect (
            screen.getByTestId('payment-summary-shipping-cost')
        ).toHaveTextContent('$4.99');
        expect(
            screen.getByTestId('payment-summary-total-before-tax')
        ).toHaveTextContent('$47.74');
    });

    it('test on the place order button',async () => {
        function Location(){
            const location = useLocation();
            return (<div data-testid="url-path">{location.pathname}</div>);
        }
        render(
            <MemoryRouter>
                <PaymentSummary paymentSummary={paymentSummary} loadCart={loadCart} />
                <Location />
            </MemoryRouter>
        );

        const orderButton = screen.getByTestId('place-order-button');
        await user.click(orderButton);
        expect(axios.post).toHaveBeenCalledWith('/api/orders');
        expect(loadCart).toHaveBeenCalled();
        expect(screen.getByTestId('url-path')).toHaveTextContent('/orders');
    });
});