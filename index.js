/*
 * Função Lambda em JavaScript que consome mensagens de um tópico Kafka e imprime cada uma no console.
 *
 * O AWS MSK como gatilho entrega eventos no formato de array de registros codificados em Base64.
 */
exports.handler = async (event) => {
  let count = 0;
  const records = event.records || {};
  
  for (const [topic, messages] of Object.entries(records)) {
    for (const msg of messages) {
      const valueB64 = msg.value || '';
      let decoded;
      try {
        decoded = Buffer.from(valueB64, 'base64').toString('utf-8');
      } catch (e) {
        decoded = valueB64;
      }
      console.log(`A mensagem chegou: ${decoded}`);
      count++;
    }
  }
  return { status: 'processed', messageCount: count };
};