# Trivio Quizzlet App

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Development Process](#development-process)
- [Diagram](#diagram)
- [Challenges](#challenges)
  - [Challenge 1](#challenge-1)
  - [Challenge 2](#challenge-2)
- [Code Block Highlight](#code-block-highlight)
- [Retrospective](#retrospective)

## Description

This project is a result of a two-day pair programming collaboration between Dureti and Hannah. The goal was to create a dynamic quiz platform using Spring Boot, Thymeleaf, and JavaScript. The application features an intuitive interface where users can take quizzes, navigate through questions, and receive feedback upon completion. The back-end handles quiz management, answer tracking, and scoring to provide a seamless user experience.

- **How to use the project**: 
  
## Features

## Development Process

We used a Trello board to keep track of the tasks we needed to accomplish. This helped us to break down the project into smaller tasks.
![img.png](img.png)

### Trivio Database Diagram
![img_2.png](img_2.png)
We started the project by making the DAOs, Models, and Controllers. Once we finished those we started working on getting the "Start Quiz"
button to work and take you to the first questions. We added a next button to move you to the next question throughout the quiz.
We added a submit button, and after you press that you can see your results! We also created a page where you can add a new QuizTitle. We used bootswatch to style the website.
We would collaborate and talk through bugs in the code, and during the weekend we would plan times to meet and program together. We would talk over things we could work on individually
when we weren't programming together. When we met again we would explain the code,references that helped us, and our thought processes. This helped us to continue
working together to accomplish adding new features. 

## Challenges

## Challenge 1
- **Problem**: 
- Connecting the buttons to link to the right pages. I had troubles with having the right pathing to get the page to display.
- **Approach**: 
- I tried to change the pathing myself at first, I couldn't seem to get it right to display the page. I looked at past projects and demos, and did research on pathing.
- 
- **Final Solution**: 
- I finally realized the @GetMapping and the href linked to my button needed to match for me to get the result I wanted.
- I also realized i needed to return the html page in my functions in the controller.
- The solution is displayed in my code block highlight. I have my solution for the edit button displayed.
- 

## Challenge 2
- **Problem**: 
- **Approach**: 
- **Final Solution**: 

## Code Block Highlight
## Hannah
![img_3.png](img_3.png)
![img_4.png](img_4.png)


## Retrospective 
##### Hannah
- **Lessons learned**: I learned to take big tasks and break them into smaller ones. I also learned that I need to make sure I am looking through my
- syntax frequently for errors. There were times I thought I was missing something, but it was just a misspelling or a curly bracket.
- **What would you do differently**: I would try to take less time stuck on a bug. Next time when there is something I am stuck on I think I will take a quick break,
- then do more research until I understand what the problem is and how to fix it more clearly.
- **What would you do the same**: 
- I really enjoyed the order of the steps we did in the development process. The Trello board was very useful in keeping
- track of our steps. It was nice to focus on one thing at a time.
- **Future improvements if more time was available**:
- If I had more time I would've liked to be able to add questions/answers to the quizzes, and possibly a popup that says if answer is correct/incorrect.
- I think it would've been nice to have a previous button so the user could go back and change answers before they submit final answers.
- I also would've liked to work more on the design aspect of the website.I am very curious to learn how to animate with javascript.
- I appreciate the convenience of bootswatch, but I think it also would have been fun to do the design of the website from scratch.
