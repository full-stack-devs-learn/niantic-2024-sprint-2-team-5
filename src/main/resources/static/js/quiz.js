document.addEventListener("DOMContentLoaded", () => {

    const startButton = document.getElementById("start-button")
    startButton = addEventListener("click", startQuiz)

});

function startQuiz(event) {
    document.getElementById("start-button").textContent = "Quiz started!"

    alert("You are starting the quiz! ")

}