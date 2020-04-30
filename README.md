## Installation on Mac OS X:
1.  Install Homebrew Package Manager, follow instructions on https://brew.sh/
2.  Install JDK (Java Development Kit) 8 or above using Homebrew:   
    `brew cask install java`
3.  In order you should whitelist JDK for MacOS use `brew cask install java --no-quarantine`    
4.  In order you need to reinstall JDK use
    `brew cask reinstall java`
5.  Check that JDK was successfully installed:  
    `java -version`
6.  Install Maven using Homebrew  
    `brew install maven`
7.  Check that Maven was installed  
    `mvn -version`
8.  Install Newman using Homebrew
    `brew install newman`  
9.  Check that Newman was installed  
    `newman -version`
10. The newman run command allows you to specify a collection to be run. You can easily export your Postman Collection as a json file from the Postman App and run it using Newman:
    `newman run examples/sample-collection.json`
11. If your collection file is available as an URL (such as from our Cloud API service), Newman can fetch your file and run it as well:
    `newman run https://www.getpostman.com/collections/cf19e8e5e8a3a4c89b5e`
12. View Serenity HTML report
    `mvn serenity:aggregate`      