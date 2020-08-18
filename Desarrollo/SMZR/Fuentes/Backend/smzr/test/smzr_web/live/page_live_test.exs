defmodule SmzrWeb.PageLiveTest do
  use SmzrWeb.ConnCase

  import Phoenix.LiveViewTest

  test "disconnected and connected render", %{conn: conn} do
    {:ok, page_live, disconnected_html} = live(conn, "/")
    assert disconnected_html =~ "Bienvenido al API de SMZR!"
    assert render(page_live) =~ "Bienvenido al API de SMZR!"
  end
end
