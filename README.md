# Internet Lab: Final Examination. Course 2022-2023
# ApolloFy

The domain of the exercise is already known for you since we sent you the code, and it's based on the course project. 

## Before you begin
* Read this file carefully
* Clone the repository to your computer
* BEFORE **touching** the code, run the application and see it working on your computer.
* You have example calls in files "resources/*.http". You can execute requests directly from this files without the need of an external http client like Postman
* Read the questions and the TODOs
* When attempting to answer the questions, modify the code in small steps and try the application (run it) after every step. In this way is easier to track possible errors
* A code that doesn't compile or run will be marked zero points
* All the questions are independent and can be answered in any order. So, if you get stuck in a question go ahead and attempt to answer another one.
* In the code you'll see **TODO**s where you need to insert new code. TODOs explain what you need to do and may contain some clues. Please,
  don't delete the TODOs from the code. TODOs are numbered according to the question number. When a question has more than one TODO they are
  numbered TODO X.1, TODO X.2 and so on, where X is the question number. There are few TODOs that don't need any code, they are there to explain code relevant to the question (and its answer)
* **As a guide, you can execute all the calls/test in file "resources/testsShouldPass.http". Tests should pass when all questions are answered**

## Tests
There is a suit of tests that should pass when all the questions are answered correctly

## Questions
* **Question 1**: When a track is created, its title must begin with a capital letter and its durations must be higher or equal to 5 and lower or equal to 300
    * When the parameters are valid the response status must be 200 and the body empty
    * When the parameters are invalid the response status must be 400 and the body must be similar to
  ```
  {
  "violations": [
  {
  "fieldName": "title",
  "message": "Track title must begin witb capital letter"
  },
  {
  "fieldName": "durationSeconds",
  "message": "must be greater than or equal to 5"
  }
  ]
  }
  ```
  You may achieve this by treating the exceptions: ConstraintViolationException and MethodArgumentNotValidException
* **Question 2**: Security
  At this moment the security is configured using the JWT method, but it allows everybody to call all the API entries. Obviously, those
  that require a logged-in user won't work if nobody is.
  You can observe (in the data.sql file) that we have three roles. Namely, ROLE_FREE, ROLE_PREMIUM and ROLE_PROFESSIONAL. You should modify
  the security configuration so that:
    * Only registered users can call entries with the "me" word in the path
    * Everybody can list tracks "GET /api/tracks"
    * ROLE_FREE users can only: list their own information "GET /api/me", list their authored tracks "GET /api/me/tracks" and list a given track
      "GET /api/tracks/{id}"
    * ROLE_PREMIUM users additionally can: create tracks "POST /api/tracks", add artists to tracks "PUT api/tracks/{trackId}/artists",  
      add genres to tracks "PUT api/tracks/{trackId}/genres", like tracks PUT "api/me/likedTracks/{trackId}" and list their liked tracks
      GET "api/me/likedTracks"
    * ROLE_PROFESSIONAL users additionally can: create, list and modify Playlists "api/me/playlists", list all users "GET /api/users" and get
      the top tracks and genres "GET /api/top/genres" "GET /api/top/tracks"

* **Question 3**: When looking for a non-existing element in the database an exception of type ElementNotFoundInBBDD is signaled. In this
  case the response status must be 404 and the body empty
* TODO 4: Add a selection of users - owned tracks with a projection using interface DTO ??? (hi ha temps????)
* **Question 5**: In the code we already have Playlists with tracks. Now, the requirements have changed, and we want Playlists to be
a collection of fragments of tracks. More formally, a track fragment is indicated with the initial millisecond and the final millisecond. 
Thus, we will determine the fragment of the track that we want to add to the Playlist.

This API call is inspired by the official Spotify documentation.  The Lab Internet teaching team has added the specific
functionality to indicate that we want to add just a part of the track to the Playlist.

https://developer.spotify.com/documentation/web-api/reference/#/operations/add-tracks-to-playlist

You should run the following test to check that your implementation is working correctly:

@Test
@WithMockUser("mperez@tecnocampus.cat")
void addTracksToPlayListWithTimeRange() 

