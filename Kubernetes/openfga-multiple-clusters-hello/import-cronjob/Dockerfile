FROM alpine:3.23

ARG FGA_VERSION=0.7.8
ARG FGA_ARCH=amd64

WORKDIR /work

RUN apk add --no-cache ca-certificates curl jq && \
    curl -fsSL \
      "https://github.com/openfga/cli/releases/download/v${FGA_VERSION}/fga_${FGA_VERSION}_linux_${FGA_ARCH}.tar.gz" \
      -o fga.tar.gz && \
    tar -xxf fga.tar.gz -C /usr/local/bin fga && \
    chmod +x /usr/local/bin/fga && \
    rm -f fga.tar.gz && \
    fga version

COPY import-store.sh import-store.sh
RUN chmod +x import-store.sh

ENTRYPOINT ["./import-store.sh"]
