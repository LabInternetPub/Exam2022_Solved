# Internet Lab: Base code for the exam (course 2022-2023)
# ApolloFy

In this repository you'll find the base code we'll be using during the exam. We provide it for you to get familiar with the code and the domain.
As you see it is a partial implementation of the course project (Apollofy).

During the exam you'll need to download a new repository that will contain this code (which may have some minor differences) with the questions


Implement a feature that allows adding tracks to a playlist.  In this version, a fragment of the track will be specified.
More formally, the initial millisecond and the final millisecond will be indicated, and thus we will determine the fragment 
of the track that we want to add to the Playlist.

This API call is inspired by the official Spotify documentation.  The Lab Internet teaching team has added the specific
functionality to indicate that we want to add just a part of the track to the Playlist.

https://developer.spotify.com/documentation/web-api/reference/#/operations/add-tracks-to-playlist

You should run the following test to check that your implementation is working correctly:

@Test
@WithMockUser("mperez@tecnocampus.cat")
void addTracksToPlayListWithTimeRange() 

