"use strict";

const calcTip = function (bill) {
  if (bill < 0) {
    console.log("bill can't be < 0");
  } else {
    return bill >= 30 && bill <= 300 ? bill * 0.15 : bill * 0.2;
  }
};

const tips = [calcTip(125), calcTip(555), calcTip(44)];

const total = tips[0] + tips[1] + tips[2];

console.log(`We got the following tips ${tips}`);
console.log(`for a total amount of ${total}`);
