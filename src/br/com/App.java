package br.com;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.text.MessageFormat;
import java.util.Map;

import br.com.Services.GeradorFigurinhaService;




public class App {
    public static void main(String[] args) throws Exception {
        
        var key = System.getenv("IMDB_API_KEY");
        
        //var url = String.format("https://imdb-api.com/en/API/Top250Movies/%s",key);
        var url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var response  = client.send(request, BodyHandlers.ofString());
        var body = response.body().toString();
        
        var parser = new JsonParser();
        var dados = parser.parse(body);
        var stickMaker = new GeradorFigurinhaService();

        for (Map<String,String> map : dados) {
            System.out.println(map.get("title"));
            System.out.println(MessageFormat.format("url:{0}", map.get("image")));
            var inputStream = new URL(map.get("image")).openStream();
            stickMaker.create(inputStream, "toper", map.get("title"));
            System.out.println();
        }
        

    }
}
