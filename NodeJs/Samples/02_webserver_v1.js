const http = require("http");
const url = require("url");

// // Create a webserver
// const server = http.createServer((req, res) => {
//   // createServe accept a callback function as argument that needs 2 parameters; the request and the response
//   res.end("Hello from the server");
// });

// server.listen(8000, "127.0.0.1", () => {
//   console.log("Listening to requests on port 8000");
// });

// Add Routing
const server = http.createServer((req, res) => {
  const pathName = req.url;
  if (pathName === "/" || pathName === "/overview") {
    res.end("Hello from the OVERVIEW");
  } else if (pathName === "/product") {
    res.end("Hello from the PRODUCT");
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
