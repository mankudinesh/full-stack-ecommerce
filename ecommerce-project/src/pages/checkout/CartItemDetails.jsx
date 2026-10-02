import axios from 'axios';
import { useState } from 'react';
import { formatMoney } from '../../utils/money';

export function CartItemDetails({ cartItem, loadCart }) {
    const [inputElement, setInputElement] = useState(false);
    const [quantity, setQuantity] = useState(cartItem.quantity);
    const deleteCartItem = async () => {
        await axios.delete(`/api/cart-items/${cartItem.productId}`);
        await loadCart();
    };
    async function showInputElement() {
        if (inputElement) {
            await axios.put(`/api/cart-items/${cartItem.productId}`, {
                quantity: quantity
            });
            await loadCart();
            setInputElement(false);
        }
        else {
            setInputElement(true);
        }
    }

    const updateQuantityByEvents = async (event) => {
        if (event.key === 'Enter') {
            showInputElement();
        }
        else if (event.key === 'Escape') {
            setQuantity(cartItem.quantity);
            setInputElement(false);
            await loadCart();
        }
    };

    const addingQuantity = (event) => {
        setQuantity(Number(event.target.value));
    };
    return (
        <>
            <img className="product-image"
                src={cartItem.product.image} />

            <div className="cart-item-details">
                <div className="product-name">
                    {cartItem.product.name}
                </div>
                <div className="product-price">
                    {formatMoney(cartItem.product.priceCents)}
                </div>
                <div className="product-quantity">
                    <span>
                        Quantity: {inputElement ? <input type="text"
                            style={{ width: 50 }}
                            value={quantity}
                            onChange={addingQuantity}
                            onKeyDown={updateQuantityByEvents} />
                            : <span className="quantity-label">{cartItem.quantity}</span>
                        }
                    </span>
                    <span className="update-quantity-link link-primary" onClick={showInputElement}>
                        Update
                    </span>
                    <span className="delete-quantity-link link-primary"
                        onClick={deleteCartItem}>
                        Delete
                    </span>
                </div>
            </div>
        </>
    );
}