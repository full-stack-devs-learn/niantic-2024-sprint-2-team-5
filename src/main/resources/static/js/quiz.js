document.addEventListener("DOMContentLoaded", () => {

    const startButton = document.getElementById("start-button")
    loadQuestion()

});

function startQuiz(event) {
    document.getElementById("start-button").textContent = "Quiz started!"

}

function loadQuestion()
{
    const url = "/api/questions/${questionId}";
     fetch(url).then(response => {
            return response.json();
        })
            .then(data => {
               const h3 = document.querySelector('h3').innerHTML = data.questionText;
                console.log(h3)
                })
            })
            .catch(error => {
                console.log(error);
            })
    }
}