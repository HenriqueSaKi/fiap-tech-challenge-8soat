const mercadopago = require('mercadopago');
require('dotenv').config();

mercadopago.configurations.setAccessToken(process.env.MP_ACCESS_TOKEN);

async function generateQRCode(amount, description) {
  const payment_data = {
    transaction_amount: amount,
    description,
    payment_method_id: 'pix',
    payer: {
      email: 'felipeu.santos@gmail.com'
    }
  };

  try {
    const payment = await mercadopago.payment.create(payment_data);
    console.log(`Pagamento criado, QR Code: ${payment.response.point_of_interaction.transaction_data.qr_code}`);

    return payment.response.point_of_interaction.transaction_data.qr_code;
  } catch (err) {
    console.error(`Erro ao gerar o QR Code: ${err}`);
    throw err;
  }
}

module.exports = { generateQRCode };
