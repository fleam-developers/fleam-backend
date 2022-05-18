services=""
if [ $1 = "all" ]; then
	services="config-server discovery-server gateway-service account-service movie-service"
else
	services=$1
fi

for d in $services; do (
    cd "$d" &&
    echo "========================Building ${d} JAR========================" &&
    ./mvnw package -Dmaven.test.skip &&
    echo "========================Building ${d} Docker Image========================" &&
    docker build -t fleam-backend_"${d}" .
    );
done

