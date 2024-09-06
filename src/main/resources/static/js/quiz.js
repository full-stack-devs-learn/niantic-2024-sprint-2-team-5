document.addEventListener("DOMContentLoaded", () => {

        const startButton = document.getElementById("start-button")
        document.getElementById("start-button").textContent = "Quiz started!"
        document.getElementById("prev-button").textContent = "Prev"
        document.getElementById("next-button").textContent = "Next"
    loadQuestion()

    startButton.addEventListener('click', startQuiz);
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




