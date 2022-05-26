from fastapi import FastAPI
from src.recommend import predict_recommendations_of_user, predict_recommendations_of_movie, get_voting

MAX_MOVIE_ID = 193609

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/recommendation/user/")
async def recommendation_user(userId:int, numOfRecommendations:int) -> list:
    user_votings = get_voting(userId)

    # Remove keys with values in user_votings where key > MAX_MOVIE_ID
    for key in list(user_votings.keys()):
        if int(key) > MAX_MOVIE_ID:
            del user_votings[key]

    return predict_recommendations_of_user(user_votings, numOfRecommendations)

@app.get("/recommendation/movie/")
async def recommendation_movie(movieId:int, numOfRecommendations:int) -> list:
    return predict_recommendations_of_movie(movieId, numOfRecommendations)