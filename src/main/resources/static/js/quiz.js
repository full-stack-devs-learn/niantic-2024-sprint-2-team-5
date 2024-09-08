document.addEventListener("DOMContentLoaded", () => {
    let currentQuestionIndex = 0;
    let correctAnswersCount = 0;
    let totalQuestions = 0;
    let userAnswers = {};          // Track user answers (by questionId)

    const startButton = document.getElementById("start-button");
    const nextButton = document.getElementById("next-button");
    const submitButton = document.getElementById("submit-button");
    const quizContent = document.getElementById("quiz-content");
    const totalQuestionsMessage = document.getElementById("total-questions-message");

    startButton.addEventListener('click', startQuiz);
    nextButton.addEventListener('click', nextQuestion);
    submitButton.addEventListener('click', submitQuiz);

    function startQuiz(event)
    {

        totalQuestionsMessage.style.display = 'none';  // Hide the total questions message when the quiz starts
        startButton.style.display = 'none';  // Hide the start button
        nextButton.style.display = 'none';   // Initially hide the next button
        submitButton.style.display = 'none'; // Hide submit button until the last question
        const quizId = document.getElementById("quiz-id").value;

        const url = `/questions/${quizId}/0`;  // get first question by quizId

        fetch(url)
            .then(response => response.text())
            .then(data => {
                document.getElementById('quiz-content').innerHTML = data;
                currentQuestionIndex = 0;

                // Set the total number of questions from the hidden input
              totalQuestions = document.getElementById("question-count").value;
            })
            .catch(error => {
                console.log(error);
            });
    }

    function nextQuestion(event)
    {
        currentQuestionIndex++;
        const quizId = document.getElementById("quiz-id").value;

        // get the next question if we're not at the last question yet
        if (currentQuestionIndex < totalQuestions) {
            const url = `/questions/${quizId}/${currentQuestionIndex}`;

            fetch(url)
                .then(response => response.text())
                .then(data => {
                    document.getElementById('quiz-content').innerHTML = data;
                    nextButton.style.display = 'none';
                })
                .catch(error => {
                    console.log(error);
                });

            // If this is the last question, show the submit button
            if (currentQuestionIndex === totalQuestions - 1) {
                nextButton.style.display = 'none';
                submitButton.style.display = 'block';
            }
        } else {
            submitQuiz(event); // If no more questions, automatically submit
        }
    }

    // track the user's answer selection using radio buttons
    document.addEventListener('change', function (event)
     {
        if (event.target.classList.contains('answer-radio')) {
            const answerId = event.target.value;
            const questionId = event.target.getAttribute('data-question-id');
            const isCorrect = event.target.getAttribute('data-is-correct') === 'true';

            // Store user's answer for the current question (using questionId as the key)
            userAnswers[questionId] = parseInt(answerId);

            if (isCorrect) {
                correctAnswersCount++;
            }
            // Show the next button if it's not the last question
            if (currentQuestionIndex < totalQuestions - 1) {
                nextButton.style.display = 'block';
            }
        }
    });

    function submitQuiz(event) {
        const quizId = document.getElementById("quiz-id").value;
        displayResults(quizId);
    }

    // display the final results
    function displayResults(quizId)
    {
        const url = `/quizzes/${quizId}/correct-answers`;

        fetch(url)
            .then(response => response.text())
            .then(data => {
                document.getElementById('quiz-content').innerHTML = `
                    <h1>Quiz Completed!</h1>
                    <p>Your final score: ${correctAnswersCount} / ${totalQuestions}</p>
                    <div>${data}</div>  <!-- Display the correct answers -->
                `;
                submitButton.style.display = 'none';
            })
            .catch(error => {
                console.log(error);
            });
    }
});
