# Makefile for interacting with the API

# API base URL
API_URL = http://localhost:8000

# Define resources and their IDs (modify as needed)
RESOURCE_ID = 1
RESOURCE_DATA = '{"name": "Updated Resource"}'

# Targets
.PHONY: get post put delete patch

get:
	curl -X GET $(API_URL)/recursos


create:
	@read -p "Enter resource name: " NAME; \
	curl -X POST -H "Content-Type: application/json" -d '{"name": "'$$NAME'"}' $(API_URL)/recursos

update:
	@read -p "Enter new resource name: " NEW_NAME; \
	curl -X PUT -H "Content-Type: application/json" -d '{"name": "'$$NEW_NAME'"}' $(API_URL)/recurso/$(RESOURCE_ID)


delete:
	curl -X DELETE $(API_URL)/recurso/$(RESOURCE_ID)

patch:
	curl -X PATCH -H "Content-Type: application/json" -d '{"name": "Updated Name"}' $(API_URL)/recurso/$(RESOURCE_ID)
