# Etapa 1: Compila o projeto usando Maven e Java 21
FROM eclipse-temurin:21-jdk-alpine as build

WORKDIR /app

# Copia o código e arquivos do Maven Wrapper
COPY . .

# Dá permissão de execução ao Maven Wrapper
RUN chmod +x ./mvnw

# Compila o projeto, sem rodar testes
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final para rodar o app (somente com JRE)
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia apenas o resultado do build
COPY --from=build /app/target/quarkus-app/ ./

# Expõe a porta padrão do Quarkus
EXPOSE 8080

# Comando para rodar o app
CMD ["java", "-jar", "quarkus-run.jar"]
