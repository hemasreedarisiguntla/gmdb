The G Movie Database
Fans are crazy for movies and the G company would like to build an application for users to browse all the movies of the world!

Your team is to develop the specs and implement the API.

Objectives
Use what you've learned about pair programming, the test cycle, and OOP to fulfill the acceptance criteria.

Your final code should have:

No compilation errors.
No failing tests.
Meet acceptance criteria.
API spec in the README.md of your repository.
Apply what you've learned so far.

Follow TDD Practices
Make sure your API follows RESTful principles
Use SEAT to help write tests
Use unit testing, integration testing, mocking, etc where appropriate
Apply ZOMBIES for test coverage
Instructions
Create a project with Spring Initializer.
Create a repository.
Work on the project, following the stories and the objectives listed above.
Submit a link to your completed code in Learn. Both of you should submit the same link.
Here is a link to some test data  , but keep in mind this is data to start with and may not represent the models your final solution has. It is NOT REQUIRED to use this data, but feel free to copy this into your project if it is useful to you.

Stories and Acceptance Criteria
Tip: When reading through stories, try focusing on writing the test that will fulfill the criteria first. Stoires and their critera outline behavior, not code.

As a user, I should see a list of movies when I visit GMDB.

When I visit GMDB
Then I can see a list of all movies.
As a user, I can browse each movie so I can learn all the details.

Rule: Movie details include title, director, actors, release year, description and star rating.

Given an existing movie
When I visit that title
Then I can see all the movie details.

Given a non-existing movie
When I visit that title
Then I receive a friendly message that it doesn't exist.
As a user, I can give a star rating to a movie so that I can share my experiences with others.

Given an existing movie
When I submit a 5 star rating
Then I can see it in the movie details.

Given a movie with one 5 star rating and one 3 star rating
When I view the movie details
Then I expect the star rating to be 4.
As a user, I can review a movie so that I can share my thoughts about it.

Given an existing movie
When I submit a star rating and text review
Then I can see my contribution on the movie details.

Given an existing movie
When I submit a text review without a star rating
Then I receive a friendly message that a star rating is required.
