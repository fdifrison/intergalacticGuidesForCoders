"use strict";

const calcAverage = (name, score1, score2, score3) => [
  name,
  (score1 + score2 + score3) / 3,
];

const checkWinner = function (avg1, avg2) {
  if (avg1[1] > 2 * avg2[1]) {
    console.log(`${avg1[0]} win (${avg1[1]} to ${avg2[1]})`);
  } else if (avg2[1] > 2 * avg1[1]) {
    console.log(`${avg2[0]} win (${avg2[1]} to ${avg1[1]})`);
  } else {
    console.log(`No team wins! (${avg1[1]} to ${avg2[1]})`);
  }
};

const dolphinScore = calcAverage("dolphins", 85, 54, 41);
const koalaScore = calcAverage("koala", 23, 34, 27);

checkWinner(dolphinScore, koalaScore);
