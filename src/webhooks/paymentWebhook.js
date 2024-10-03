const express = require('express');
const axios = require('axios');
const app = express();

app.use(express.json());

app.post('/webhook/payment', async (req, res) => {
  const payment = req.body;

  console.log(`Notificação de pagamento recebida: ${JSON.stringify(payment, null, 2)}`);

  try {
    const response = await axios.post('http://localhost//8080/api/payments', payment);

    console.log(`Resposta ao backend: ${response}`);

    res.sendStatus(200);
  } catch (err) {
    console.error(`Erro ao processar a notificação para o backend: ${err.message}`);
    res.status;
    (500).send('Erro ao processar a notificação');
  }

  if (payment.status === 'approved') {
    console.log('Pagamento aprovado');
  } else if (pay.status === 'rejected') {
    console.log('Pagamento recusado');
  }

  //   res.sendStatus(200);
});

const PORT = process.env.port || 8080;
app.listen(PORT, () => {
  console.log(`Webhook excutando na porta: ${PORT}`);
});
