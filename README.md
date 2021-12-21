# NewYorkTimes_ArticleSearchAPI
# Java 16+ must be used to run this application
# Windows instructions:
This branch is contains the jar file and javafx sdk needed to run this application locally on your computer. The following steps illustrate how to do this:
1. Right click on the top-right corner a green button titled 'Code'.
2. Click the option to download zip, and download the zip folder to a location on your computer.
3. Right click on the downloaded zip folder and click 'Extract all', accepting the default location.
4. Open the command prompt by searching in the bottom-left search bar "cmd".
5. After the command prompt has opened, type 'cd <path_name>' where <path_name> is the folder location of the project folder (non-zip version)
6. Finally, after traversing to the folder location, type the following command:

java -jar --module-path "javafx-sdk-17.0.1/lib" --add-modules javafx.controls,javafx.fxml Comp1011-Assignment2.jar
