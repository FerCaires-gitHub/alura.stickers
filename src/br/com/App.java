package br.com;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;




public class App {
    public static void main(String[] args) throws Exception {
        
        var key = System.getenv("IMDB_API_KEY");
        
        //var url = String.format("https://imdb-api.com/en/API/Top250Movies/%s",key);
        var url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        
        //var cep = "14807040";
        //var url = String.format("http://viacep.com.br/ws/%s/json/", cep);

        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var response  = client.send(request, BodyHandlers.ofString());
        var body = response.body().toString();
        
        var parser = new JsonParser();
        var dados = parser.parse(body);

        for (Map<String,String> map : dados) {
            System.out.println(map.get("title"));
            System.out.println(map.get("image"));
            System.out.println(map.get("imDbRating"));
            System.out.println();
        }
        

    }
}
