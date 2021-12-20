# Java application for using the New York Times 'Article Search' REST API.

Using the the New York Times Article Search API, you can fetch a JSON response object which contains recently published articles via a supplied keyword in a query. In this Maven JavaFx application, a JSON response is fetched from the New York Times website using HttpComponents and stored in a file, and is then parsed using Google Gson library to populate a list with the parsed data. Then, you can display specific article information by selecting an article from that list.

java -jar --module-path "javafx-sdk-17.0.1/lib" --add-modules javafx.controls,javafx.fxml Comp1011-Assignment2.jar
