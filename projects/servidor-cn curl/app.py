
from http.server import BaseHTTPRequestHandler, HTTPServer
import json

# read j.json
with open('j.json', 'r') as json_file:
    data = json.load(json_file)

class RequestHandler(BaseHTTPRequestHandler):
    def _set_response(self, status_code=200, content_type='text/json'):
        self.send_response(status_code)
        self.send_header('Content-type', content_type)
        self.end_headers()

    def do_GET(self):
        if self.path == '/recursos':
            self._set_response()
            self.wfile.write(json.dumps(data).encode())
        elif self.path.startswith('/recurso/'):
            resource_id = int(self.path.split('/')[2])
            if resource_id in data:
                self._set_response()
                self.wfile.write(json.dumps(data[resource_id]).encode())
            else:
                self._set_response(404)
                self.wfile.write("Recurso no encontrado".encode())

    def do_POST(self):
        if self.path == '/recursos':
            content_length = int(self.headers['Content-Length'])
            data_received = self.rfile.read(content_length)
            new_resource = json.loads(data_received.decode())
            new_id = max(data.keys()) + 1
            new_resource["id"] = new_id
            data[new_id] = new_resource
            self._set_response(201)
            self.wfile.write(json.dumps(new_resource).encode())

    def do_PUT(self):
        if self.path.startswith('/recurso/'):
            resource_id = int(self.path.split('/')[2])
            if resource_id in data:
                content_length = int(self.headers['Content-Length'])
                data_received = self.rfile.read(content_length)
                updated_resource = json.loads(data_received.decode())
                updated_resource["id"] = resource_id
                data[resource_id] = updated_resource
                self._set_response()
                self.wfile.write(json.dumps(updated_resource).encode())
            else:
                self._set_response(404)
                self.wfile.write("Recurso no encontrado".encode())

    def do_DELETE(self):
        if self.path.startswith('/recurso/'):
            resource_id = int(self.path.split('/')[2])
            if resource_id in data:
                del data[resource_id]
                self._set_response(204)
            else:
                self._set_response(404)
                self.wfile.write("Recurso no encontrado".encode())
    
    def do_CONNECT(self):
        self._set_response(501)  # Método no implementado
        self.wfile.write("Método CONNECT no implementado".encode())

    def do_OPTIONS(self):
        self._set_response()
        self.send_header('Allow', 'GET, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH')
        self.wfile.write("Método OPTIONS permitido".encode())

    def do_TRACE(self):
        self._set_response()
        self.wfile.write("Método TRACE es compatible".encode())

    def do_PATCH(self):
        if self.path.startswith('/recurso/'):
            resource_id = int(self.path.split('/')[2])
            if resource_id in data:
                content_length = int(self.headers['Content-Length'])
                data_received = self.rfile.read(content_length)
                patch_data = json.loads(data_received.decode())
                for key, value in patch_data.items():
                    data[resource_id][key] = value
                data[resource_id]["id"] = resource_id
                self._set_response()
                self.wfile.write(json.dumps(data[resource_id]).encode())
            else:
                self._set_response(404)
                self.wfile.write("Recurso no encontrado".encode())
        else:
            self._set_response(400)
            self.wfile.write("Ruta no válida para el método PATCH".encode())

def run():
    server_address = ('', 8000)
    httpd = HTTPServer(server_address, RequestHandler)
    print('Iniciando el servidor...')
    httpd.serve_forever()

if __name__ == '__main__':
    run()
