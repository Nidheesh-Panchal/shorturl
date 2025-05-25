mvn clean package
docker build -t short-url:v0.0.1 .

docker compose up -d
docker compose logs -f