FROM mysql
RUN apt-get update
RUN mkdir -p /app/scripts
COPY scripts/schema.sql /docker-entrypoint-initdb.d/cargo_schema.sql
RUN chmod 777 /docker-entrypoint-initdb.d/cargo_schema.sql