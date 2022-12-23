FROM eclipse-temurin:11-alpine
WORKDIR /app
ARG JAR_FILE=stampy/target/*.war
COPY ${JAR_FILE} app.war
RUN mkdir uploads
RUN mkdir admin
VOLUME [ "/app/uploads" ]
VOLUME [ "/app/admin" ]
ENTRYPOINT ["java","-jar","app.war"]
