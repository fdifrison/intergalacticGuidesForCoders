"use strict";

const playerWins = function () {
  document.querySelector("body").style.backgroundColor = "#60b347";
  document.querySelector(".number").style.width = "30rem";
  document.querySelector(".number").textContent = randNum;
  document.querySelector(".message").textContent = "Correct Number!";
  currentScore = document.querySelector(".score").textContent;
  if (currentScore > highScore) {
    highScore = currentScore;
    document.querySelector(".highscore").textContent = highScore;
  }
};

const playerLose = function (msg) {
  document.querySelector(".message").textContent = msg;
  currentScore > 0 ? (currentScore -= 1) : 0;
};

const resetGame = function () {
  currentScore = 20;
  document.querySelector(".message").textContent = "Start guessing ...";
  document.querySelector(".score").textContent = currentScore;
  document.querySelector(".guess").value = "";
  document.querySelector("body").style.backgroundColor = "#222";
  document.querySelector(".number").style.width = "15rem";
  document.querySelector(".number").textContent = "?";
  randNum = genRandNum();
};

const genRandNum = function () {
  const randNum = Math.trunc(Math.random() * 20) + 1;
  // show the secret number for debug
  document.querySelector(".number").textContent = randNum;
  return randNum;
};

let highScore = 0;
let currentScore = 20;
let randNum = genRandNum();

const getGuessValue = function () {
  const guess = Number(document.querySelector(".guess").value);
  if (!guess) {
    // if there is no input in the browser
    const msg = (document.querySelector(".message").textContent = "No number");
  } else if (guess === randNum) {
    // if player wins
    playerWins();
  } else if (guess !== randNum) {
    // if player too high or too low
    guess < randNum ? playerLose("Too low") : playerLose("Too high");
  }
  if (guess) {
    // whenever a guess is made
    document.querySelector(".score").textContent = currentScore;
  }
  if (currentScore <= 0) {
    // if player lost
    document.querySelector(".message").textContent = "You Lost!";
  }
};

// add a listener to the check button whenever it is clicked
document.querySelector(".check").addEventListener("click", getGuessValue);

// add listener to again button to restart the game
document.querySelector(".again").addEventListener("click", resetGame);
