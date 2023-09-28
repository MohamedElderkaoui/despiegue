

## Table of Contents

- [Introduction](#introduction)
- [Creating Python HTTP Server from the Command Line
  Terminal](#creating-python-http-server-from-the-command-line-terminal)
- [Accessing the Python HTTP Server
  Locally](#accessing-the-python-http-server-locally)
- [Access Python HTTP Server Over the
  Network](#access-python-http-server-over-the-network)
- [Creating a Python HTTP Server via
  Script](#creating-a-python-http-server-via-script)
- [Customizing the Python HTTP Server with an index.html
  File](#customizing-the-python-http-server-with-an-indexhtml-file)
- [Summary](#summary)
- [Next Steps](#next-steps)

## Introduction

Python\'s standard library includes a simple web server that supports
web client-server communication. Although the `http.server` module has
limited security and [should not be use in a production
environment](https://docs.python.org/3/library/http.server.html), it is
useful for developmental purposes and local file sharing.

In this article, we\'ll discuss how the Python http server module can be
used from the terminal, and then we\'ll cover how to use it within a
Python script.

## Creating Python HTTP Server from the Command Line Terminal

To launch a Python HTTP server from the command-line, first open the
terminal and navigate to the directory that will be hosted to the
server. In our example, we\'ll navigate to a folder we\'ve created that
contains `hello-world.txt` and `lorem-ipsum.txt`:

```bash
cd ~/projects/http-server
```

From this directory, we can run the command `python -m http.server` to
start a local HTTP server. By default, this will create a server at port 8000. We can also specify a port by running the command
`python -m http.server PORT_NUMBER`.

::: {#waldo-tag-14051}
:::

## Accessing the Python HTTP Server Locally

Now that we\'ve launched the server, we can access it on our local
device. To access the server, open a browsing window and enter
`http://localhost:PORT_NUMBER` into the URL field. If a port number is
not specified in the previous step, the server will be found at
`http://localhost:8000`.

The browser window will then show a list of files in the local
directory:

::: img-container
![Example file list in Python HTTP server
directory](/img/initialcommit/http-server-example.jpg "Example file list in Python HTTP server directory")
:::

From here, users can open or download any of the hosted files.

## Access Python HTTP Server Over the Network

Once the server is launched, users can also access the page from other
devices that are connected to the same LAN or WLAN network. To access
this server, we first need to get the IP address of the host device.

To do this, we can navigate to the terminal and enter either `ipconfig`
on a Windows device, or `ifconfig` on a Linux, Unix, or macOS device.
Once we\'ve obtained the IP address of the host machine, we can then
access the server on any device on the same network by simply opening a
browser window and entering, `http://IP_ADDRESS:8000/`. As on the host
device, this page will display the list of files in the directory.

## Creating a Python HTTP Server via Script

In addition to launching a server from the terminal, Python\'s
`http.server` module can be used to start a server using the following
script:

```python
import SimpleHTTPServer
import SocketServer

PORT = 8000
Handler = SimpleHTTPServer.SimpleHTTPRequestHandler
httpd = SocketServer.TCPServer(("", PORT), Handler)
httpd.serve_forever()
```

By default, this will start a server in the working directory, but a
directory location can also be specified. As with the previous example,
this server can be accessed on the host device by typing
`http://localhost:8000/` into the browser window or on other network
devices by entering `http://IP_ADDRESS:8000/` into a browser window.

::: {#waldo-tag-14052}
:::

## Customizing the Python HTTP Server with an index.html File {#customizing-the-python-http-server-with-an-indexhtml-file}

The `http.server` module is not limited to just hosting a list of files.
We can also use this module to host a website based on a custom
`index.html` file. With this approach, the URL will show the contents of
the `index.html` file instead of the list of files in the host
directory.

We\'ll create a simple `index.html` file in the work directory that
simply displays \'Hello World\':

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Hello!</title>
  </head>

  <body>
    <h1>Hello World!</h1>
    <p>This is a simple paragraph.</p>
  </body>
</html>
```

Then, we can run the following script to launch the server:

```python
import http.server
import socketserver

PORT = 8000
class MyHttpRequestHandler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        self.path = 'index.html'
        return http.server.SimpleHTTPRequestHandler.do_GET(self)

Handler = MyHttpRequestHandler

with socketserver.TCPServer(("", PORT), Handler) as httpd:
    print("Http Server Serving at port", PORT)
    httpd.serve_forever()
```

Now, when we navigate to `http://localhost:8000/`, we see the custom
HTML from our `index.html` file instead of the list of files in our
working directory:

::: img-container
![Example Python HTTP Server index.html
file](/img/initialcommit/http-server-example2.jpg "Example Python HTTP Server index.html file")
:::

## Summary

Python's HTTP server makes it easy for developers to get started with
web client-server communication either from the terminal or from a
script. Although `http.server` isn\'t secure for use in a production
environment, it provides an easy way for developers to view a local web
design or to share files across a private network. For people new to web
development, `http.server` is a user-friendly way to experiment with
website designs.

## Next Steps

If you\'re interested in learning more about the basics of coding,
programming, and software development, check out our [Coding Essentials
Guidebook for
Developers](/store/coding-essentials-guidebook-for-developers), where we
cover the essential languages, concepts, and tools that you\'ll need to
become a professional developer.

Thanks and happy coding! We hope you enjoyed this article. If you have
any questions or comments, feel free to reach out to
<jacob@initialcommit.io>.

<div>

## Final Notes

<div>

Recommended product: [Coding Essentials Guidebook for
Developers](https://www.amazon.com/gp/product/B084M21LBZ/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325&creativeASIN=B084M21LBZ&linkCode=as2&tag=initialcommit-20&linkId=61c9c4e9dd904df856b13245ee2250c5){rel="nofollow"
target="\_blank"}

</div>

</div>
:::
:::

::: relatedArticles

#### Related Articles

::: main-carousel
::: carousel-cell
::: carousel-cell-inner
::: related-post-outer
::: related-post-inner
::: related-post-content
[![Image of Python Yield vs
Return](/img/initialcommit/python-yield-vs-return.png "Python Yield vs Return"){width="900"
height="500"}](/blog/python-yield-vs-return){.related-blog-post-clicks
style="background-color: lightblue;"}

## [Python Yield vs Return](/blog/python-yield-vs-return){.post-link .related-blog-post-clicks} {#python-yield-vs-return .related-post-title}

:::

::: related-post-footer
![Image of the Git
logo](/img/initialcommit/babygit-logo.png "Git Logo"){.related-post-footer-icon
width="40" height="40"}

::: related-post-categories
[python]{.related-category}[programming]{.related-category}
:::
:::
:::
:::
:::
:::

::: carousel-cell
::: carousel-cell-inner
::: related-post-outer
::: related-post-inner
::: related-post-content
[![Image of Python Zip Two
Lists](/img/initialcommit/python-zip-two-lists.png "Python Zip Two Lists"){width="900"
height="500"}](/blog/python-zip-two-lists){.related-blog-post-clicks
style="background-color: lightblue;"}

## [Python Zip Two Lists](/blog/python-zip-two-lists){.post-link .related-blog-post-clicks} {#python-zip-two-lists .related-post-title}

:::

::: related-post-footer
![Image of the Git
logo](/img/initialcommit/babygit-logo.png "Git Logo"){.related-post-footer-icon
width="40" height="40"}

::: related-post-categories
[python]{.related-category}[programming]{.related-category}
:::
:::
:::
:::
:::
:::

::: carousel-cell
::: carousel-cell-inner
::: related-post-outer
::: related-post-inner
::: related-post-content
[![Image of Using raw_input() in Python
3](/img/initialcommit/raw-input-python-3.png "Using raw_input() in Python 3"){width="900"
height="500"}](/blog/raw-input-python-3){.related-blog-post-clicks
style="background-color: lightblue;"}

## [Using raw_input() in Python 3](/blog/raw-input-python-3){.post-link .related-blog-post-clicks} {#using-raw_input-in-python-3 .related-post-title}

:::

::: related-post-footer
![Image of the Git
logo](/img/initialcommit/babygit-logo.png "Git Logo"){.related-post-footer-icon
width="40" height="40"}

::: related-post-categories
[python]{.related-category}[programming]{.related-category}
:::
:::
:::
:::
:::
:::

::: carousel-cell
::: carousel-cell-inner
::: related-post-outer
::: related-post-inner
::: related-post-content
[![Image of Python List
Print](/img/initialcommit/python-list-print.png "Python List Print"){width="900"
height="500"}](/blog/python-list-print){.related-blog-post-clicks
style="background-color: lightblue;"}

## [Python List Print](/blog/python-list-print){.post-link .related-blog-post-clicks} {#python-list-print .related-post-title}

:::

::: related-post-footer
![Image of the Git
logo](/img/initialcommit/babygit-logo.png "Git Logo"){.related-post-footer-icon
width="40" height="40"}

::: related-post-categories
[python]{.related-category}[programming]{.related-category}
:::
:::
:::
:::
:::
:::

::: carousel-cell
::: carousel-cell-inner
::: related-post-outer
::: related-post-inner
::: related-post-content
[![Image of NumPy around() in
Python](/img/initialcommit/np-around.png "NumPy around() in Python"){width="900"
height="500"}](/blog/np-around){.related-blog-post-clicks
style="background-color: lightblue;"}

## [NumPy around() in Python](/blog/np-around){.post-link .related-blog-post-clicks} {#numpy-around-in-python .related-post-title}

:::

::: related-post-footer
![Image of the Git
logo](/img/initialcommit/babygit-logo.png "Git Logo"){.related-post-footer-icon
width="40" height="40"}

::: related-post-categories
[python]{.related-category}[programming]{.related-category}
:::
:::
:::
:::
:::
:::

::: carousel-cell
::: carousel-cell-inner
::: related-post-outer
::: related-post-inner
::: related-post-content
[![Image of Assignment Operators in
Python](/img/initialcommit/assignment-operators-python.png "Assignment Operators in Python"){width="900"
height="500"}](/blog/assignment-operators-python){.related-blog-post-clicks
style="background-color: lightblue;"}

## [Assignment Operators in Python](/blog/assignment-operators-python){.post-link .related-blog-post-clicks} {#assignment-operators-in-python .related-post-title}

:::

::: related-post-footer
![Image of the Git
logo](/img/initialcommit/babygit-logo.png "Git Logo"){.related-post-footer-icon
width="40" height="40"}

::: related-post-categories
[python]{.related-category}[programming]{.related-category}
:::
:::
:::
:::
:::
:::

::: carousel-cell
::: carousel-cell-inner
::: related-post-outer
::: related-post-inner
::: related-post-content
[![Image of __repr__ in
Python](/img/initialcommit/repr-in-python.png "__repr__ in Python"){width="900"
height="500"}](/blog/repr-in-python){.related-blog-post-clicks
style="background-color: lightblue;"}

## [\_\_repr\_\_ in Python](/blog/repr-in-python){.post-link .related-blog-post-clicks} {#repr\_\_-in-python .related-post-title}

:::

::: related-post-footer
![Image of the Git
logo](/img/initialcommit/babygit-logo.png "Git Logo"){.related-post-footer-icon
width="40" height="40"}

::: related-post-categories
[python]{.related-category}[programming]{.related-category}
:::
:::
:::
:::
:::
:::
:::
:::

[Back to Blog](/blog){.home-link}
:::
:::
:::

::: {#footer-ad style="background-color: white"}
ADVERTISEMENT

::: footer-ad-wrapper
::: {#waldo-tag-14005}
:::
:::
:::

::: {#footer-ad}
::: {#waldo-tag-14050}
:::
:::

::: container
::: {.row .justify-content-center .text-center}
::: col-2
[](https://www.instagram.com/initialcommit){.instagram-link
target="\_blank" aria-label="Instagram"}
:::

::: col-2
[](https://www.facebook.com/initcommit){.facebook-link target="\_blank"
aria-label="Facebook"}
:::

::: col-2
[](https://www.reddit.com/user/initcommit){.reddit-link target="\_blank"
aria-label="Reddit"}
:::

::: col-2
[](https://twitter.com/initcommit){.twitter-link target="\_blank"
aria-label="Twitter"}
:::

::: col-2
[](mailto:jacob@initialcommit.io){.email-link target="\_blank"
aria-label="Email"}
:::
:::

::: {.row .justify-content-center .my-4}
[HOME](/){.nav-home .px-3
style="color: #212529;"}[TOOLS](/tools){.nav-tools .px-3
style="color: #212529;"}[BLOG](/blog){.nav-blog .px-3
style="color: #212529;"}[BOOKS](/store?type=book){.nav-books .px-3
style="color: #212529;"}[MERCH](/store?type=merch){.nav-merch .px-3
style="color: #212529;"}[STORE](/store){.nav-store .px-3
style="color: #212529;"}[ABOUT](/about){.nav-about .px-3
style="color: #212529;"}[CONTACT](/contact){.nav-contact .px-3
style="color: #212529;"}[WRITE FOR US](/write-for-us){.nav-write .for
.us .px-3 style="color: #212529;"}
:::
:::

::: {.footer-copyright .py-3 style="background-color: rgba(51, 51, 51, 0.08)"}
::: {#footerCopyright style="color: #212529;"}
© Copyright 2023 Initial Commit LLC
:::

::: {#footerCopyright style="color: #212529;"}
[Privacy Policy](/privacy-policy){#privacy-policy-link
style="color: #212529;"}
:::

::: {#footerCopyright style="color: #212529;"}
This site uses icons from
[Icons8](https://icons8.com){style="color: #212529;"}
:::
:::

<div>

::: {#emailCaptureModal .modal .fade .ce tabindex="-1" role="dialog"}
::: {.modal-dialog .modal-dialog-centered role="document"}
::: {.modal-content style="background-color: #ffffff"}
::: modal-header

#### Get the first 5 chapters of our _[Coding Essentials Guidebook for Developers]{.underline}_ [**FREE**]{.blue}! {#get-the-first-5-chapters-of-our-coding-essentials-guidebook-for-developers-free .modal-title}

[×]{aria-hidden="true"}
:::

::: modal-body
::: emailCaptureFormDiv1
::: {#interest-group .input-group .input-group-sm}
::: input-group-append
::: {.dropdown-menu .dropdown-menu-right}
[Git](#){.dropdown-item}[Spring
Boot](#){.dropdown-item}[Java](#){.dropdown-item}[Python](#){.dropdown-item}[C
Programming](#){.dropdown-item}[General
Programming](#){.dropdown-item}[MongoDB](#){.dropdown-item}[Javascript](#){.dropdown-item}[HTML](#){.dropdown-item}[CSS](#){.dropdown-item}[Bitcoin](#){.dropdown-item}[Ethereum](#){.dropdown-item}[Cryptocurrency](#){.dropdown-item}[Amazon
Web Services (AWS)](#){.dropdown-item}
:::
:::
:::

::: {#bookFormat-group .input-group .input-group-sm .mt-3 .mb-3}
::: input-group-append
::: {.dropdown-menu .dropdown-menu-right}
[pdf](#){.dropdown-item}[epub](#){.dropdown-item}
:::
:::
:::

::: {.input-group .input-group-sm .mb-3}
::: input-group-append
Submit
:::
:::
:::

::: emailCaptureFormDiv2
:::
:::
:::
:::
:::

::: {.product-block .ce tabindex="-1" role="dialog"}
::: product-block-content

#### The programming guide I wish I had when I started learning to code\... 🚀👨‍💻📚

\
[![Image of the cover of the Coding Essentials Guidebook for
Developers](/img/initialcommit/ce-half-cover.png "Coding Essentials Guidebook for Developers Cover"){.toast-cover
width="324"
height="500"}](/store/coding-essentials-guidebook-for-developers)\
\

#### Check out our [Coding Essentials Guidebook for Developers](/store/coding-essentials-guidebook-for-developers)

:::
:::

</div>
