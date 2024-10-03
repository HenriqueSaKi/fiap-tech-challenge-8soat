const express = require('express');
const app = express();

app.use(express.json());

app.post('/webhook/payment', (req, res) => {
  const payment = req.body;

  console.log(`Notificação de pagamento recebida: ${JSON.stringify(payment, null, 2)}`);

  if (payment.status === 'approved') {
    console.log('Pagamento aprovado');
  } else if (pay.status === 'rejected') {
    console.log('Pagamento recusado');
  }

  res.sendStatus(200);
});

const PORT = process.env.port || 8080;
app.listen(PORT, () => {
  console.log(`Webhook excutando na porta: ${PORT}`);
});
