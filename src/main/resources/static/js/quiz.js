document.addEventListener("DOMContentLoaded", () => {

        let currentQuestionIndex = 0
        const startButton = document.getElementById("start-button")
        const nextButton = document.getElementById("next-button")
        const quizContent = document.getElementById("quiz-content")

        document.getElementById("start-button").textContent = "Start Quiz!"


        startButton.addEventListener('click', startQuiz);
        nextButton.addEventListener('click', nextQuestion);


function startQuiz(event)
{
startButton.style.display = 'none';
nextButton.style.display = 'block';
const quizId = document.getElementById("quiz-id").value;

    const url = `/questions/${quizId}`;
     fetch(url)
     .then(response => { return response.text();
        })
        .then(data => {

           document.getElementById('quiz-content').innerHTML = data;


        })
        .catch(error => {
            console.log(error);
        });
}

function nextQuestion(event) {
const quizId = document.getElementById("quiz-id").value
currentQuestionIndex++;
const url = `/questions/${quizId}/${currentQuestionIndex}`;

fetch(url)
.then(response => { return response.text();
})
.then(data => {

document.getElementById('quiz-content').innerHTML = data;
})
.catch(error => {
console.log(error);
});
}

});


