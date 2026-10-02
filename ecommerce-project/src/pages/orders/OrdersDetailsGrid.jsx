import dayjs from 'dayjs';
import axios from 'axios';
import BuyAgainIcon from '../../assets/images/icons/buy-again.png';
import { Link } from 'react-router';
import { Fragment } from 'react';

export function OrdersDetailsGrid({order,loadCart}) {
    return (
        <div className="order-details-grid">
            {
                order.products.map((orderProduct) => {
                    const addItemToCart = async () => {
                        await axios.post('/api/cart-items',{
                            productId: orderProduct.product.id,
                            quantity: 1
                        });
                        await loadCart();
                    };
                    return (
                        <Fragment key={orderProduct.product.id}>
                            <div className="product-image-container">
                                <img src={orderProduct.product.image} />
                            </div>

                            <div className="product-details">
                                <div className="product-name">
                                    {orderProduct.product.name}
                                </div>
                                <div className="product-delivery-date">
                                    Arriving on: {dayjs(orderProduct.
                                        estimatedDeliveryTimeMs).format('MMMM D')}
                                </div>
                                <div className="product-quantity">
                                    Quantity: {orderProduct.quantity}
                                </div>
                                <button className="buy-again-button button-primary" onClick={addItemToCart}>
                                    <img className="buy-again-icon" src={BuyAgainIcon} />
                                    <span className="buy-again-message">Add to Cart</span>
                                </button>
                            </div>

                            <div className="product-actions">
                                <Link to={`/tracking/${order.id}/${orderProduct.product.id}`}>
                                    <button className="track-package-button button-secondary">
                                        Track package
                                    </button>
                                </Link>
                            </div>
                        </Fragment>
                    );
                })
            }
        </div>
    );
}