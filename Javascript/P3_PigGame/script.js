"use strict";

/* 
Games instructions
Each player in its turn can choose to roll the dice as many times as he wants. 
If he roll a 1 then it lose all its current points and the turn goes to player 2.
If he holds, the currents point rolled goes into its cumulative score.
The first player to reach 100 points win
*/

// create constant for elements
const scores = document.querySelectorAll(".score");
const currentScores = document.querySelectorAll(".current-score");
const player1 = document.querySelector(".player--0");
const player2 = document.querySelector(".player--1");
const dice = document.querySelector(".dice");
const diceBtn = document.querySelector(".btn--roll");
const holdBtn = document.querySelector(".btn--hold");
const newGameBtn = document.querySelector(".btn--new");

let activePlayer;
let pCurScore;
let pTotScore;

// functions
const enableGame = function () {
  diceBtn.addEventListener("click", rollDice),
    holdBtn.addEventListener("click", holdScore);
};
const disableGame = function () {
  diceBtn.removeEventListener("click", rollDice),
    holdBtn.removeEventListener("click", holdScore);
};

const setNewGame = function () {
  for (let s of scores) s.textContent = 0;
  for (let cs of currentScores) cs.textContent = 0;
  dice.classList.add("hidden");
  player1.classList.remove("player--winner");
  player2.classList.remove("player--winner", "player--active");
  activePlayer = player1;
  activePlayer.classList.add("player--active");
  enableGame();
};

const rollDice = function () {
  if (dice.classList.contains("hidden")) dice.classList.remove("hidden");
  const roll = Math.trunc(Math.random() * 6) + 1;
  dice.src = `dice-${roll}.png`;
  return currentScore(roll);
};

const currentScore = function (roll) {
  pCurScore = activePlayer.querySelector(".current-score");
  roll !== 1
    ? (pCurScore.textContent = +pCurScore.textContent + roll)
    : ((pCurScore.textContent = "0"), switchPlayer());
  activePlayer.classList.add("player--active");
};

const switchPlayer = function () {
  activePlayer.classList.remove("player--active");
  activePlayer === player1
    ? (activePlayer = player2)
    : (activePlayer = player1);
  activePlayer.classList.add("player--active");
};

const holdScore = function () {
  pTotScore = activePlayer.querySelector(".score");
  pCurScore = activePlayer.querySelector(".current-score");
  if (pCurScore.textContent !== "0") {
    pTotScore.textContent = +pCurScore.textContent + +pTotScore.textContent;
    pCurScore.textContent = "0";
    pTotScore.textContent >= winningScore
      ? (activePlayer.classList.add("player--winner"),
        activePlayer.classList.remove("player--active"),
        disableGame(),
        dice.classList.add("hidden"))
      : switchPlayer();
  }
};

// set-up beginning configuration
setNewGame();
newGameBtn.addEventListener("click", setNewGame);
const winningScore = 10;
// set up done
