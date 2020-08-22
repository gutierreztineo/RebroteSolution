defmodule SmzrWeb.UserLocationControllerTest do
  use SmzrWeb.ConnCase

  alias Smzr.Tracking
  alias Smzr.Tracking.UserLocation

  @create_attrs %{

  }
  @update_attrs %{

  }
  @invalid_attrs %{}

  def fixture(:user_location) do
    {:ok, user_location} = Tracking.create_user_location(@create_attrs)
    user_location
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all user_locations", %{conn: conn} do
      conn = get(conn, Routes.user_location_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create user_location" do
    test "renders user_location when data is valid", %{conn: conn} do
      conn = post(conn, Routes.user_location_path(conn, :create), user_location: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.user_location_path(conn, :show, id))

      assert %{
               "id" => id
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.user_location_path(conn, :create), user_location: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update user_location" do
    setup [:create_user_location]

    test "renders user_location when data is valid", %{conn: conn, user_location: %UserLocation{id: id} = user_location} do
      conn = put(conn, Routes.user_location_path(conn, :update, user_location), user_location: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.user_location_path(conn, :show, id))

      assert %{
               "id" => id
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, user_location: user_location} do
      conn = put(conn, Routes.user_location_path(conn, :update, user_location), user_location: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete user_location" do
    setup [:create_user_location]

    test "deletes chosen user_location", %{conn: conn, user_location: user_location} do
      conn = delete(conn, Routes.user_location_path(conn, :delete, user_location))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.user_location_path(conn, :show, user_location))
      end
    end
  end

  defp create_user_location(_) do
    user_location = fixture(:user_location)
    %{user_location: user_location}
  end
end
