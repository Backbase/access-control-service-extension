# Docker inheritance
FROM repo.backbase.com/backbase-docker-releases/access-control:DBS-2.19.0-cr.190

ARG JAR_FILE
COPY target/${JAR_FILE} /app/WEB-INF/lib/
