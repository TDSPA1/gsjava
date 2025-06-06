# Etapa 1 - Compilar o projeto
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app
COPY . .

# Definir permissões e compilar o projeto
RUN chmod +x ./mvnw \
 && ./mvnw clean package -DskipTests

# Etapa 2 - Runtime com JRE leve
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia apenas o que é necessário para rodar o app
COPY --from=build /app/target/quarkus-app/ ./

EXPOSE 8080

# Comando para executar o aplicativo
CMD ["java", "-jar", "quarkus-run.jar"]
