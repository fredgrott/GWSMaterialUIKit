![material matters](art/materialmatters.png)
GWSMaterialUIKit
================

Since the gradle build system gets bogged down when we have 
many libs(aars) and since I want to control the diamond dependency problem 
the UI back ported libs have been combined into one UI library.

The main important feature of the is library is that it integrates with 
how the android support internals work as far as Theme, Type, etc. It works 
this way, the custom views we create are extensions of custom stuff in the 
GWSMaterialUIKit library that extends views/widgets from the AppCompat 
Android Support library stuff so that we get the benefits of tint, etc(hopefully, 
android engineers adopt that pattern for other UI feature changes past api 24 as 
that would make ti oh so much easier to deal with back ports).

In a start-up with a growing android team you want the UI base 3rd party 
widgets combined into one internal library that your team contributes 
to and controls as the main goal in the beginning of a start-up is to hose 
out new features to somewhat bootstrap increasing the MAUs.
 
This is a temporary library when targeting stuff under api 21 and its 
expected that when developers are focusing on targeting as minSDk api 21 
or higher that this library will no longer be used.

# Usage

if library, describe how to download library using jitpack than describe how to use the library.

I use jitpack to upload my libraries so you put this in your root buildscript:

```groovy
allprojects {
        repositories {
            jcenter()
            maven { url "https://jitpack.io" }
        }
   }
```
Than in the module buildscript:


```groovy
compile 'com.github.shareme:GWSMaterialUIKit:{latest-release-number}@aar'
```

# Developed By

![gws logo](art/gws_github_header.png)

<a href="http://stackoverflow.com/users/237740/fred-grott">
<img src="http://stackoverflow.com/users/flair/237740.png" width="208" height="58" alt="profile for Fred Grott at Stack Overflow, Q&amp;A for professional and enthusiast programmers" title="profile for Fred Grott at Stack Overflow, Q&amp;A for professional and enthusiast programmers">
</a>


Created by [Fred Grott](http://shareme.github.com).


# Credits





# Resources

Resources can be found at the [GWSTheWayOfAndroid wiki](http://github.com/shareme/GWSTheWayOfAndroid/wiki).



# License

Copyright (C) 2016 Fred Grott(aka shareme GrottWorkShop)

Licensed under the Apache License, Version 2.0 (the "License"); you 
may not use this file except in compliance with the License. You may 
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an 
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
either express or implied. See the License for the specific language 
governing permissions and limitations under License.