"use strict";

const printForecast = function (array) {
  let str = "";
  for (let index = 0; index < array.length; index++) {
    const element = array[index];
    str += `... ${element}°C in day ${index + 1}`;
  }
  str += "...";
  console.log(str);
};

// Testing

const test1 = [17, 21, 23];
const test2 = [12, 5, -5, 0, 4];

printForecast(test2);
