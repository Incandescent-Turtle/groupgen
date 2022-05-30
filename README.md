# 🛠️ Group Generator
Creates groups for classes and things. <br>
<a href="https://github.com/Incandescent-Turtle/groupgen/raw/main/nameGame.jar">Click here to download runnable jar</a>
![example](https://user-images.githubusercontent.com/59327500/160219718-da45f97d-d0f6-4dac-b6a2-918511647795.gif)

---
## 📝 How to Use
1. To load your list of names you will need a file named [classList.txt](#classList.txt) in the same folder as the jar.  
2. To load your restrictions you will need a file named [parameters.txt](#parameters.txt) in the same folder as the jar. 
3. Once your names are loaded in, you can add/remove any that you need to.
4. In the bottom right select your group size, then click "Generate Groups". You can re-adjust group size and generate new groups as needed.

<hr width="50%">

### 📁 classList.txt
This is the file to input all the student names. Keep in mind only first names are used, so if there are multiple people with the same first name, append an identifier to the end of their name, not using a space (use an underscore instead!)

<hr width="25%">

#### Usage
If the file contains:
>  John  
>  Henry  
>  Jacob H  
>  Claire_H  
>  Claire_M  
>  Jooniper
   

This will sort the names <code> John, Henry, Jacob, Claire_H, Claire_M, Jooniper</code>.

<hr width="50%">

### 📁 parameters.txt
This file is used to set up groups of kids that cannot be together

<hr width="25%">

#### Usage
However many first names separated by spaces. None of those people will show up in a group together.  
If a file contained the following:  
<code>
  John Henry Jacob
</code>  
No groups would contain more than one of these boys.
