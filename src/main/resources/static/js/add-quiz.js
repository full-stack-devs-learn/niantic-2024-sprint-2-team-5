document.addEventListener("DOMContentLoaded", () => {
    const addQuiz = document.getElementById("add-quiz");
    const title = document.getElementById("title");

    title.addEventListener("input", () => {
        // Remove validation feedback when the user starts typing
        addQuiz.classList.remove("was-validated");
    });

    addQuiz.addEventListener("submit", (event) => {
        // Check if the title is empty or not
        if (!title.value.trim()) {
            event.preventDefault();
            title.setCustomValidity("Please enter a quiz title.");
            addQuiz.classList.add("was-validated");
        } else {
            // If title is valid, remove any previous error messages
            title.setCustomValidity("");
        }

        // Check form validity (in case of other fields)
        if (!addQuiz.checkValidity()) {
            event.preventDefault();
            addQuiz.classList.add("was-validated");
        }
    });
});
