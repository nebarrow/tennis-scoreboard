const form = document.getElementById("matchForm");
const playerOne = document.getElementById("playerOne");
const playerTwo = document.getElementById("playerTwo");

form.addEventListener("submit", function (event) {
    let isValid = true;
    document.querySelectorAll('.error-message').forEach(error => {
        error.classList.remove("show");
        error.textContent = '';
    })

    if (!validateNameLanguage(playerOne.value) || !validateNameLanguage(playerTwo.value)) {
        showError( 'Name should contain only English letters!');
        isValid = false;
    }

    if (!validateNameLength(playerOne.value) || !validateNameLength(playerTwo.value)) {
        showError( 'Name should be between 3 and 25 characters!');
        isValid = false;
    }

    if (!validateSameOrNot(playerOne.value, playerTwo.value)) {
        showError( 'Names should be difference!');
        isValid = false;
    }

    if (!isValid) {
        event.preventDefault();
    }
});

function validateNameLanguage(name) {
    const regex = /^[a-zA-Z\s]+$/;
    return regex.test(name);
}

function validateSameOrNot(playerOne, playerTwo) {
    return playerTwo !== playerOne;
}

function validateNameLength(name) {
    return name.length >= 3 && name.length <= 25;
}

function showError(message) {
    const error = document.getElementById(`error-player`);
    error.textContent = message;
    error.classList.add('show');
}