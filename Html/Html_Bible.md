<h1>My Personal Html Bible</h1>

by Ing. Giovanni Frison

---

## What is HTML

HTML stands for Hyper Text Markup Language and it is the standard for creating webpages. It is a composition of elements that are rendered by the browser.
Following a basic html example:

```html
<!DOCTYPE html>             <!--it says this is an html5 document, and this is a comment-->
<html>                      <!--'html' is the root of the html page-->
<head>                      <!--'head' contains metadata about the page-->
<title>Page Title</title>   <!--being in the head section, it wont be rendered, but it will be the name in the page tab-->
</head>                     <!--'/head' indicates the end of the head section-->
<body>                      <!--'body' is the body of the page, where almost everything is displayed-->

<h1>My First Heading</h1>   <!--'h1' the element for the biggest heading format-->
<p>My first paragraph.</p>  <!--'p' is the element for paragraph-->

</body>
</html>
```

An html element is everything from the opening to the closing tag (es. `<p>...<\p>`). Some elements have no content like `<br>` (line break) and none of them is rendered by the browser, only their content.


## BASICS

### DOCTYPE

The DOCTYPE declaration helps the browser to better display the page, it has to be declared only once at the top of the page and is not case sensitive. For html5 the declaration is `<!DOCTYPE html>`

### Heading

Headings tag goes from `<h1>` to `<h6>` in decreasing order of importance/size

### Links

Links are defined by the tag `<a>`, the url of the link is indicated by the `href=` keyword, optionally followed by a string that will be clickable to redirect to the url. N.B. urls can have a absolute or a relative path; absolute path are discouraged since they refers to other webpages that can have copyright or modify the image in the long run, while relative path are relative to the website domain and therefore permanent.

```html
<a href="www.google.it">Go to google page<\a>
```

### Images

To render images the tag is `<img>` and it has three arguments, `src` the source of the image, `alt` an optional alternative text, `width` and `<height>`

```html
<img src="myImage.jpg" alt="Some text" width="104" height="142">
```

### Attributes

Everything inside the tag is referred as an attribute (es. src, width, height etc..). Some other useful attributes are:

* `<p style="colore: red">` style serves to specify attribute on text, like the color, fontsize etc..
* `<html lang="en-US">` lang serves to help the search engines to identify the language of the webpage
* `<p title="I'm a tooltip">` title creates a tooltip that appear when the mouse is positioned on the element

Here can be found a complete set of the attributes https://www.w3schools.com/tags/ref_attributes.asp






