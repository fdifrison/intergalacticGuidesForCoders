const fs = require("fs");

// // Synchronous
// const textIn = fs.readFileSync(`${__dirname}/dummy_data/01_dummy_sync.txt`, "utf-8");
// console.log(textIn);
// const textOut = "\n\rIm writing to a file!";
// fs.appendFileSync(`${__dirname}/dummy_data/01_dummy_sync.txt`, textOut);

// // Asynchronous using callbacks
// fs.readFile("./dummy_async.txt", "utf-8", (err, data) => console.log(data)); // first argument is usually always the potential errors
// console.log("This will be execute before the callback!");

// async callbacks can be nested
fs.appendFile(
  `${__dirname}/dummy_data/01_dummy_async.txt`,
  "\nAdded text in async mode",
  "utf-8",
  (err) => {
    fs.readFile(
      `${__dirname}/dummy_data/01_dummy_async.txt`,
      "utf-8",
      (err, data) => {
        console.log(data);
      }
    );
  }
); // first argument is usually always the potential errors
console.log("This will be execute before the callback!");
