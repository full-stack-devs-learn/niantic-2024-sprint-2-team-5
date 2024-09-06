


document.addEventListener("DOMContentLoaded", () => {

        let currentQuestionIndex = 0
        const startButton = document.getElementById("start-button")
        document.getElementById("start-button").textContent = "Quiz started!"
        const previousButton = document.getElementById("prev-button")
        const nextButton = document.getElementById("next-button")
        
    loadQuestion()

    startButton.addEventListener('click', startQuiz);
    nextButton.addEventListener('click, nextPage')
});

function startQuiz(event)
{
const quizId = document.getElementById("quiz-id").value;

    const url = `/questions/${quizId}`;
     fetch(url).then(response => {
            return response.text();
        })
        .then(data => {
           document.getElementById('quiz-content').innerHTML = data;
            //console.log(h3)
        })
        .catch(error => {
            console.log(error);
        })
}

function loadQuestion()
{
}

function nextPage(event) {
    const quizId = document.getElementById("quiz-id").currentQuestionIndex++;
}



