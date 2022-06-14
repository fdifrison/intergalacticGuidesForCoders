"use strict";

const john = {
  fullName: "John Smith",
  mass: 92,
  height: 1.95,
};

const mark = {
  fullName: "Mark Miller",
  mass: 78,
  height: 1.69,
};

const calcBMI = function () {
  this.bmi = this.mass / this.height ** 2;
  return this.bmi;
};

john.calcBMI = calcBMI;
mark.calcBMI = calcBMI;

john.calcBMI();
mark.calcBMI();

console.log(`${john.fullName} has a BMI (${john.bmi}) that is
${john.bmi > mark.bmi ? "higher" : "lower"} then ${mark.fullName}'s (${
  mark.bmi
})`);
