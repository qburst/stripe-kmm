import { loadStripe } from '@stripe/stripe-js';

const stripePublicKey = "pk_test_FkQvi0DNueKlNnVwNoJktg2W";

// Load Stripe
let stripePromise = loadStripe(stripePublicKey);

export async function initializeStripe() {
    const stripe = await stripePromise;
    const elements = stripe.elements();

    const cardElement = elements.create('card');
    cardElement.mount('#payment-form');

    const payButton = document.getElementById('pay-button');
    payButton.addEventListener('click', async () => {
        const { paymentIntent, error } = await stripe.createPaymentMethod({
            type: 'card',
            card: cardElement,
        });
        if (error) {
            console.error(error);
        } else {
            console.log('Payment successful:', paymentIntent);
        }
    });
}
