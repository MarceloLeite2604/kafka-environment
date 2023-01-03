IMAGES = $(strip $(shell docker images -q -f label=com.docker.compose.project=kenv))

up: build
	docker compose up -d

down:
	docker compose down

build:
	docker compose build

delete-images:
ifneq ($(IMAGES),)
	docker rmi -f $(IMAGES)
else
	$(info No images to remove)
endif

clean: down delete-images