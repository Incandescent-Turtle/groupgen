# Group Generator
Creates groups for classes and things. 
<a href="https://github.com/Incandescent-Turtle/groupgen/raw/main/nameGame.jar">Click to download runnable jar</a>
![example](https://user-images.githubusercontent.com/59327500/160219718-da45f97d-d0f6-4dac-b6a2-918511647795.gif)

---
## How to Use
Needs classList.txt and parameters.txt in same folder to run.

### classList.txt
This is the file to input all the student names. Keep in mind only first names are used, so if there are multiple people with the same first name, append an identifier to the end of their name, not using a space (use an underscore instead!)
#### Usage
If the file contains:  
<code>
  John 
  Henry 
  Jacob H
  Claire_H
  Claire_M
  Jooniper
</code>  
This will sort the names <code> John, Henry, Jacob, Claire_H, Claire_M, Jooniper</code>.
### parameters.txt
This file is used to set up groups of kids that cannot be together
#### Usage
However many first names separated by spaces. None of those people will show up in a group together.  
If a file contained the following:  
<code>
  John Henry Jacob
</code>  
No groups would contain more than one of these boys.
