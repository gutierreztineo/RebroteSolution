defmodule SmzrWeb.AilmentControllerTest do
  use SmzrWeb.ConnCase

  alias Smzr.Monitoring
  alias Smzr.Monitoring.Ailment

  @create_attrs %{
    description: "some description"
  }
  @update_attrs %{
    description: "some updated description"
  }
  @invalid_attrs %{description: nil}

  def fixture(:ailment) do
    {:ok, ailment} = Monitoring.create_ailment(@create_attrs)
    ailment
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all ailments", %{conn: conn} do
      conn = get(conn, Routes.ailment_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create ailment" do
    test "renders ailment when data is valid", %{conn: conn} do
      conn = post(conn, Routes.ailment_path(conn, :create), ailment: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.ailment_path(conn, :show, id))

      assert %{
               "id" => id,
               "description" => "some description"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.ailment_path(conn, :create), ailment: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update ailment" do
    setup [:create_ailment]

    test "renders ailment when data is valid", %{conn: conn, ailment: %Ailment{id: id} = ailment} do
      conn = put(conn, Routes.ailment_path(conn, :update, ailment), ailment: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.ailment_path(conn, :show, id))

      assert %{
               "id" => id,
               "description" => "some updated description"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, ailment: ailment} do
      conn = put(conn, Routes.ailment_path(conn, :update, ailment), ailment: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete ailment" do
    setup [:create_ailment]

    test "deletes chosen ailment", %{conn: conn, ailment: ailment} do
      conn = delete(conn, Routes.ailment_path(conn, :delete, ailment))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.ailment_path(conn, :show, ailment))
      end
    end
  end

  defp create_ailment(_) do
    ailment = fixture(:ailment)
    %{ailment: ailment}
  end
end
