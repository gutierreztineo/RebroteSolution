defmodule SmzrWeb.AilmentLevelControllerTest do
  use SmzrWeb.ConnCase

  alias Smzr.Monitoring
  alias Smzr.Monitoring.AilmentLevel

  @create_attrs %{
    description: "some description",
    level: 42
  }
  @update_attrs %{
    description: "some updated description",
    level: 43
  }
  @invalid_attrs %{description: nil, level: nil}

  def fixture(:ailment_level) do
    {:ok, ailment_level} = Monitoring.create_ailment_level(@create_attrs)
    ailment_level
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all ailment_levels", %{conn: conn} do
      conn = get(conn, Routes.ailment_level_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create ailment_level" do
    test "renders ailment_level when data is valid", %{conn: conn} do
      conn = post(conn, Routes.ailment_level_path(conn, :create), ailment_level: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.ailment_level_path(conn, :show, id))

      assert %{
               "id" => id,
               "description" => "some description",
               "level" => 42
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.ailment_level_path(conn, :create), ailment_level: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update ailment_level" do
    setup [:create_ailment_level]

    test "renders ailment_level when data is valid", %{conn: conn, ailment_level: %AilmentLevel{id: id} = ailment_level} do
      conn = put(conn, Routes.ailment_level_path(conn, :update, ailment_level), ailment_level: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.ailment_level_path(conn, :show, id))

      assert %{
               "id" => id,
               "description" => "some updated description",
               "level" => 43
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, ailment_level: ailment_level} do
      conn = put(conn, Routes.ailment_level_path(conn, :update, ailment_level), ailment_level: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete ailment_level" do
    setup [:create_ailment_level]

    test "deletes chosen ailment_level", %{conn: conn, ailment_level: ailment_level} do
      conn = delete(conn, Routes.ailment_level_path(conn, :delete, ailment_level))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.ailment_level_path(conn, :show, ailment_level))
      end
    end
  end

  defp create_ailment_level(_) do
    ailment_level = fixture(:ailment_level)
    %{ailment_level: ailment_level}
  end
end
