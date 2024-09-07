document.addEventListener("DOMContentLoaded", () => {

    let currentQuestionIndex = 0;  // Track the current question index
    let correctAnswersCount = 0;   // Track the number of correct answers
    let totalQuestions = 0;        // Track the total number of questions
    let userAnswers = [];          // Track user answers

    const startButton = document.getElementById("start-button");
    const nextButton = document.getElementById("next-button");
    const quizContent = document.getElementById("quiz-content");


    startButton.addEventListener('click', startQuiz);
    nextButton.addEventListener('click', nextQuestion);

    function startQuiz(event) {
        startButton.style.display = 'none';  // Hide the start button
        nextButton.style.display = 'none';   // Initially hide the next button
        const quizId = document.getElementById("quiz-id").value;

        const url = `/questions/${quizId}/0`;  // Load the first question by quizId

        fetch(url)
            .then(response => response.text())
            .then(data => {
                document.getElementById('quiz-content').innerHTML = data;  // Insert the first question into the page
                currentQuestionIndex = 0;  // Reset question index

                // Set the total number of questions from the hidden input
                totalQuestions = document.getElementById("question-count").value;
            })
            .catch(error => {
                console.log(error);
            });
    }

   function nextQuestion(event) {
       currentQuestionIndex++;  // Increment the question index
       const quizId = document.getElementById("quiz-id").value;

       // Check if we are at the last question
       if (currentQuestionIndex >= totalQuestions) {
           displayResults(quizId);  // Display the results after the last question
           return;
       }

       // Fetch the next question
       const url = `/questions/${quizId}/${currentQuestionIndex}`;

       fetch(url)
           .then(response => response.text())
           .then(data => {
               document.getElementById('quiz-content').innerHTML = data;  // Replace the current question with the next one
               nextButton.style.display = 'none';  // Hide the next button until an answer is selected
           })
           .catch(error => {
               console.log(error);
           });
   }


// Event listener to track the user's answer selection using radio buttons
    document.addEventListener('change', function (event) {
        if (event.target.classList.contains('answer-radio')) {
            const answerId = event.target.value;
            const questionId = event.target.getAttribute('data-question-id');
            const isCorrect = event.target.getAttribute('data-is-correct') === 'true';

            // Store user's answer for the current question
            userAnswers[questionId] = parseInt(answerId);

            // Check if the selected answer is correct
            if (isCorrect) {
                correctAnswersCount++;  // Increment correct answers count if answer is correct
            }

            // Show the next button after an answer is selected
            nextButton.style.display = 'block';
        }
    });

    function displayResults(quizId) {
    const url = `/quizzes/${quizId}/correct-answers`;

            fetch(url)
                .then(response => response.text())
                .then(data => {
                    document.getElementById('quiz-content').innerHTML = `
                        <h1>Quiz Completed!</h1>
                        <p>Your final score: ${correctAnswersCount} / ${totalQuestions}</p>
                        <div>${data}</div>  <!-- Display the correct answers -->
                    `;
                })
                .catch(error => {
                    console.log(error);
                });
 }
});
