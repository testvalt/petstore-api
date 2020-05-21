## Installation on Mac OS X:
1.  Install Homebrew Package Manager, follow instructions on https://brew.sh/
2.  Install JDK (Java Development Kit) 8 or above using Homebrew:   
    `brew cask install java`
3.  In order you should whitelist JDK for MacOS use: 
    `brew cask install java --no-quarantine`    
4.  In order you need to reinstall JDK use:
    `brew cask reinstall java`
5.  Check that JDK was successfully installed:  
    `java -version`
6.  Install Maven using Homebrew:  
    `brew install maven`
7.  Check that Maven was installed:  
    `mvn -version`
8. In order you need to view Serenity HTML report use:
    `mvn serenity:aggregate`      