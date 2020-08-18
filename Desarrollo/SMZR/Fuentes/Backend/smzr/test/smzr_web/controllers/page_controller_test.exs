defmodule SmzrWeb.PageControllerTest do
  use SmzrWeb.ConnCase

  test "GET /", %{conn: conn} do
    conn = get(conn, "/")
    assert html_response(conn, 200) =~ "Bienvenido al API de SMZR!"
  end
end
