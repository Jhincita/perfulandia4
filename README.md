﻿# perfulandia
# Instalar
Java
Maven
Docker
Postman
HeidiSQL

clone repository
git clone https://github.com/your-org/perfulandia4.git
cd perfulandia4

build con maven
mvn clean package

docker run -d -p 8080:8080 --name perfulandia-net -e SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/perfulandia -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=mipassword123 perfulandia // arreglar comando


