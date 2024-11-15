const stripe = Stripe('pk_test_51Q6nIWBcKXXWFZvohz1l3f2knaBxCfm9pfQtSNmrLDUUMjtHOmn1L4pX0xMigyigP8P44JvRNgtfICVoM2fcSEd30033ZbIRRn');
const paymentButton = document.querySelector('#paymentButton');

paymentButton.addEventListener('click', () => {
  stripe.redirectToCheckout({
    sessionId: sessionId
  })
});