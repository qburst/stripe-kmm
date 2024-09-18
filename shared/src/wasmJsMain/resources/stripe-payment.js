import { loadStripe } from "@stripe/stripe-js";

const stripePublicKey =
  "pk_test_51NE7wlSFf0Ntn7fX6oxXTv49OyXCWj3FffFeAeoxAvQEaPBrZBwNcqVH8YuxsuOAzXmtER4hgztFXkU1WsKYdgPB00d0zbyRXD";
const stripe = loadStripe(stripePublicKey);

const mockCardNumber = "4242424242424242";
const mockExpMonth = "08";
const mockExpYear = "29";
const mockCvc = "123";

async function handlePayment() {
  const stripeInstance = await stripe;

  const { paymentMethod, error } = await stripeInstance.createPaymentMethod({
    type: "card",
    card: {
      number: mockCardNumber,
      exp_month: mockExpMonth,
      exp_year: mockExpYear,
      cvc: mockCvc,
    },
  });

  if (error) {
    console.error("Error:", error);
  } else {
    console.log("PaymentMethod:", paymentMethod);

    alert("Payment successful!");
  }
}

window.handlePayment = handlePayment;
