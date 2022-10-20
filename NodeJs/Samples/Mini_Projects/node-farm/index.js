const http = require("http");
const fs = require("fs");
const path = require("path");
const url = require("url");

const replaceTemplate = require(path.normalize(
  `${__dirname}/modules/replaceTemplate`
));

// load data in sync mode only once
const tempOverview = fs.readFileSync(
  path.normalize(`${__dirname}/templates/template_overview.html`),
  "utf-8"
);
const tempCard = fs.readFileSync(
  path.normalize(`${__dirname}/templates/template_card.html`),
  "utf-8"
);
const tempProduct = fs.readFileSync(
  path.normalize(`${__dirname}/templates/template_product.html`),
  "utf-8"
);

const data = fs.readFileSync(
  path.normalize(`${__dirname}/data/farm_data.json`),
  "utf-8"
);
const dataJSON = JSON.parse(data);

//create a server object
const server = http.createServer((req, res) => {
  const { query, pathname } = url.parse(req.url, true); // extract directly what needed in an object as constant

  // home page
  if (pathname === "/") {
    res.writeHead(200, { "Content-type": "text/html" });

    // map the function replaceTemplate to each object in the JSON file
    // and then join them all in one string so that it can be passed
    // to the overview html page
    const cardsHtml = dataJSON
      .map((el) => replaceTemplate(tempCard, el))
      .join("");
    const output = tempOverview.replace("{%PRODUCT_CARDS}", cardsHtml);
    res.end(output);
  }
  // product
  else if (pathname === "/product") {
    const product = dataJSON[query.id];
    const output = replaceTemplate(tempProduct, product);
    res.end(output);
  }
  // API
  else if (pathname === "/api") {
    res.end("API");
  }
  // 404 NOT FOUND
  else {
    res.writeHead(404, {
      "Content-type": "text/html",
      "my-own-header": "hello-world",
    });
    res.end("<h1>Page not Found</h1>");
  }
});

//run server
server.listen(8000, "127.0.0.1", () => {
  console.log("Listening to requests on port 8000");
});
