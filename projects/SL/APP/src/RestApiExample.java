import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class RestApiExample {
    private static Map<Integer, JSONObject> data = new HashMap<>();
    private static int nextId = 1;

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        server.createContext("/recursos", new ResourcesHandler());
        server.createContext("/recurso/", new ResourceHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

        System.out.println("Servidor en ejecución en el puerto 8080...");
    }

    static class ResourcesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("GET".equals(exchange.getRequestMethod())) {
                JSONArray jsonArray = new JSONArray(data.values());
                sendResponse(exchange, 200, jsonArray.toString());
            } else if ("POST".equals(exchange.getRequestMethod())) {
                try {
					InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
					BufferedReader br = new BufferedReader(isr);
					StringBuilder sb = new StringBuilder();
					String line;
					while ((line = br.readLine()) != null) {
					    sb.append(line);
					}
					br.close();
					isr.close();

					JSONObject newResource = new JSONObject(sb.toString());
					newResource.put("id", nextId);
					data.put(nextId, newResource);
					nextId++;

					sendResponse(exchange, 201, newResource.toString());
				} catch (IOException | JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                sendResponse(exchange, 405, "Método no permitido");
            }
        }
    }

    static class ResourceHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            String[] parts = path.split("/");
            if (parts.length == 3) {
                int resourceId = Integer.parseInt(parts[2]);
                JSONObject resource = data.get(resourceId);

                if (resource != null) {
                    try {
						if ("GET".equals(exchange.getRequestMethod())) {
						    sendResponse(exchange, 200, resource.toString());
						} else if ("PUT".equals(exchange.getRequestMethod())) {
						    InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "UTF-8");
						    BufferedReader br = new BufferedReader(isr);
						    StringBuilder sb = new StringBuilder();
						    String line;
						    while ((line = br.readLine()) != null) {
						        sb.append(line);
						    }
						    br.close();
						    isr.close();

						    JSONObject updatedResource = new JSONObject(sb.toString());
						    updatedResource.put("id", resourceId);
						    data.put(resourceId, updatedResource);

						    sendResponse(exchange, 200, updatedResource.toString());
						} else if ("DELETE".equals(exchange.getRequestMethod())) {
						    data.remove(resourceId);
						    sendResponse(exchange, 204, "");
						} else {
						    sendResponse(exchange, 405, "Método no permitido");
						}
					} catch (IOException | JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } else {
                    sendResponse(exchange, 404, "Recurso no encontrado");
                }
            } else {
                sendResponse(exchange, 400, "Ruta no válida");
            }
        }
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(statusCode, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
