# Html Manual

by Ing. Giovanni Frison

---

## Front-End Development

With Front-end development we indicate the writing of html, css and js code that will be rendered by the browser: i.e. what we can see when we open a webpage. When querying the browser for a particular domain, the browser is sending a request to the web server that is hosting the webpage. The response is a collection of files (html, css, js and picture) that the browser will render for us. When this files are simply sent by the web server without modification, we have a `static website`. Nowadays, the vast majority of websites are dynamic, meaning that the webserver is communicating with some applications and databases to continuously update the webpage content. A whole field of development is devoted to handle all this actions and it is called `back-end` development. Common back-end languages are node.js, php, python etc.

Coming back to the topic, the tree languages responsible for the front-end have different tasks:

* `html` is responsible for the content of the page
* `css` is responsible for the presentation of the content, i.e. its styling
* `javascript` is the programming language of the front-end responsible for the dynamic and the responsive effects. It can also be used to load data from webserver and create entire webpages called web applications.

## What is HTML

HTML is a markup language (not a programming language) and stands for Hyper Text Markup Language and it is the standard for creating webpages. It is a composition of elements that are rendered by the browser.
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


## Semantic Html
With **Semantic** Html we basically mean the use of html elements not only to give a proper render in the browser but to create a more clear and informative structure/meaning to the html code. **We use semantic html to give a clear meaning to elements**. This concept came along with the introduction of Html5. For example we could use the `<div>` element as container in any situation, but it would be more semantically meaningful, e.g. for a navigation bar, to use a less generic and more specific `<nav>` element. 

Another reason to use semantic html is that search engine are able to "read the html structure" and better index the search.

Probably the most important reason is to give full `accessibility` to the webpage content for blind people or anyone using a screen reader.

## Basics of Html

Following the anatomy of an html element:

<img src="html_element.png">

### DOCTYPE

The DOCTYPE declaration helps the browser to better display the page, it has to be declared only once at the top of the page and is not case sensitive. For html5 the declaration is `<!DOCTYPE html>`

### Head & Body

`<head>` and `<body>`, together with DOCTYPE, are the other fundamental part of an html code. The head contains  metadata about the page and its content is not rendered by the browser; The body instead contains everything that is rendered: headings, paragraphs, images etc..

### Heading

Headings tag goes from `<h1>` to `<h6>` in decreasing order of importance/size. Search engines use headings to structure the website content and as a consequence users can be more or less attracted by the weblink. Heading shouldn't be used to make a text bigger or smaller since they have a specific purpose in the page structure. Headers have a predefined font size but this can be override with the `style="font-size:60px";` attribute (font-size is a CSS attribute).

### Paragraph

Paragraph are tagged with `<p>`; they start always on a new line and have a predefined margin above and below. The browser is in control of the spacing, since it has to handle different window size and resolutions, therefore, adding extra spaces between words is useless, they will be stripped away. `<hr>`indicates a line break between two paragraph and it is rendered with an horizontal ruler, while `<br>` is a simple line break and can be placed also inside a single paragraph. If we want to preserve spaces and line breaks as they are written we can use the `<pre>` tag which usually comes with a default Courier font.

### Links

Links are defined by the tag `<a>`, the url of the link is indicated by the `href=` keyword, optionally followed by a string that will be clickable to redirect to the url. N.B. urls can have a absolute or a relative path; absolute path are discouraged since they refers to other webpages that can have copyright or modify the image in the long run, while relative path are relative to the website domain and therefore permanent.

```html
<a href="www.google.it">Go to google page<\a>
```

### Images

To render images the tag is `<img>` and it has three arguments, `src` the source of the image, `alt` an optional alternative text, `width` and `<height>`- Img elements don't require any content (there is no closing tag), instead an attribute, i.e. a piece of data, can be inserted inside the tag; src (source) is the attribute that specify the path and the filename of the image to show.

Another important attribute is `alt` (alternative text) i.e. a description of the image. This is important for two reasons, the first is related to the ability of web search engine to associate the image to a specific search, the second is to allow blind people to let the web reader tell them what the image is about. The alt description won't be printed to the screen unless the image itself cannot be found, in which case the alt text will substitute the image.

```html
<img src="myImage.jpg" alt="Some text describing the image" width="104" height="142">
```

### Attributes

Everything inside the tag is referred as an attribute (es. src, width, height etc..). Some other useful attributes are:

* `<p style="colore: red">` style serves to specify attribute on text, like the color, fontsize etc..
* `<html lang="en-US">` lang serves to help the search engines to identify the language of the webpage
* `<p title="I'm a tooltip">` title creates a tooltip that appear when the mouse is positioned on the element

Here can be found a complete set of the attributes https://www.w3schools.com/tags/ref_attributes.asp

### Html entities
Html entities are printable special characters defined by a special html code. A complete list can be found here https://www.freeformatter.com/html-entities.html.


### Text Style

Text appearance can be altered with specific tags:

* `<strong>` for bold text (former `<b>` but wasn't semantic html)
* `<em>` for italic text (former `<i>`)


### Lists

Lists can be defined as `Ordered` or `Unordered`.
Ordered lists are enclosed in the `<ol>` tag and each element is numbered progressively with the `<li>` tag.
Unordered lists are enclosed in the `<ul>` tag each element is essentially a bullet point with the same `<li>` tag.

### Hyperlinks

Links can be divided into two major categories: the ones pointing inside the website domain (i.e. to another page of the same website) or the ones that point another external website. The structure of the tags is the same for the two typologies. We start with a `<a>` tag (anchor) and inside the tags we specify the text that wil be displayed on the webpage that will have the hyperlink attached. The actual url of the page will be an attribute inside the opening tag called `href`. Another attribute we may want to specify is the `target=_blank` meaning that the link will be opened in a new tab. For an internal link, instead of the complete url path we can simply place in the href attribute the name of the .html file at which we are pointing at. Finally, if we want to create a dummy link that doesn't point anywhere, we can specify `href="#"`, in this way, we can click the link but the only behavior we will observe is to go back to the top of the current page we are already on.


### Structuring the webpage

If we place elements in the body of the webpage without any particular structure, they will be simply placed one below the other. Instead, there are specific html5 elements that should be used to structure specific parts of the page. 

For example, if we have a series of hyperlinks at the top of the page we want them to be grouped in a `<nav>` (navigation bar) element. The appearance of the page won't change, we have simply grouped a series of link inside an invisible box, but when we will be adding a layout to the page it will be fundamental to have such structure.

Again, the top part of the page, i.e. the title and the nav-bar can be placed inside another html element called `<header>` indicating the top part of the page.

If it is a blog page, another common element for the actual content part is the `<article>` element. The article element itself can and should have an header element inside to better structure the content.

At the end of the body element usually there is a `<footer>`.

All of these elements serves for two purpose mainly: the first, as already stated, to later attach the elements to a layout and the second to give a semantic structure to the page.