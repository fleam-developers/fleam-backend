FROM python:3.8.13-bullseye as dev_build

ENV DOCKERIZE_VERSION=v0.6.1 \
    POETRY_VERSION=1.1.13 \
    POETRY_CACHE_DIR='/var/cache/pypoetry' \
    POETRY_NO_INTERACTION=1 \
    POETRY_VIRTUALENVS_CREATE=false \
    PATH="$PATH:/root/.local/bin"

ARG APP_USER="root"
ARG UID=1001
ARG GID=1001

# Update the system
RUN apt-get update -y && \
    apt-get install -y --no-install-recommends \
    ca-certificates \
    wget \
    build-essential \
    curl \
    python3-dev \
    gfortran \
    musl-dev \
    # Install dockerize to simplify the running application
    && wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    # Install poetry for the project
    && curl -sSL https://install.python-poetry.org | python3 - \
    # Clean the apt cache
    && apt-get clean -y && rm -rf /var/lib/apt/lists/*


WORKDIR /app

# For caching purposes
COPY --chown=$APP_USER:$APP_USER ./poetry.lock ./pyproject.toml /app/

# Install poetry and dependencies
RUN poetry install --no-interaction \
    && poetry run pip install -U pip