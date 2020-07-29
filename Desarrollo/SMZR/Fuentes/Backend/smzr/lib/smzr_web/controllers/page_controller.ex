defmodule SmzrWeb.PageController do
  use SmzrWeb, :controller

  def index(conn, _params) do
    render(conn, "index.html")
  end
end
