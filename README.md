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
* In the "resources" folder, you'll find files with the termination ".http" where you can see (and execute with Intellij http client) call examples

## Tests
In /test/java/cat/tecnocampus/apollofy/api, there is a suit of tests that should pass when all the questions are answered correctly

## Questions
### Question 1 
When a track is created, its title must begin with a capital letter and its durations must be higher or equal to 5 and lower or equal to 300
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
  There are no TODOs for this, but we solved this in the Tinder project

## Question 2
  At this moment the security is configured using the JWT method, but it allows everybody to call all the API entries. Obviously, those
  that require a logged-in user won't work if nobody is.  
  You can observe (in the data.sql file) that we have three roles. Namely, ROLE_FREE, ROLE_PREMIUM and ROLE_PROFESSIONAL. You should modify
  the security configuration so that:
    * Only registered users can call entries with the "me" word in the path
    * Everybody, even unregistered users, can list tracks "GET /api/tracks"
    * ROLE_FREE users can only: list their own information "GET /api/me", list their authored tracks "GET /api/me/tracks" and list a given track
      "GET /api/tracks/{id}"
    * ROLE_PREMIUM users additionally can: create tracks "POST /api/tracks", add artists to tracks "PUT api/tracks/{trackId}/artists",  
      add genres to tracks "PUT api/tracks/{trackId}/genres", like tracks PUT "api/me/likedTracks/{trackId}" and list their liked tracks
      GET "api/me/likedTracks"
    * ROLE_PROFESSIONAL users additionally can: create, list and modify Playlists "api/me/playlists", list all users "GET /api/users" and get
      the top tracks and genres "GET /api/top/genres" "GET /api/top/tracks"

## Question 3
When looking for a non-existing element in the database an exception of type ElementNotFoundInBBDD is signaled. In this
  case the response status must be 404 and the body empty

## Question 4 Add a selection of users - owned tracks with a projection using interface DTO ??? (hi ha temps????)

## Question 5
Our platform, Apollofy, is getting noticed and used among disc-jockeys (also known as DJ) and they would like to have lists of fragments of tracks.
That is, the lists not only need to contain tracks but also the moment in which they should begin and end when played in the disco or musical bar.

We will call these lists **DJList**. They are going to be owned by a user, and instead of Tracks they will list **DJListTrackFragment**. Actually,
DJListTrackFragment will play the role of an association class pointing to both a User and a Track, and containing the initial and final 
milliseconds of the track fragment.

Note that, conceptually, they are very similar to a Playlist but the implementation differs because we don't want DJList to 
have a list of track fragments (DJListTrackFragment). Instead, it will be the DJListTrackFragment that will point to DJList and Track. 
See the diagram in the blackboard.

For this exercise you should implement a REST method that handles @PostMapping("/djlist/{id}/tracks") API calls to register new track
fragments associations with the DJList specified as parameter. We assume that the DJList already exists in the database 
(actually there is already one as you can see in the data.sql file, owned by user jalcobe@tecnocampus.cat).




