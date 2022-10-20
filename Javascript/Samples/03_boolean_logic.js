// TEST 1
/* const dolphinAvgScore = (96 + 108 + 89) / 3;
const koalaAvgScore = (88 + 91 + 110) / 3; */

// TEST 2
/* const dolphinAvgScore = (97 + 112 + 101) / 3;
const koalaAvgScore = (109 + 95 + 123) / 3; */

// TEST 2
const dolphinAvgScore = (97 + 112 + 101) / 3;
const koalaAvgScore = (109 + 95 + 106) / 3;

console.log(dolphinAvgScore, koalaAvgScore);

const cutOffScore = 100;

if (dolphinAvgScore < cutOffScore && koalaAvgScore < cutOffScore) {
  console.log("There is no winner!");
}

if (dolphinAvgScore >= cutOffScore && koalaAvgScore >= cutOffScore) {
  if (dolphinAvgScore > koalaAvgScore) {
    console.log(`Dolphins win with a score of ${dolphinAvgScore}`);
  } else if (dolphinAvgScore === koalaAvgScore) {
    console.log("It is a draw!");
  } else {
    console.log(`Koala win with a score of ${koalaAvgScore}`);
  }
}
