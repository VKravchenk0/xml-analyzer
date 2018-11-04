# Build:
```mvn clean install```

# Run:
```java -jar xml-analyzer.jar <original-file-path> <modified-file-path>``` - Will use default element id - ```#make-everything-ok-button```

```java -jar xml-analyzer.jar <original-file-path> <modified-file-path> <element-id>```

# Execution results:

* sample-1-evil-gemini.html
```
Element found: 
html[0]  > body[1]  > div[0]  > div[1]  > div[2]  > div[0]  > div[0]  > div[1]  > a[1] 

This element was chosen because of the following matches:
Element's text match. Text value is 'Make everything OK'
Attribute 'class' match. Attribute value is 'btn btn-success'
Attribute 'title' match. Attribute value is 'Make-Button'
Attribute 'onclick' match. Attribute value is 'javascript:window.okDone(); return false;'
```

* sample-2-container-and-clone.html
```
Element found: 
html[0]  > body[1]  > div[0]  > div[1]  > div[2]  > div[0]  > div[0]  > div[1]  > div[0]  > a[0] 

This element was chosen because of the following matches:
Element's text match. Text value is 'Make everything OK'
Attribute 'href' match. Attribute value is '#ok'
Attribute 'title' match. Attribute value is 'Make-Button'
Attribute 'rel' match. Attribute value is 'next'
```

* sample-3-the-escape.html
```
Element found: 
html[0]  > body[1]  > div[0]  > div[1]  > div[2]  > div[0]  > div[0]  > div[2]  > a[0] 

This element was chosen because of the following matches:
Attribute 'class' match. Attribute value is 'btn btn-success'
Attribute 'href' match. Attribute value is '#ok'
Attribute 'rel' match. Attribute value is 'next'
Attribute 'onclick' match. Attribute value is 'javascript:window.okDone(); return false;'
```

* sample-4-the-mash.html
```
Element found: 
html[0]  > body[1]  > div[0]  > div[1]  > div[2]  > div[0]  > div[0]  > div[2]  > a[0] 

This element was chosen because of the following matches:
Attribute 'class' match. Attribute value is 'btn btn-success'
Attribute 'href' match. Attribute value is '#ok'
Attribute 'title' match. Attribute value is 'Make-Button'
Attribute 'rel' match. Attribute value is 'next'
```

* Test with different id - 'side-menu'. File - sample-1-evil-gemini.html
```
Element found: 
html[0]  > body[1]  > div[0]  > nav[0]  > div[2]  > div[0]  > ul[0] 

This element was chosen because of the following matches:
Element's text match. Text value is 'Dashboard'
Attribute 'class' match. Attribute value is 'nav'
Attribute 'id' match. Attribute value is 'side-menu'
```