# 1-ci mərhələ - Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app

# pom.xml-i kopyala
COPY pom.xml .

# Asılılıqları yüklə (cache üçün)
RUN mvn dependency:go-offline

# Source kodu kopyala
COPY src ./src

# Build et
RUN mvn clean package -DskipTests

# 2-ci mərhələ - Run
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Build nəticəsində yaranan jar-ı köçür
COPY --from=build /app/target/clinic-0.0.1-SNAPSHOT.jar app.jar

# App-ı işə sal
ENTRYPOINT ["java", "-jar", "app.jar"]
