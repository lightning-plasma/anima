FROM mysql:8.0

COPY ./table/*.sql /docker-entrypoint-initdb.d/
COPY ./init-data/*.sql /docker-entrypoint-initdb.d/
