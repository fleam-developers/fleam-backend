from fastapi import FastAPI
from src.recommend import predict_recommendations_of_user, predict_recommendations_of_movie, get_voting, UserInput, ResponseOutput

app = FastAPI()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/recommendation/user/")
async def recommendation_user(userId:int, numOfRecommendations:int) -> ResponseOutput:
    user_votings = get_voting(userId)
    return predict_recommendations_of_user(user_votings, numOfRecommendations)

@app.get("/recommendation/movie/")
async def recommendation_movie(movieId:int, numOfRecommendations:int) -> ResponseOutput:
    print(predict_recommendations_of_movie(movieId, numOfRecommendations))
    return predict_recommendations_of_movie(movieId, numOfRecommendations)

