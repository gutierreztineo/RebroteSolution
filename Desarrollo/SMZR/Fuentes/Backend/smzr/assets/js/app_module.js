import phx from "https://cdn.pika.dev/phoenix@^1.5.3";
import LiveSocket from "https://cdn.pika.dev/phoenix_live_view@^0.13.2";

let csrfToken = document.querySelector("meta[name='csrf-token']").getAttribute("content")
let liveSocket = new LiveSocket("/live", phx.Socket, {params: {_csrf_token: csrfToken}})
liveSocket.connect()