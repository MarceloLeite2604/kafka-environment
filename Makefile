up: build
	docker compose up -d

down:
	docker compose down

build:
	docker compose build

delete-images:
	docker rmi $(docker images -f label=com.docker.compose.project=kenv -q)