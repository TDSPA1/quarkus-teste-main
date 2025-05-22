# Use the Eclipse Temurin Alpine official image
FROM eclipse-temurin:21-jdk-alpine

# Instale utilitários necessários no Alpine (como dos2unix)
RUN apk add --no-cache bash dos2unix

# Crie e vá para o diretório da aplicação
WORKDIR /app

# Copie o código para o container
COPY . .

# Corrija permissões e conversões de fim de linha (Windows -> Unix)
RUN dos2unix ./mvnw && chmod +x ./mvnw

# Faça o build do app usando o Maven Wrapper
RUN ./mvnw -DoutputFile=target/mvn-dependency-list.log -B -DskipTests clean dependency:list install

# Execute o app Quarkus
CMD ["sh", "-c", "java -jar target/quarkus-app/quarkus-run.jar"]
