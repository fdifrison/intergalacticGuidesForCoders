const fs = require("fs");
const http = require("http");
const url = require("url");

// WEB API

// it is better to read the file in sync mode  only once
const data = fs.readFileSync(
  `${__dirname}/dummy_data/03_api_data.json`,
  "utf-8"
);

dataObj = JSON.parse(data);

const server = http.createServer((req, res) => {
  const pathName = req.url;
  if (pathName === "/") {
    res.end("HOME PAGE");
  } else if (pathName === "/API") {
    // having the json read as async action is not ideal since we are reading the json each time we hit the api webpage
    // fs.readFile(
    //   `${__dirname}/dummy_data/03_api_data.json`,
    //   "utf-8",
    //   (err, data) => {
    //     const jsonData = JSON.parse(data);
    //     res.writeHead(200, { "Content-type": "application/json" });
    //     res.end(data);
    //   }
    // );
    res.writeHead(200, { "Content-type": "application/json" });
    res.end(data);
  } else {
    res.writeHead(404, {
      "Content-type": "text/html",
      "my-own-header": "hello-world",
    });
    res.end("This page cannot be found");
  }
});

server.listen(8000, "127.0.0.1", () => {
  console.log("Listening to requests on port 8000");
});
