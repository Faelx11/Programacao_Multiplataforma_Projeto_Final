FROM public.ecr.aws/lambda/nodejs:18

# Copia o código da função
COPY index.js ${LAMBDA_TASK_ROOT}

# Dependências podem ser instaladas aqui se necessário (package.json)
# RUN npm install

# Define o comando de inicialização para a função
CMD ["index.handler"]